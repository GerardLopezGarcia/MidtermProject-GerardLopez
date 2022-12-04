package com.ironhack.controller.impl;

import com.ironhack.controller.interfaces.IAccountController;
import com.ironhack.model.Account;
import com.ironhack.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController implements IAccountController {
    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }
}
