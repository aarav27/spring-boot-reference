package org.sharma.aarav.spring.boot.reference.data.provider;

import org.sharma.aarav.spring.boot.reference.data.repository.ReactiveEnrolleeRepository;
import org.sharma.aarav.spring.boot.reference.data.model.Enrollee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReactiveEnrolleeProvider {

    @Autowired
    private ReactiveEnrolleeRepository reactiveEnrolleeRepository;

    public List<Enrollee> getAllEnrollees() {
        return reactiveEnrolleeRepository.findAll().collectList().block();
    }

    public Enrollee getEnrolleeByEnrolleeId(String enrolleeId) {
        return reactiveEnrolleeRepository.findByEnrolleeId(enrolleeId).block();
    }

    public Enrollee getEnrolleeByPersonId(String personId) {
        return reactiveEnrolleeRepository.findByPersonId(personId).block();
    }

    public List<Enrollee> getEnrolleeByEnrolleeName(String enrolleeName) {
        return reactiveEnrolleeRepository.findByEnrolleeName(enrolleeName).collectList().block();
    }

    public void createEnrollee(String enrolleeId, String personId, String enrolleeName) {
        Enrollee enrollee = new Enrollee(enrolleeId, personId, enrolleeName);
        reactiveEnrolleeRepository.save(enrollee).block();
    }

    public Enrollee updateEnrollee(String enrolleeId, String personId, String enrolleeName) {
        Enrollee enrollee = reactiveEnrolleeRepository.findByEnrolleeId(enrolleeId).block();
        if(enrollee!=null) {
            enrollee.setPersonId(personId);
            enrollee.setEnrolleeName(enrolleeName);
            return reactiveEnrolleeRepository.save(enrollee).block();
        }
        return null;
    }

    public void deleteEnrollee(String enrolleeId) {
        Enrollee enrollee = reactiveEnrolleeRepository.findByEnrolleeId(enrolleeId).block();
        if (enrollee!=null) reactiveEnrolleeRepository.delete(enrollee).block();
    }

}