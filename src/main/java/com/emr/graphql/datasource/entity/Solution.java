package com.emr.graphql.datasource.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "solution")
public class Solution {

    @Id
    private UUID id;
    @CreationTimestamp
    private LocalDateTime createdDateTime;
    private String title;

    @ManyToOne
    @JoinColumn(name = "solution_created_by", nullable = false)
    private OfficeAssistant solutionCreatedBy;

    @ManyToOne
    @JoinColumn(name = "enquiry_id", nullable = false)
    private Enquiry patientEnquiry;

    public OfficeAssistant getSolutionCreatedBy() {
        return solutionCreatedBy;
    }

    public void setSolutionCreatedBy(OfficeAssistant solutionCreatedBy) {
        this.solutionCreatedBy = solutionCreatedBy;
    }

    public Enquiry getPatientEnquiry() {
        return patientEnquiry;
    }

    public void setPatientEnquiry(Enquiry patientEnquiry) {
        this.patientEnquiry = patientEnquiry;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
}
