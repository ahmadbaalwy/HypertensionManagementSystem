package com.aalawy.hma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.aalawy.hma.model.*;

public interface AppointmentRepository extends JpaRepository<Appointment, String>, JpaSpecificationExecutor {

  @Query(
        value="select * from appointments where uid =?1",
        nativeQuery = true
    )
  List<Appointment> getUserAppointments(String uid);

  }