package org.sharma.aarav.spring.boot.reference.service;

import org.sharma.aarav.spring.boot.reference.data.model.Enrollee;
import org.sharma.aarav.spring.boot.reference.data.provider.ReactiveEnrolleeProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.text.MessageFormat.format;

@Service
public class EnrolleeService {

    Logger logger = LoggerFactory.getLogger(EnrolleeService.class);

    @Autowired
    ReactiveEnrolleeProvider reactiveEnrolleeProvider;

    public List<org.sharma.aarav.spring.boot.reference.dto.Enrollee> getAllEnrollees() {
        return convert(reactiveEnrolleeProvider.getAllEnrollees());
    }

    public org.sharma.aarav.spring.boot.reference.dto.Enrollee getEnrolleeByEnrolleeId(String enrolleeId) {
        org.sharma.aarav.spring.boot.reference.dto.Enrollee enrollee = null;
        try {
            enrollee = convert(reactiveEnrolleeProvider.getEnrolleeByEnrolleeId(enrolleeId));
        } catch (Exception ex) {
            logger.error(format("Unable to retrieve enrollee detail for the given enrollee-id: {0}", enrolleeId, ex));
        }
        return enrollee;
    }

    public org.sharma.aarav.spring.boot.reference.dto.Enrollee getEnrolleeByPersonId(String personId) {
        return convert(reactiveEnrolleeProvider.getEnrolleeByPersonId(personId));
    }

    public List<org.sharma.aarav.spring.boot.reference.dto.Enrollee> getEnrolleeByEnrolleeName(String enrolleeName) {
        return convert(reactiveEnrolleeProvider.getEnrolleeByEnrolleeName(enrolleeName));
    }

    public void addEnrollee(String enrolleeId, String personId, String enrolleeName) {
        reactiveEnrolleeProvider.createEnrollee(enrolleeId, personId, enrolleeName);
    }

    public org.sharma.aarav.spring.boot.reference.dto.Enrollee updateEnrollee(String enrolleeId, String personId, String enrolleeName) {
        return convert(reactiveEnrolleeProvider.updateEnrollee(enrolleeId, personId, enrolleeName));
    }

    public void deleteEnrollee(String enrolleeId) {
        reactiveEnrolleeProvider.deleteEnrollee(enrolleeId);
    }

    private List<org.sharma.aarav.spring.boot.reference.dto.Enrollee> convert(List<Enrollee> enrollees) {
        List<org.sharma.aarav.spring.boot.reference.dto.Enrollee> enrollesDTO = enrollees.stream().map(enrollee -> {
            org.sharma.aarav.spring.boot.reference.dto.Enrollee enrolleDTO = new org.sharma.aarav.spring.boot.reference.dto.Enrollee();
            enrolleDTO.setEnrolleeId(enrollee.getEnrolleeId());
            enrolleDTO.setPersonId(enrollee.getPersonId());
            enrolleDTO.setEnrolleeName(enrollee.getEnrolleeName());
            return enrolleDTO;
        }).collect(Collectors.toList());
        return enrollesDTO;
    }

    private org.sharma.aarav.spring.boot.reference.dto.Enrollee convert(Enrollee enrollee) {
        org.sharma.aarav.spring.boot.reference.dto.Enrollee enrolleeDTO = new org.sharma.aarav.spring.boot.reference.dto.Enrollee();
        enrolleeDTO.setEnrolleeId(enrollee.getEnrolleeId());
        enrolleeDTO.setPersonId(enrollee.getPersonId());
        enrolleeDTO.setEnrolleeName(enrollee.getEnrolleeName());
        return enrolleeDTO;
    }

}