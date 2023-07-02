package com.emr.graphql.datasource.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    private UUID patiendId;
    private String patientName;
    private Integer medicalRecordNumber;
    private String DOB;
    private String address;
    private String gender;
    private String email;
    private String contactNumber;
    private String primary;
    private String preferredPharmacy;
    private String hashed_password;
    @CreationTimestamp
    private LocalDateTime create_time;
    @CreationTimestamp
    private LocalDate dateProfileCreated;

    public UUID getPatiendId() {
        return patiendId;
    }

    public void setPatiendId(UUID patiendId) {
        this.patiendId = patiendId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    public void setMedicalRecordNumber(Integer medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getPreferredPharmacy() {
        return preferredPharmacy;
    }

    public void setPreferredPharmacy(String preferredPharmacy) {
        this.preferredPharmacy = preferredPharmacy;
    }

    public String getHashed_password() {
        return hashed_password;
    }

    public void setHashed_password(String hashed_password) {
        this.hashed_password = hashed_password;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public LocalDate getDateProfileCreated() {
        return dateProfileCreated;
    }

    public void setDateProfileCreated(LocalDate dateProfileCreated) {
        this.dateProfileCreated = dateProfileCreated;
    }
}
