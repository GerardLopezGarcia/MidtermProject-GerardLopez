package com.ironhack.controller.interfaces;

import com.ironhack.model.AccountHolder;
import com.ironhack.model.Admin;
import com.ironhack.model.ThirdParty;
import com.ironhack.model.User;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IUsersController {
    public List<AccountHolder> getAccounts();
    public List<User> getAllUsers();
    public List<Admin> getAllAdmins();
    public List<ThirdParty> getAllThirdPartyUsers();
    public void deleteAccountHolder(String name);
}
