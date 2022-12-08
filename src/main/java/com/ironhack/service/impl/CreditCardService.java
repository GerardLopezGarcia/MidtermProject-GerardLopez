package com.ironhack.service.impl;

import com.ironhack.model.*;
import com.ironhack.repository.AccountRepository;
import com.ironhack.repository.CreditCardRepository;
import com.ironhack.repository.UserRepository;
import com.ironhack.service.interfaces.ICreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CreditCardService implements ICreditCardService {
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;

    public List<CreditCard> getAllCreditCardAccounts() {
        return creditCardRepository.findAll();
    }


    public CreditCard getMyCreditCardAccount(Integer id,String name) {

        Optional<User> optionalUser = userRepository.findByName(name);
        String role = optionalUser.get().getRole();
        Optional<Account> optionalAccount = accountRepository.findById(id);
        String secondary = null;
        String primary =null;

        AccountHolder primaryOwner = optionalAccount.get().getPrimaryOwner();
        if(primaryOwner != null) primary = primaryOwner.getName();

        AccountHolder secondaryOwner = optionalAccount.get().getSecondaryOwner();
        if(secondaryOwner != null) secondary = secondaryOwner.getName();

        if(role.equals("ADMIN")){
            return returnCreditAccount(id);
        } else {
            assert primary != null;
            if (primary.equals(name) || Objects.equals(secondary, name)) {
                return returnCreditAccount(id);
            }else{
                throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Solo pueden acceder los propietarios de la cuenta o los administradores");
            }
        }

    }

    public CreditCard returnCreditAccount(Integer id){

        Optional<CreditCard>optionalCreditCard = creditCardRepository.findById(id);
        LocalDate lastTimeInterestApplied = optionalCreditCard.get().getLastTimeInterestApplied();
        validateEmptyAccount(optionalCreditCard);
        LocalDate creationDate = optionalCreditCard.get().getCreationDate();
        LocalDate now = LocalDate.now();
        long daysElapsed = java.time.temporal.ChronoUnit.DAYS.between(now,creationDate);
//      para testing añadir esta condición en el if(now.getYear()-creationDate.getYear()>0)
        if(daysElapsed > 28 && daysElapsed < 31){

            //interes * balance
            if(lastTimeInterestApplied.getDayOfMonth() != now.getDayOfMonth()){
                BigDecimal interestToAdd = optionalCreditCard.get().getBalance().getAmount().multiply(optionalCreditCard.get().getInterestRate());
                optionalCreditCard.get().getBalance().increaseAmount(interestToAdd);
                System.out.println("Interes añadido");
                optionalCreditCard.get().setLastTimeInterestApplied(now);
                System.out.println("Fecha de aplicación de interés actualizada");
                creditCardRepository.save(optionalCreditCard.get());
                return optionalCreditCard.get();
            }else {
                System.out.println("El interés ya ha sido aplicado con anterioridad");
                return optionalCreditCard.get();
            }
        }
        return optionalCreditCard.get();
    }
    public void validateEmptyAccount(Optional<CreditCard> account){
        if(account.isEmpty())throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cuenta no encontrada");
    }
}
