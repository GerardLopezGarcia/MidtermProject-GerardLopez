package com.ironhack.service.impl;

import com.ironhack.model.AccountHolder;
import com.ironhack.model.Admin;
import com.ironhack.model.Checking;
import com.ironhack.model.ThirdParty;
import com.ironhack.repository.AccountHolderRepository;
import com.ironhack.repository.AdminRepository;
import com.ironhack.repository.ThirdPartyRepository;
import com.ironhack.repository.UserReposiroty;
import com.ironhack.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    UserReposiroty userReposiroty;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    public void deleteAccountHolder(String name) {
        Optional<AccountHolder> optionalAccountHolder = accountHolderRepository.findById(name);
        if(optionalAccountHolder.isEmpty())throw new ResponseStatusException(HttpStatus.NOT_FOUND,"La cuenta asociada a este nombre no existe");
        accountHolderRepository.deleteById(name);
        userReposiroty.deleteById(name);
    }

    public void deleteAdmin(String name) {
        Optional<Admin> optionalAdmin = adminRepository.findById(name);
        if(optionalAdmin.isEmpty())throw new ResponseStatusException(HttpStatus.NOT_FOUND,"La cuenta asociada a este nombre no existe");
        adminRepository.deleteById(name);
        userReposiroty.deleteById(name);
    }

    @Override
    public void deleteThirdPartyUser(String name) {
        Optional<ThirdParty> optionalThirdParty = thirdPartyRepository.findById(name);
        if(optionalThirdParty.isEmpty())throw new ResponseStatusException(HttpStatus.NOT_FOUND,"La cuenta asociada a este nombre no existe");
        thirdPartyRepository.deleteById(name);
        userReposiroty.deleteById(name);
    }
}
