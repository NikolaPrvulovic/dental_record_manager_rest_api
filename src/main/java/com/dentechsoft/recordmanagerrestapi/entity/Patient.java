package com.dentechsoft.recordmanagerrestapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "patient")
public class Patient {

    // define the fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    // ** set up mapping to PatientDetail, Prosthetist, Dentist Entity
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_detail_id")
    @JsonManagedReference(value = "patientDetailToPatient")
    private PatientDetail patientDetail;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "prosthetist_id")
    @JsonBackReference(value = "patientToProsthetist")
    private Prosthetist prosthetist;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "dentist_id")
    @JsonBackReference(value = "patientToDentist")
    private Dentist dentist;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tooth_status_id")
    @JsonManagedReference(value = "toothStatusToPatient")
    private ToothStatus toothStatus;

    // create constructors

    public Patient() {
    }

    public Patient(String firstName, String lastName, String phoneNumber,
                   PatientDetail patientDetail, Prosthetist prosthetist,
                   Dentist dentist, ToothStatus toothStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.patientDetail = patientDetail;
        this.prosthetist = prosthetist;
        this.dentist = dentist;
        this.toothStatus = toothStatus;
    }

    // generate getter/setter methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PatientDetail getPatientDetail() {
        return patientDetail;
    }

    public void setPatientDetail(PatientDetail patientDetail) {
        this.patientDetail = patientDetail;
    }

    public Prosthetist getProsthetist() {
        return prosthetist;
    }

    public void setProsthetist(Prosthetist prosthetist) {
        this.prosthetist = prosthetist;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    public ToothStatus getToothStatus() {
        return toothStatus;
    }

    public void setToothStatus(ToothStatus toothStatus) {
        this.toothStatus = toothStatus;
    }

// generate toString() method

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
