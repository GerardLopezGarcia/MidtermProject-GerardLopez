package com.ironhack.service.impl;

import com.ironhack.model.Employee;
import com.ironhack.model.Patient;
import com.ironhack.repository.PatientRepository;
import com.ironhack.service.interfaces.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IPatientService {
    @Autowired
    PatientRepository patientRepository;

    public List<Patient> getPatientsByDateWithinRange(Date date1, Optional<Date> date2) {
        if(date2.isPresent())return patientRepository.findByDateOfBirthBetween(date1,date2);
        return patientRepository.findByDateOfBirth(date1);
    }

    //PUT
    public void updatePatient(Patient patient, Integer id) {
        Optional<Patient>optionalPatient = patientRepository.findById(id);
        if(optionalPatient.isEmpty())return;
        patient.setId(id);
        patientRepository.save(patient);
    }


    public void deleteCourse(Integer id) {
        Optional<Patient>optionalPatient = patientRepository.findById(id);
        if(optionalPatient.isEmpty())throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The patient doesn't exist");
        patientRepository.deleteById(id);
    }
}
