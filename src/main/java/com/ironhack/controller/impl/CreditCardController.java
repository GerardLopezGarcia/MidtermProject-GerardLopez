package com.ironhack.controller.impl;

import com.ironhack.controller.interfaces.ICreditCardController;
import com.ironhack.model.CreditCard;
import com.ironhack.model.Savings;
import com.ironhack.repository.CreditCardRepository;
import com.ironhack.service.impl.CreditCardService;
import com.ironhack.service.impl.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class CreditCardController implements ICreditCardController {
    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    CreditCardService creditCardService;

    @GetMapping("/creditcards")
    @ResponseStatus(HttpStatus.OK)
    public List<CreditCard> getAllCreditCardAccounts(){
        return creditCardService.getAllCreditCardAccounts();
    }
    @GetMapping("/mycreditaccount/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CreditCard getMyCreditCardAccount(@PathVariable(name = "id")Integer id, Principal user){
        return creditCardService.getMyCreditCardAccount(id,user.getName());
    }
}
