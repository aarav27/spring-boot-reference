package org.sharma.aarav.spring.boot.reference.data.config;

/**
 * @author Aarav Sharma
 */

import com.mongodb.*;
import com.mongodb.connection.ClusterSettings;
import com.mongodb.connection.SslSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import com.mongodb.reactivestreams.client.MongoClients;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aarav Sharma
 */
@EnableAutoConfiguration
@EnableReactiveMongoRepositories(basePackages = "org.sharma.aarav.spring.boot.reference.data")
public class ReactiveMongoConfig extends AbstractReactiveMongoConfiguration {

    @Autowired
    MongoProperties mongoProperties;

    private com.mongodb.reactivestreams.client.MongoClient mongo;

    private MongoClientOptions options;

    private List<ServerAddress> serverAddresses;

    private MongoCredential mongoCredential;

    @PostConstruct
    public void init() {
        options = MongoClientOptions.builder()
                .serverSelectionTimeout(mongoProperties.getServerSelectionTimeout())
                .socketTimeout(mongoProperties.getSocketTimeout())
                .connectTimeout(mongoProperties.getConnectionTimeout())
                .minHeartbeatFrequency(mongoProperties.getMinHeartbeatFrequency())
                .heartbeatSocketTimeout(mongoProperties.getSocketTimeout()).build();
        mongoCredential = MongoCredential.createCredential(mongoProperties.getUserName(), mongoProperties.getMongoDB(), mongoProperties.getMongoPassword().toCharArray());
        serverAddresses = new ArrayList<ServerAddress>();
        for (String ip : mongoProperties.getMongoCluster().split(",")) {
            serverAddresses.add(new ServerAddress(ip,mongoProperties.getMongoPort()));
        }
    }

    @Override
    @Bean
    public com.mongodb.reactivestreams.client.MongoClient reactiveMongoClient() {
        this.mongo = MongoClients.create(mongoClientSettings());
        return this.mongo;
    }

    @Override
    protected String getDatabaseName() {
        return mongoProperties.getMongoDB();
    }

    @PreDestroy
    public void close() {
        if (this.mongo != null) {
            this.mongo.close();
        }
    }

    private MongoClientSettings mongoClientSettings() {
        return MongoClientSettings.builder()
                .applyToClusterSettings(clusterSettingsBuilder())
                .applyToSslSettings(sslSettingsBuilder())
                .credential(mongoCredential)
                .build();
    }

    private Block<ClusterSettings.Builder> clusterSettingsBuilder() {
        return (cluster) -> cluster.hosts(serverAddresses).build();
    }

    private Block<SslSettings.Builder> sslSettingsBuilder() {
        return (ssl) -> ssl.enabled(false).build();
    }

}