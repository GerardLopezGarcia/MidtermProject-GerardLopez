package com.ironhack.controller.impl;

import com.ironhack.controller.Interfaces.IPatientController;
import com.ironhack.model.Patient;
import com.ironhack.model.Status;
import com.ironhack.repository.PatientRepository;
import com.ironhack.service.impl.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class PatientController implements IPatientController {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PatientService patientService;

    @GetMapping("/patients")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getPatients(){
        return patientRepository.findAll();
    }
    @GetMapping("/patients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient getPatientById(@PathVariable(name = "id")Integer id) {
        return patientRepository.findById(id).get();
    }

    @GetMapping("/patients/date-range")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getPatientsByDateWithinRange(@RequestParam Date date1,@RequestParam Optional<Date> date2){
        return patientService.getPatientsByDateWithinRange(date1, date2);
    }
    @GetMapping("/patients/admittedby-status")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getPatientsWithDocStatusOff(@RequestParam (defaultValue = "OFF")Status status){
        return patientRepository.findByEmployeeStatus(status);
    }

    @GetMapping("/patients/department")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getPatientsWithDepartment(@RequestParam (defaultValue = "cardiology")String department){
        return patientRepository.findByEmployeeDepartment(department);
    }
    //post
    @PostMapping("/patients")
    @ResponseStatus(HttpStatus.CREATED)
    public void savePatient(@RequestBody Patient patient){
        patientRepository.save(patient);
    }

    //Put
    @PutMapping("/patients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePatient(@RequestBody Patient patient, @PathVariable Integer id){
        patientService.updatePatient(patient,id);
    }

    //DELETE
    @DeleteMapping("/patients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable Integer id){
        patientService.deleteCourse(id);
    }



}
