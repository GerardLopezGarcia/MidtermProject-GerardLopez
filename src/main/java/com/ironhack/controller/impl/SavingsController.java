package com.ironhack.controller.impl;

import com.ironhack.controller.interfaces.ISavingsController;
import com.ironhack.model.Savings;
import com.ironhack.repository.SavingsRepository;
import com.ironhack.service.impl.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SavingsController implements ISavingsController {
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    SavingsService savingsService;

    @GetMapping("/savings")
    @ResponseStatus(HttpStatus.OK)
    public List<Savings> getAllSavingsAccounts(){
        return savingsService.getAllSavingsAccounts();
    }
    @GetMapping("/mysavingsaccount/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Savings getMySavingsAccount(@PathVariable(name = "id")Integer id){
        return savingsService.getMySavingsAccount(id);
    }
}
