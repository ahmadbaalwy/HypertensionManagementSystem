package com.aalawy.hma.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Table(name = "diets")
@Entity
public class Diet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uid")
    @JsonBackReference
    @NotBlank
    private Patient patient;

    String servingType;

    Number servingDailyAmount;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getServingType() {
        return servingType;
    }

    public void setServingType(String servingType) {
        this.servingType = servingType;
    }

    public Number getServingDailyAmount() {
        return servingDailyAmount;
    }

    public void setServingDailyAmount(Number servingDailyAmount) {
        this.servingDailyAmount = servingDailyAmount;
    }

    public String getServingDetails() {
        return servingDetails;
    }

    public void setServingDetails(String servingDetails) {
        this.servingDetails = servingDetails;
    }

    String servingDetails;

    public Diet() {
    }


    
}
