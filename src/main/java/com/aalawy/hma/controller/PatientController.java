package com.aalawy.hma.controller;

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

//@CrossOrigin(origins = "https://hma-frontend-aalawy3.herokuapp.com")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PatientController {

    @Autowired
	PatientRepository hmaPatientRepository;

    @GetMapping("/patients")
	public ResponseEntity<List<Patient>> getAllPatients() {
		System.out.println("Getting all patients...");
		try {

			ArrayList<Patient> hmaPatients = new ArrayList<Patient>();
			
			
            hmaPatientRepository.findAll().forEach(hmaPatients::add);
    

			if (hmaPatients.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			

			return new ResponseEntity<>(hmaPatients, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @PostMapping("/patients")
	public ResponseEntity<Patient> createPatient(@RequestParam String id, @RequestParam String firstName, @RequestParam String lastName) {
		System.out.print("Creatin a new patient...");
		try {
			
            Patient _HmaPatient = new Patient();
            _HmaPatient.setUid(id);
            _HmaPatient.setFirstName(firstName);
			_HmaPatient.setLastName(lastName);
            hmaPatientRepository.save(_HmaPatient);
			System.out.println("New User Created");


			// HmaPatient _HmaPatient = hmaPatientRepository
			// 		.save(new HmaPatient());
			return new ResponseEntity<Patient>(_HmaPatient, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/patients")
	public ResponseEntity<HttpStatus> deletePatient(@RequestParam String id) {
		try {
			hmaPatientRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



    
}
