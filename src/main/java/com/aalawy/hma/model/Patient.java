package com.aalawy.hma.model;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.AttributeAccessor;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;

import javax.persistence.*;

@Table(name = "patients")
@Entity
public class Patient  {
       
    @Id
	private String uid;

    String firstName;

    String lastName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "patient")
    @JsonManagedReference
    Set<BloodPressureValue> observations;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "patient")
    @JsonManagedReference
    Set<Medication> medications;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "patient")
    @JsonManagedReference
    Set<Appointment> appointments;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "patient")
    @JsonManagedReference
    Set<Diet> diets;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "patient")
    @JsonManagedReference
    Set<Exercise> exercises;

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

    public Patient() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    

    
    
}
