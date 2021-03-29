package org.sharma.aarav.spring.boot.reference.controller;

import org.sharma.aarav.spring.boot.reference.service.EnrolleeService;
import org.sharma.aarav.spring.boot.reference.dto.Enrollee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Aarav Sharma
 */
@RestController
@RequestMapping(value = "/enrollees", produces = "application/json")
public class EnrolleeController {

    @Autowired
    private EnrolleeService enrolleeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Enrollee>> getAllEnrollees(@RequestParam(name="name", required = false) String name) {
        List<Enrollee> enrolless = name != null ? enrolleeService.getEnrolleeByEnrolleeName(name) : enrolleeService.getAllEnrollees();
        return new ResponseEntity<> (enrolless, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Enrollee> getEnrolleeByEnrolleeId(@PathVariable("id") String enrolleeId) {
        return new ResponseEntity<>(enrolleeService.getEnrolleeByEnrolleeId(enrolleeId), HttpStatus.OK);
    }

    @GetMapping("/persons/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Enrollee> getEnrolleeByPersonId(@PathVariable("id") String personId) {
        return new ResponseEntity<>(enrolleeService.getEnrolleeByPersonId(personId), HttpStatus.OK);
    }

    @PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addEnrollee(@RequestBody Enrollee enrollee) {
        enrolleeService.addEnrollee(enrollee.getEnrolleeId(), enrollee.getPersonId(), enrollee.getEnrolleeName());
    }

    @PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Enrollee> updateEnrollee(@RequestBody Enrollee enrollee) {
        Enrollee enrolleeDto = enrolleeService.updateEnrollee(enrollee.getEnrolleeId(), enrollee.getPersonId(), enrollee.getEnrolleeName());
        ResponseEntity responseEntity = enrolleeDto !=null ? new ResponseEntity<>(enrolleeDto, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEnrolleeByEnrolleeId(@PathVariable("id") String enrolleeId) {
        enrolleeService.deleteEnrollee(enrolleeId);
    }
}