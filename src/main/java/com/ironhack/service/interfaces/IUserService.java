package com.ironhack.service.interfaces;

import java.math.BigDecimal;

public interface IUserService {
     void deleteAccountHolder(String name);

    void deleteAdmin(String name);

    void deleteThirdPartyUser(String name);

    void updateAccountBalance(BigDecimal amount, Integer id);
}
