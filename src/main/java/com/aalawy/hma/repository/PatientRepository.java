package com.aalawy.hma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aalawy.hma.model.*;

public interface PatientRepository extends JpaRepository<Patient, String> {

  }