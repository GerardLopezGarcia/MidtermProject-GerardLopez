package com.ironhack.service.interfaces;

import com.ironhack.model.CreditCard;
import com.ironhack.model.Savings;

import java.util.List;

public interface ICreditCardService {

    List<CreditCard> getAllCreditCardAccounts();

    CreditCard getMyCreditCardAccount(Integer id, String name);
    CreditCard returnCreditAccount(Integer id);
}
