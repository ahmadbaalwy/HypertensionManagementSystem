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

public class AppointmentController {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    PatientRepository patientRepository;


    @GetMapping("/appointments")
	public ResponseEntity<List<Appointment>> getAllAppointments(@RequestParam String uid) {
		System.out.println("Getting all appointments...");
		try {

			ArrayList<Appointment> appointments = new ArrayList<Appointment>();
			
			
			appointmentRepository.getUserAppointments(uid).forEach(appointments::add);
    

			if (appointments.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
            for (Appointment appointmenTimestamp : appointments) {
                System.out.println(appointmenTimestamp.getAppointmentTime());
            }
			return new ResponseEntity<>(appointments, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @PostMapping("/appointments")
	public ResponseEntity<Appointment> createAppointment(@RequestParam String uid, @RequestParam String doctorName, @RequestParam String location, @RequestParam Timestamp appointmentTime) {
		System.out.print("Creating a new appointment...");
		try {

            Patient patient = patientRepository.findById(uid).orElseThrow(null);

			
            Appointment appointment = new Appointment();

            System.out.println(appointmentTime);

            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            // LocalDateTime temp = LocalDateTime.parse(appointmentTime,formatter);
            //Date newDate = Date.valueOf(temp);

            // System.out.println(temp);


            // System.out.println(newDate);


            appointment.setPatient(patient);
            appointment.setDoctorName(doctorName);
            appointment.setLocation(location);
			appointment.setAppointmentTime(appointmentTime);
       
            appointmentRepository.save(appointment);
			System.out.println("New Appointment Created");


			// HmaPatient _HmaPatient = hmaPatientRepository
			// 		.save(new HmaPatient());
			return new ResponseEntity<Appointment>(appointment, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
    
}
