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

//@CrossOrigin(origins = "https://hma-frontend-aalawy3.herokuapp.com")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")

public class BloodPressureValueController {

    @Autowired
    BloodPressureValueRepository bloodPressureValueRepository;

    @Autowired
    PatientRepository patientRepository;


    @GetMapping("/bloodPressureValues")
	public ResponseEntity<List<BloodPressureValue>> getAllBloodPressureValues(@RequestParam String uid) {
		System.out.println("Getting all blood pressure values...");
		try {

			ArrayList<BloodPressureValue> bloodPressureValues = new ArrayList<BloodPressureValue>();
			
			
            //bloodPressureValueRepository.findAll().forEach(bloodPressureValues::add);
			bloodPressureValueRepository.getUserBloodPressureValues(uid).forEach(bloodPressureValues::add);
    

			if (bloodPressureValues.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			

			return new ResponseEntity<>(bloodPressureValues, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @PostMapping("/bloodPressureValues")
	public ResponseEntity<BloodPressureValue> createBloodPressureValue(@RequestParam String uid, @RequestParam Integer systolic, @RequestParam Integer diastolic, @RequestParam Date dateMeasured) {
		System.out.print("Creating a new blood pressure value...");
		try {

            Patient patient = patientRepository.findById(uid).orElseThrow(null);

			
            BloodPressureValue bloodPressureValue = new BloodPressureValue();

            bloodPressureValue.setPatient(patient);
            bloodPressureValue.setSystolic(systolic);
            bloodPressureValue.setDiastolic(diastolic);
			bloodPressureValue.setDateMeasured(dateMeasured);
       
            bloodPressureValueRepository.save(bloodPressureValue);
			System.out.println("New Blood Pressure Value Created");


			// HmaPatient _HmaPatient = hmaPatientRepository
			// 		.save(new HmaPatient());
			return new ResponseEntity<BloodPressureValue>(bloodPressureValue, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/bloodPressureAverage")
	public ResponseEntity<BloodPressureValue> getBloodPressureAverage(@RequestParam String uid) {
		System.out.println("Getting average blood pressure values...");
		try {

			ArrayList<BloodPressureValue> bloodPressureValues = new ArrayList<BloodPressureValue>();
			
			
            //bloodPressureValueRepository.findAll().forEach(bloodPressureValues::add);
			bloodPressureValueRepository.getUserBloodPressureValues(uid).forEach(bloodPressureValues::add);


			if (bloodPressureValues.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			ArrayList<Integer> allSystolic = new ArrayList<>();
			ArrayList<Integer> allDiastolic = new ArrayList<>();


			for (BloodPressureValue bloodPressureValue : bloodPressureValues) {
				allSystolic.add(bloodPressureValue.getSystolic());
				allDiastolic.add(bloodPressureValue.getDiastolic());
			}

			
			BloodPressureValue averageBloodPressure = new BloodPressureValue();
			
			int averageSystolic = (int) allSystolic.stream()
                .mapToInt(d -> d)
                .average()
                .orElse(0);

			int averageDiastolic = (int) allDiastolic.stream()
                .mapToInt(d -> d)
                .average()
                .orElse(0);

			averageBloodPressure.setSystolic(averageSystolic);
			averageBloodPressure.setDiastolic(averageDiastolic);

			return new ResponseEntity<>(averageBloodPressure, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
}
