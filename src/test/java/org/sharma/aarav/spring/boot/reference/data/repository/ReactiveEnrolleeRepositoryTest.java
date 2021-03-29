package org.sharma.aarav.spring.boot.reference.data.repository;

/**
 * @author Aarav Sharma
 */

import org.sharma.aarav.spring.boot.reference.data.config.EmbeddedReactiveMongoConfig;
import org.sharma.aarav.spring.boot.reference.data.model.Enrollee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Aarav Sharma
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {EmbeddedReactiveMongoConfig.class})
public class ReactiveEnrolleeRepositoryTest {

    @Autowired
    private ReactiveEnrolleeRepository reactiveEnrolleeRepository;

    @Autowired
    private ReactiveMongoOperations operations;

    @Before
    public void setUp() {
        operations.collectionExists(Enrollee.class)
                .flatMap(exists -> exists ? operations.dropCollection(Enrollee.class) : Mono.just(exists))
                .flatMap(o -> operations.createCollection(Enrollee.class))
                .then()
                .block();

        reactiveEnrolleeRepository.saveAll(Flux.just(new Enrollee("1", "101", "George Washington"),
                new Enrollee("2", "201", "John Adams"),
                new Enrollee("3", "301", "Thomas Jefferson"),
                new Enrollee("4", "401", "Thomas Jefferson"))).then().block();
    }

    @Test
    public void testFindByEnrolleeId() {
        Mono<Enrollee> monoEnrollee = reactiveEnrolleeRepository.findByEnrolleeId("1");

        StepVerifier.create(monoEnrollee).assertNext(enrollee -> {
            assertEquals("1", enrollee.getEnrolleeId());
            assertEquals("101" , enrollee.getPersonId());
            assertEquals("George Washington", enrollee.getEnrolleeName());
        }).expectComplete().verify();
    }

    @Test
    public void testFindByPersonId() {
        Mono<Enrollee> monoEnrollee = reactiveEnrolleeRepository.findByPersonId("201");

        StepVerifier.create(monoEnrollee).assertNext(enrollee -> {
            assertEquals("2", enrollee.getEnrolleeId());
            assertEquals("201" , enrollee.getPersonId());
            assertEquals("John Adams", enrollee.getEnrolleeName());
        }).expectComplete().verify();
    }

    @Test
    public void testFindByEnrolleeName() {
        List<Enrollee> enrollees = reactiveEnrolleeRepository.findByEnrolleeName("Thomas Jefferson").collectList().block();
        assertEquals(2, enrollees.size());
    }

}
