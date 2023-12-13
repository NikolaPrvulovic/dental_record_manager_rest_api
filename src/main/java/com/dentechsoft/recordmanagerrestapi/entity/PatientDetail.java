package com.dentechsoft.recordmanagerrestapi.entity;

import com.dentechsoft.recordmanagerrestapi.enums.ProstheticStatus;
import com.dentechsoft.recordmanagerrestapi.enums.RestorationType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "patient_detail")
public class PatientDetail {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // add restoration type attribute
    @Enumerated(EnumType.STRING)
    @Column(name = "restoration_type")
    private RestorationType restorationType;

    // add status attribute
    @Enumerated(EnumType.STRING)
    @Column(name = "prosthetic_status")
    private ProstheticStatus prostheticStatus;

    @OneToOne(mappedBy = "patientDetail",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonBackReference(value = "patientDetailToPatient")
    private Patient patient;

    // constructors

    public PatientDetail() {
    }


    // getters/setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RestorationType getRestorationType() {
        return restorationType;
    }

    public void setRestorationType(RestorationType restorationType) {
        this.restorationType = restorationType;
    }

    public ProstheticStatus getProstheticStatus() {
        return prostheticStatus;
    }

    public void setProstheticStatus(ProstheticStatus prostheticStatus) {
        this.prostheticStatus = prostheticStatus;
    }

}
