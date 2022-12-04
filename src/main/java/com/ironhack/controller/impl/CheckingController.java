package com.ironhack.controller.impl;

import com.ironhack.controller.interfaces.ICheckingController;
import com.ironhack.model.Checking;
import com.ironhack.repository.CheckingRepository;
import com.ironhack.service.impl.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CheckingController implements ICheckingController {
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    CheckingService checkingService;

    @GetMapping("/checkings")
    public List<Checking> getAll(){
        return checkingRepository.findAll();
    }
    //POST
    @PostMapping("/checkings")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void saveCheckingAccount(@RequestBody Checking checking){
        checkingService.saveCheckingAccount(checking);
    }
}
