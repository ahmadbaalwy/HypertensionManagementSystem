package com.aalawy.hma.model;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hl7.fhir.r4.model.Observation;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Table(name = "bloodPressureValues")
@Entity
public class BloodPressureValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uid")
    @JsonBackReference
    @NotBlank
    private Patient patient;
    
    Integer systolic;

    public BloodPressureValue() {
    }

    Integer diastolic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Integer getSystolic() {
        return systolic;
    }

    public void setSystolic(Integer systolic) {
        this.systolic = systolic;
    }

    public Integer getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(Integer diastolic) {
        this.diastolic = diastolic;
    }

    public Date getDateMeasured() {
        return dateMeasured;
    }

    public void setDateMeasured(Date dateMeasured) {
        this.dateMeasured = dateMeasured;
    }

    Date dateMeasured;


    
    
}
