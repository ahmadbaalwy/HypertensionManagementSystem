package com.aalawy.hma.controller;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aalawy.hma.model.*;
import com.aalawy.hma.repository.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")

public class MedicationController {

    @Autowired
    MedicationRepository medicationRepository;

    @Autowired
    PatientRepository patientRepository;


    @GetMapping("/medications")
	public ResponseEntity<List<Medication>> getAllMedications(@RequestParam String uid) {
		System.out.println("Getting all medications...");
		try {

			ArrayList<Medication> medications = new ArrayList<Medication>();
			
			
            //bloodPressureValueRepository.findAll().forEach(bloodPressureValues::add);
			medicationRepository.getUserMedications(uid).forEach(medications::add);
    

			if (medications.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			

			return new ResponseEntity<>(medications, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @PostMapping("/medications")
	public ResponseEntity<Medication> createMedication(@RequestParam String uid, @RequestParam String medicationName, @RequestParam String notes, @RequestParam Date prescribedDate, @RequestParam String isActive) {
		System.out.print("Creating a new medication...");
		try {

            Patient patient = patientRepository.findById(uid).orElseThrow(null);

			
            Medication medication = new Medication();

            medication.setPatient(patient);
            medication.setMedicationName(medicationName);
            medication.setNotes(notes);
			medication.setPrescribedDate(prescribedDate);
			medication.setIsActive(isActive);
       
            medicationRepository.save(medication);
			System.out.println("New Medication Created");


			// HmaPatient _HmaPatient = hmaPatientRepository
			// 		.save(new HmaPatient());
			return new ResponseEntity<Medication>(medication, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
    
}
