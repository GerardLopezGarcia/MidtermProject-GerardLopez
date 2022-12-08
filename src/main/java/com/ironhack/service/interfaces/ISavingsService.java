package com.ironhack.service.interfaces;

import com.ironhack.model.Savings;

import java.util.List;

public interface ISavingsService {
    List<Savings> getAllSavingsAccounts();

    Savings getMySavingsAccount(Integer id,String name);
}
