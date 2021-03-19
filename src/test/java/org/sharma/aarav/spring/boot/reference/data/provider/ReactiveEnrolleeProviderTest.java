package org.sharma.aarav.spring.boot.reference.data.provider;

import org.sharma.aarav.spring.boot.reference.data.model.Enrollee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Aarav Sharma
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ReactiveEnrolleeProvider.class})
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class , MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableReactiveMongoRepositories(basePackages = "org.sharma.aarav.spring.boot.reference.data")
@AutoConfigureAfter(EmbeddedMongoAutoConfiguration.class)
public class ReactiveEnrolleeProviderTest {

    @Autowired
    private ReactiveEnrolleeProvider reactiveEnrolleeProvider;

    @Before
    public void setUp() {
        reactiveEnrolleeProvider.createEnrollee("1", "101", "George Washington");
        reactiveEnrolleeProvider.createEnrollee("2", "201", "John Adams");
        reactiveEnrolleeProvider.createEnrollee("3", "301", "Thomas Jefferson");
        testCreateEnrollee();
    }

    @Test
    public void testGetAllEnrollees() {
        List<Enrollee> enrollees = reactiveEnrolleeProvider.getAllEnrollees();
        assertNotNull(enrollees);
        assertEquals(4, enrollees.size());
    }

    @Test
    public void testGetEnrolleeByEnrolleeId() {
        Enrollee enrollee = reactiveEnrolleeProvider.getEnrolleeByEnrolleeId("1");
        assertNotNull(enrollee);
        assertEquals("1", enrollee.getEnrolleeId());
        assertEquals("101", enrollee.getPersonId());
        assertEquals("George Washington", enrollee.getEnrolleeName());
    }

    @Test
    public void testGetEnrolleeByPersonId() {
        Enrollee enrollee = reactiveEnrolleeProvider.getEnrolleeByPersonId("201");
        assertNotNull(enrollee);
        assertEquals("2", enrollee.getEnrolleeId());
        assertEquals("201", enrollee.getPersonId());
        assertEquals("John Adams", enrollee.getEnrolleeName());
    }

    @Test
    public void testGetEnrolleeByEnrolleeName() {
        List<Enrollee> enrollees = reactiveEnrolleeProvider.getEnrolleeByEnrolleeName("Thomas Jefferson");
        assertNotNull(enrollees);
        assertEquals(2, enrollees.size());
        assertEquals(enrollees.get(0).getEnrolleeName(), enrollees.get(1).getEnrolleeName());
    }

    @Test
    public void testCreateEnrollee() {
        reactiveEnrolleeProvider.createEnrollee("4", "401", "Thomas Jefferson");
        List<Enrollee> enrollees = reactiveEnrolleeProvider.getAllEnrollees();
        assertNotNull(enrollees);
        assertEquals(4, enrollees.size());
    }

    @Test
    public void testUpdateEnrollee() {
        Enrollee enrollee = reactiveEnrolleeProvider.getEnrolleeByEnrolleeId("4");
        assertNotNull(enrollee);
        assertEquals("Thomas Jefferson", enrollee.getEnrolleeName());
        Enrollee updatedEnrollee = reactiveEnrolleeProvider.updateEnrollee(enrollee.getEnrolleeId(), enrollee.getPersonId(), "James Madison");
        assertNotNull(updatedEnrollee);
        assertEquals("James Madison", updatedEnrollee.getEnrolleeName());
    }

    @Test
    public void testDeleteEnrollee() {
        Enrollee enrollee = reactiveEnrolleeProvider.getEnrolleeByEnrolleeId("4");
        assertNotNull(enrollee);
        reactiveEnrolleeProvider.deleteEnrollee(enrollee.getEnrolleeId());
        List<Enrollee> enrollees = reactiveEnrolleeProvider.getAllEnrollees();
        assertNotNull(enrollees);
        assertEquals(3, enrollees.size());
    }

}