package com.ironhack.service.interfaces;

import com.ironhack.controller.dto.TransferDTO;
import com.ironhack.model.Account;
import com.ironhack.model.Checking;

import java.math.BigDecimal;
import java.util.List;

public interface ICheckingService {
    void saveCheckingAccount(Checking checking);

    List<Account> getMyAccountsByOwner(String name, String password);

    void transferMoney(TransferDTO transferDTO);
}
