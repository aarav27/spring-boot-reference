package org.sharma.aarav.spring.boot.reference.controller;

import org.sharma.aarav.spring.boot.reference.service.EnrolleeService;
import org.sharma.aarav.spring.boot.reference.dto.Enrollee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/enrollees", produces = "application/json")
public class EnrolleeController {

    @Autowired
    private EnrolleeService enrolleeService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Enrollee>> getAllEnrollees() {
        return new ResponseEntity<>(enrolleeService.getAllEnrollees(), HttpStatus.OK);
    }

    @GetMapping("/{enrolleeId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Enrollee> getEnrolleeByEnrolleeId(@PathVariable("enrolleeId") String enrolleeId) {
        return new ResponseEntity<>(enrolleeService.getEnrolleeByEnrolleeId(enrolleeId), HttpStatus.OK);
    }

    @GetMapping("/{personId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Enrollee> getEnrolleeByPersonId(@PathVariable("personId") String personId) {
        return new ResponseEntity<>(enrolleeService.getEnrolleeByPersonId(personId), HttpStatus.OK);
    }

    @GetMapping("/{enrolleeName}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Enrollee>> getEnrolleeByEnrollee(@PathVariable("enrolleeName") String enrolleeName) {
        return new ResponseEntity<>(enrolleeService.getEnrolleeByEnrolleeName(enrolleeName), HttpStatus.OK);
    }

    @PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addEnrollee(@RequestBody Enrollee enrollee) {
        enrolleeService.addEnrollee(enrollee.getEnrolleeId(), enrollee.getPersonId(), enrollee.getEnrolleeName());
    }

    @PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Enrollee> updateEnrollee(@RequestBody Enrollee enrollee) {
        return new ResponseEntity<>(enrolleeService.updateEnrollee(enrollee.getEnrolleeId(), enrollee.getPersonId(), enrollee.getEnrolleeName()), HttpStatus.OK);
    }

    @DeleteMapping("/{enrolleeId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEnrolleeByEnrolleeId(@PathVariable("enrolleeId") String enrolleeId) {
        enrolleeService.deleteEnrollee(enrolleeId);
    }
}