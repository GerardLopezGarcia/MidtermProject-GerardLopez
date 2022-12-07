package com.ironhack.service.impl;

import com.ironhack.model.Savings;
import com.ironhack.repository.SavingsRepository;
import com.ironhack.service.interfaces.ISavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SavingsService implements ISavingsService {
    @Autowired
    SavingsRepository savingsRepository;
    //GET
    public List<Savings> getAllSavingsAccounts() {
        return savingsRepository.findAll();
    }
    public Savings getMySavingsAccount(Integer id) {

        Optional<Savings>optionalSavings = savingsRepository.findById(id);
        validateEmptyAccount(optionalSavings);
        LocalDate creationDate = optionalSavings.get().getCreationDate();
        LocalDate lastTimeInterestApplied = optionalSavings.get().getLastTimeInterestApplied();
        LocalDate now = LocalDate.now();
        long daysElapsed = java.time.temporal.ChronoUnit.DAYS.between(now,creationDate);
//      para testing añadir esta condición en el if(now.getYear()-creationDate.getYear()>0)    daysElapsed == 365
        if(daysElapsed == 365){
            //interes * balance
            //aplicar metodo en la clase
            if(lastTimeInterestApplied.getDayOfMonth() != now.getDayOfMonth()){
                BigDecimal interestToAdd = optionalSavings.get().getBalance().getAmount().multiply(optionalSavings.get().getInterestRate());
                optionalSavings.get().getBalance().increaseAmount(interestToAdd);
                System.out.println("Interes añadido");
                optionalSavings.get().setLastTimeInterestApplied(now);
                System.out.println("Fecha de aplicación de interés actualizada");
                savingsRepository.save(optionalSavings.get());
                return optionalSavings.get();
            }else {
                System.out.println("El interés ya ha sido aplicado con anterioridad");
                return optionalSavings.get();
            }

        }
        return optionalSavings.get();
    }
    public void validateEmptyAccount(Optional<Savings> account){
        if(account.isEmpty())throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cuenta no encontrada");
    }
}
