package com.ironhack.service.impl;

import com.ironhack.model.*;
import com.ironhack.repository.*;
import com.ironhack.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    @Autowired
    AccountRepository accountRepository;

    public void deleteAccountHolder(String name) {
        Optional<AccountHolder> optionalAccountHolder = accountHolderRepository.findById(name);
        if(optionalAccountHolder.isEmpty())throw new ResponseStatusException(HttpStatus.NOT_FOUND,"La cuenta asociada a este nombre no existe");
        accountHolderRepository.deleteById(name);

    }

    public void deleteAdmin(String name) {
        Optional<Admin> optionalAdmin = adminRepository.findById(name);
        if(optionalAdmin.isEmpty())throw new ResponseStatusException(HttpStatus.NOT_FOUND,"La cuenta asociada a este nombre no existe");
        adminRepository.deleteById(name);

    }


    public void deleteThirdPartyUser(String name) {
        Optional<ThirdParty> optionalThirdParty = thirdPartyRepository.findById(name);
        if(optionalThirdParty.isEmpty())throw new ResponseStatusException(HttpStatus.NOT_FOUND,"La cuenta asociada a este nombre no existe");
        thirdPartyRepository.deleteById(name);
    }

    //PATCH
    public void updateAccountBalance(BigDecimal amount, Integer id) {
        Optional<Account> optAccount = accountRepository.findById(id);
        if(optAccount.isEmpty())throw new ResponseStatusException(HttpStatus.NOT_FOUND,"la cuenta introducida no existe");
        Account account = optAccount.get();
        Money balanceAmount = new Money(amount);
        account.setBalance(balanceAmount);
        accountRepository.save(account);
    }
}
