package com.emr.graphql.datasource.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "enquiry")
public class Enquiry {

    @Id
    private UUID id;
    @CreationTimestamp
    private LocalDateTime createdDateTime;
    private String title;
    private String message;



    public Patient getEnquiryCreatedBy() {
        return enquiryCreatedBy;
    }

    public void setEnquiryCreatedBy(Patient enquiryCreatedBy) {
        this.enquiryCreatedBy = enquiryCreatedBy;
    }

    @OneToOne
    @JoinColumn(name = "enquiry_created_by",nullable = false)
    private Patient enquiryCreatedBy;

    @OneToOne
    @JoinColumn(name = "solution")
    private Solution solution;
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
}
