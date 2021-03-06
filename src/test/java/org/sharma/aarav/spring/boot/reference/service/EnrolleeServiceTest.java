package org.sharma.aarav.spring.boot.reference.service;

import org.sharma.aarav.spring.boot.reference.dto.Enrollee;
import org.sharma.aarav.spring.boot.reference.data.provider.ReactiveEnrolleeProvider;
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

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {EnrolleeService.class, ReactiveEnrolleeProvider.class})
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class , MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableReactiveMongoRepositories(basePackages = "org.sharma.aarav.spring.boot.reference.data")
@AutoConfigureAfter(EmbeddedMongoAutoConfiguration.class)
public class EnrolleeServiceTest {

    @Autowired
    private EnrolleeService enrolleeService;

    @Before
    public void setUp() {
        enrolleeService.addEnrollee("1", "101", "George Washington");
        enrolleeService.addEnrollee("2", "201", "John Adams");
        enrolleeService.addEnrollee("3", "301", "Thomas Jefferson");
        testCreateEnrollee();
    }

    @Test
    public void testGetAllEnrollees() {
        List<Enrollee> enrollees = enrolleeService.getAllEnrollees();
        assertNotNull(enrollees);
        assertEquals(4, enrollees.size());
    }

    @Test
    public void testGetEnrolleeByEnrolleeId() {
        Enrollee enrollee = enrolleeService.getEnrolleeByEnrolleeId("1");
        assertNotNull(enrollee);
        assertEquals("1", enrollee.getEnrolleeId());
        assertEquals("101", enrollee.getPersonId());
        assertEquals("George Washington", enrollee.getEnrolleeName());
    }

    @Test
    public void testGetEnrolleeByPersonId() {
        Enrollee enrollee = enrolleeService.getEnrolleeByPersonId("201");
        assertNotNull(enrollee);
        assertEquals("2", enrollee.getEnrolleeId());
        assertEquals("201", enrollee.getPersonId());
        assertEquals("John Adams", enrollee.getEnrolleeName());
    }

    @Test
    public void testGetEnrolleeByEnrolleeName() {
        List<Enrollee> enrollees = enrolleeService.getEnrolleeByEnrolleeName("Thomas Jefferson");
        assertNotNull(enrollees);
        assertEquals(2, enrollees.size());
        assertEquals(enrollees.get(0).getEnrolleeName(), enrollees.get(1).getEnrolleeName());
    }

    @Test
    public void testCreateEnrollee() {
        enrolleeService.addEnrollee("4", "401", "Thomas Jefferson");
        List<Enrollee> enrollees = enrolleeService.getAllEnrollees();
        assertNotNull(enrollees);
        assertEquals(4, enrollees.size());
    }

    @Test
    public void testUpdateEnrollee() {
        Enrollee enrollee = enrolleeService.getEnrolleeByEnrolleeId("4");
        assertNotNull(enrollee);
        assertEquals("Thomas Jefferson", enrollee.getEnrolleeName());
        Enrollee updatedEnrollee = enrolleeService.updateEnrollee(enrollee.getEnrolleeId(), enrollee.getPersonId(), "James Madison");
        assertNotNull(updatedEnrollee);
        assertEquals("James Madison", updatedEnrollee.getEnrolleeName());
    }

    @Test
    public void testDeleteEnrollee() {
        Enrollee enrollee = enrolleeService.getEnrolleeByEnrolleeId("4");
        assertNotNull(enrollee);
        enrolleeService.deleteEnrollee(enrollee.getEnrolleeId());
        List<Enrollee> enrollees = enrolleeService.getAllEnrollees();
        assertNotNull(enrollees);
        assertEquals(3, enrollees.size());
    }

}