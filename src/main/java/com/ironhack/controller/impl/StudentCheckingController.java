package com.ironhack.controller.impl;

import com.ironhack.controller.interfaces.IStudentCheckingController;
import com.ironhack.model.StudentChecking;
import com.ironhack.repository.CheckingRepository;
import com.ironhack.repository.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentCheckingController implements IStudentCheckingController {

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    @GetMapping("/studentcheckings")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentChecking> getAllStudentCheckings(){
        return studentCheckingRepository.findAll();
    }
}
