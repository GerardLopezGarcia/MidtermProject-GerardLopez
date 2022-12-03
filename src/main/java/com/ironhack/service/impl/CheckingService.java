package com.ironhack.service.impl;

import com.ironhack.model.AccountHolder;
import com.ironhack.model.Checking;
import com.ironhack.model.StudentChecking;
import com.ironhack.repository.CheckingRepository;
import com.ironhack.repository.StudentCheckingRepository;
import com.ironhack.service.interfaces.ICheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CheckingService implements ICheckingService {

    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;


    public void saveCheckingAccount(Checking checking) {
      Integer accountHolderYear = checking.getPrimaryOwner().getDateOfBirth().getYear();
      Integer actualYear = LocalDate.now().getYear();
      Integer accountHolderAge = actualYear - accountHolderYear;

      if(accountHolderAge>=24) checkingRepository.save(checking);
      //if the primaryOwner is less than 24, a StudentChecking account should be created
      Optional<AccountHolder> optionalsecondaryOwner = Optional.ofNullable(checking.getSecondaryOwner());
      if(optionalsecondaryOwner.isEmpty()){
          StudentChecking studentChecking = new StudentChecking(checking.getBalance(),checking.getPrimaryOwner(),checking.getCreationDate(),checking.getPenaltyFee(),checking.getSecretKey(),checking.getStatus());
          studentCheckingRepository.save(studentChecking);
      }else{
          StudentChecking studentChecking = new StudentChecking(checking.getBalance(),checking.getPrimaryOwner(),checking.getSecondaryOwner(),checking.getCreationDate(),checking.getPenaltyFee(),checking.getSecretKey(),checking.getStatus());
          studentCheckingRepository.save(studentChecking);
      }
    }
}
