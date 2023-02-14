package com.aalawy.hma.controller;


import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import net.bytebuddy.asm.Advice.Local;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")

public class DietController {

    @Autowired
    DietRepository dietRepository;

    @Autowired
    PatientRepository patientRepository;


    @GetMapping("/diets")
	public ResponseEntity<List<Diet>> getAllDiets(@RequestParam String uid) {
		System.out.println("Getting all diets...");
		try {

			ArrayList<Diet> diets = new ArrayList<Diet>();
			
			
			dietRepository.getUserDiets(uid).forEach(diets::add);
    

			if (diets.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
            
			return new ResponseEntity<>(diets, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @PostMapping("/diets")
	public ResponseEntity<Diet> createDiet(@RequestParam String uid, @RequestParam String servingType, @RequestParam Number servingDailyAmount, @RequestParam String servingDetails) {
		System.out.print("Creating a new diet...");
		try {

            Patient patient = patientRepository.findById(uid).orElseThrow(null);

			
            Diet diet = new Diet();


            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            // LocalDateTime temp = LocalDateTime.parse(appointmentTime,formatter);
            //Date newDate = Date.valueOf(temp);

            // System.out.println(temp);


            // System.out.println(newDate);


            diet.setPatient(patient);
            diet.setServingType(servingType);
            diet.setServingDailyAmount(servingDailyAmount);
			diet.setServingDetails(servingDetails);
       
            dietRepository.save(diet);
			System.out.println("New Diet Created");


			// HmaPatient _HmaPatient = hmaPatientRepository
			// 		.save(new HmaPatient());
			return new ResponseEntity<Diet>(diet, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
    
}
