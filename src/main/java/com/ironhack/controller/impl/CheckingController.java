package com.ironhack.controller.impl;

import com.ironhack.controller.dto.TransferDTO;
import com.ironhack.controller.interfaces.ICheckingController;
import com.ironhack.model.Account;
import com.ironhack.model.Checking;
import com.ironhack.repository.AccountRepository;
import com.ironhack.repository.CheckingRepository;
import com.ironhack.service.impl.CheckingService;
import com.ironhack.service.impl.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CheckingController implements ICheckingController {
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    CheckingService checkingService;

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransferService transferService;

    //GET
    @GetMapping("/checkings")
    @ResponseStatus(HttpStatus.OK)
    public List<Checking> getAll(){
        return checkingRepository.findAll();
    }

    //spring security context @autentication!
    @GetMapping("/myaccounts/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getMyAccountsByOwner(@PathVariable(name = "name") String name){
        return checkingService.getMyAccountsByOwner(name);
    }
    //POST
    @PostMapping("/checkings")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void saveCheckingAccount(@RequestBody @Valid Checking checking){
        checkingService.saveCheckingAccount(checking);
    }


    //DELETE checking
    @DeleteMapping("/checkings/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChecking(@PathVariable Integer id){
        checkingService.deleteChecking(id);
    }

}
