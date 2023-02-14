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

public class ExerciseController {

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    PatientRepository patientRepository;


    @GetMapping("/exercises")
	public ResponseEntity<List<Exercise>> getAllExercises(@RequestParam String uid) {
		System.out.println("Getting all exercises...");
		try {

			ArrayList<Exercise> exercises = new ArrayList<Exercise>();
			
			
			exerciseRepository.getUserExercises(uid).forEach(exercises::add);
    

			if (exercises.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
            
			return new ResponseEntity<>(exercises, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @PostMapping("/exercises")
	public ResponseEntity<Exercise> createExercise(@RequestParam String uid, @RequestParam String exerciseType, @RequestParam Number exerciseDailyAmount, @RequestParam String exerciseDetails) {
		System.out.print("Creating a new exercise...");
		try {

            Patient patient = patientRepository.findById(uid).orElseThrow(null);

			
            Exercise exercise = new Exercise();


            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            // LocalDateTime temp = LocalDateTime.parse(appointmentTime,formatter);
            //Date newDate = Date.valueOf(temp);

            // System.out.println(temp);


            // System.out.println(newDate);


            exercise.setPatient(patient);
            exercise.setExerciseType(exerciseType);
            exercise.setExerciseDailyAmount(exerciseDailyAmount);
			exercise.setExerciseDetails(exerciseDetails);
       
            exerciseRepository.save(exercise);
			System.out.println("New Exercise Created");


			// HmaPatient _HmaPatient = hmaPatientRepository
			// 		.save(new HmaPatient());
			return new ResponseEntity<Exercise>(exercise, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
    
}
