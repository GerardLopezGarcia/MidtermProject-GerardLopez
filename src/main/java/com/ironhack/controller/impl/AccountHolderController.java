package com.ironhack.controller.impl;

import com.ironhack.model.AccountHolder;
import com.ironhack.repository.AccountHolderRepository;
import com.ironhack.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountHolderController {
    @Autowired
    AccountHolderRepository accountHolderRepository;

    @GetMapping("/accountholders")
    public List<AccountHolder> getAccounts(){
        return accountHolderRepository.findAll();
    }
}
