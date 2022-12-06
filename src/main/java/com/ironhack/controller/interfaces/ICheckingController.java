package com.ironhack.controller.interfaces;

import com.ironhack.controller.dto.TransferDTO;
import com.ironhack.model.Account;
import com.ironhack.model.Checking;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ICheckingController {
    void saveCheckingAccount( Checking checking);
    List<Account> getMyAccountsByOwner(String name, String password);
    void transferMoney(TransferDTO transferDTO);
    public void deleteChecking(Integer id);
}
