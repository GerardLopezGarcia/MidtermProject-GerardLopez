package com.ironhack.controller.interfaces;

import com.ironhack.model.CreditCard;
import com.ironhack.model.Savings;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

public interface ICreditCardController {
    List<CreditCard> getAllCreditCardAccounts();
    CreditCard getMyCreditCardAccount(@PathVariable(name = "id")Integer id, Principal user);
}
