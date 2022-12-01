package com.ironhack.controller.Interfaces;

import com.ironhack.model.Patient;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IPatientController {
    Patient getPatientById(Integer id);
    List<Patient> getPatientsByDateWithinRange(Date date1, Optional<Date> date2);

    //POST
    void savePatient(Patient patient);

    //PUT
    void updatePatient(Patient patient, Integer id);
}
