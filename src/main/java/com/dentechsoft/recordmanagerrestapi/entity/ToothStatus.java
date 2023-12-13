package com.dentechsoft.recordmanagerrestapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tooth_status")
public class ToothStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "teeth_position")
    private String teethPosition;

    @Column(name = "teeth_for_restoration")
    @NotNull
    @Min(value = 1)
    @Max(value = 32)
    private int teethForRestoration;

    @Column(name = "teeth_color")
    private String teethColor;

    // set up mapping for Patient Entity
    @OneToOne(mappedBy = "toothStatus",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonBackReference(value = "toothStatusToPatient")
    private Patient patient;

    // constructors

    public ToothStatus() {
    }

    public ToothStatus(String positions, int teethForRestoration, String teethColor, Patient patient) {
        this.teethPosition = positions;
        this.teethForRestoration = teethForRestoration;
        this.teethColor = teethColor;
        this.patient = patient;
    }

    // getters/setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeethPositions() {
        return teethPosition;
    }

    public void setTeethPositions(String teethPosition) {
        this.teethPosition = teethPosition;
    }



    public int getTeethForRestoration() {
        return teethForRestoration;
    }

    public void setTeethForRestoration(int teethForRestoration) {
        this.teethForRestoration = teethForRestoration;
    }

    public String getTeethColor() {
        return teethColor;
    }

    public void setTeethColor(String teethColor) {
        this.teethColor = teethColor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    // toString

    @Override
    public String toString() {
        return "ToothStatus{" +
                "id=" + id +
                ", positions=" + this.teethPosition +
                ", teethForRestoration=" + teethForRestoration +
                ", teethColor='" + teethColor + '\'' +
                ", patient=" + patient +
                '}';
    }
}
