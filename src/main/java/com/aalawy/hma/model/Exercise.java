package com.aalawy.hma.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Table(name = "exercises")
@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uid")
    @JsonBackReference
    @NotBlank
    private Patient patient;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public Number getExerciseDailyAmount() {
        return exerciseDailyAmount;
    }

    public void setExerciseDailyAmount(Number exerciseDailyAmount) {
        this.exerciseDailyAmount = exerciseDailyAmount;
    }

    public String getExerciseDetails() {
        return exerciseDetails;
    }

    public void setExerciseDetails(String exerciseDetails) {
        this.exerciseDetails = exerciseDetails;
    }

    public Exercise() {
    }

    String exerciseType;

    Number exerciseDailyAmount;

    String exerciseDetails;
}
