package org.sharma.aarav.spring.boot.reference.data.model;

/**
 * @author Aarav Sharma
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Enrollee {

    @Id
    private String enrolleeId;

    private String personId;

    private String enrolleeName;

    public Enrollee() {}

    public Enrollee(String enrolleeId, String personId, String enrolleeName) {
        this.enrolleeId=enrolleeId;
        this.personId= personId;
        this.enrolleeName=enrolleeName;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getEnrolleeId() {
        return enrolleeId;
    }

    public void setEnrolleeId(String enrolleeId) {
        this.enrolleeId = enrolleeId;
    }

    public String getEnrolleeName() {
        return enrolleeName;
    }

    public void setEnrolleeName(String enrolleeName) {
        this.enrolleeName = enrolleeName;
    }
}