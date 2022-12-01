package com.ironhack.repository;

import com.ironhack.model.Patient;
import com.ironhack.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {
    List<Patient> findByDateOfBirthBetween(Date date1, Optional<Date> date2);
    List<Patient> findByDateOfBirth(Date date1);
    List<Patient> findByEmployeeStatus(Status status);
    List<Patient> findByEmployeeDepartment(String department);
}
