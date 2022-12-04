package com.ironhack.controller.impl;

import com.ironhack.controller.dto.UserDTO;
import com.ironhack.controller.interfaces.ICheckingController;
import com.ironhack.model.Account;
import com.ironhack.model.Checking;
import com.ironhack.repository.AccountRepository;
import com.ironhack.repository.CheckingRepository;
import com.ironhack.service.impl.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CheckingController implements ICheckingController {
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    CheckingService checkingService;

    @Autowired
    AccountRepository accountRepository;

    //GET
    @GetMapping("/checkings")
    @ResponseStatus(HttpStatus.OK)
    public List<Checking> getAll(){
        return checkingRepository.findAll();
    }


    @GetMapping("/myaccounts/{name}/{password}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getMyAccountsByOwner(@PathVariable(name = "name") String name,@PathVariable(name = "password") String password){
        return checkingService.getMyAccountsByOwner(name, password);
    }
    //POST
    @PostMapping("/checkings")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void saveCheckingAccount(@RequestBody Checking checking){
        checkingService.saveCheckingAccount(checking);
    }

    //PUT transfers
    @PatchMapping("/transfer/{sender_id}/{amount}/{receiver_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transferMoney(@RequestBody UserDTO userDTO, @PathVariable (name = "sender_id") Integer senderId, @PathVariable (name = "amount") BigDecimal amount, @PathVariable (name = "receiver_id") Integer receiverId){
        checkingService.transferMoney(userDTO,senderId,amount,receiverId);
    }
}
