package com.ironhack.service.interfaces;

import com.ironhack.model.Patient;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IPatientService {
    List<Patient> getPatientsByDateWithinRange(Date date1, Optional<Date> date2);

    //PUT
    void updatePatient(Patient patient, Integer id);

    void deleteCourse(Integer id);
}
