package org.sharma.aarav.spring.boot.reference.data.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties
public class MongoProperties {

    @Value("${spring.data.mongodb.database}")
    private String mongoDB;

    @Value("${spring.data.mongodb.repositories.enabled}")
    private String enabled;

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.username}")
    private String userName;

    @Value("${spring.data.mongodb.password}")
    private String mongoPassword;

    @Value("${spring.data.mongodb.port}")
    private Integer mongoPort;

    @Value("${spring.data.mongodb.authentication-database: admin}")
    private String mongoAuthDB;

    @Value("${spring.data.mongodb.grid-fs-database:}")
    private String gridFS;

    @Value("${mongodb.socket.timeout}")
    private Integer socketTimeout;

    @Value("${mongodb.connection.timeout}")
    private Integer connectionTimeout;

    @Value("${mongodb.minHeartbeatFrequency}")
    private Integer minHeartbeatFrequency;

    @Value("${mongodb.cluster}")
    private String mongoCluster;

    @Value("${mongodb.server.selection.timeout}")
    private Integer serverSelectionTimeout;

    public String getMongoDB() {
        return mongoDB;
    }

    public void setMongoDB(String mongoDB) {
        this.mongoDB = mongoDB;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMongoPassword() {
        return mongoPassword;
    }

    public void setMongoPassword(String mongoPassword) {
        this.mongoPassword = mongoPassword;
    }

    public Integer getMongoPort() {
        return mongoPort;
    }

    public void setMongoPort(Integer mongoPort) {
        this.mongoPort = mongoPort;
    }

    public String getMongoAuthDB() {
        return mongoAuthDB;
    }

    public void setMongoAuthDB(String mongoAuthDB) {
        this.mongoAuthDB = mongoAuthDB;
    }

    public String getGridFS() {
        return gridFS;
    }

    public void setGridFS(String gridFS) {
        this.gridFS = gridFS;
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public String getMongoCluster() {
        return mongoCluster;
    }

    public void setMongoCluster(String mongoCluster) {
        this.mongoCluster = mongoCluster;
    }

    public Integer getMinHeartbeatFrequency() {
        return minHeartbeatFrequency;
    }

    public void setMinHeartbeatFrequency(Integer minHeartbeatFrequency) {
        this.minHeartbeatFrequency = minHeartbeatFrequency;
    }

    public Integer getServerSelectionTimeout() {
        return serverSelectionTimeout;
    }

    public void setServerSelectionTimeout(Integer serverSelectionTimeout) {
        this.serverSelectionTimeout = serverSelectionTimeout;
    }

}