package com.ironhack.service.impl;

import com.ironhack.controller.dto.UserDTO;
import com.ironhack.model.*;
import com.ironhack.repository.AccountRepository;
import com.ironhack.repository.CheckingRepository;
import com.ironhack.repository.StudentCheckingRepository;
import com.ironhack.repository.UserReposiroty;
import com.ironhack.service.interfaces.ICheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.ironhack.model.Constants.ANSI_CYAN;
import static com.ironhack.model.Constants.ANSI_RESET;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CheckingService implements ICheckingService {

    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserReposiroty userReposiroty;


    public void saveCheckingAccount(Checking checking) {
      Integer accountHolderYear = checking.getPrimaryOwner().getDateOfBirth().getYear();
      Integer actualYear = LocalDate.now().getYear();
      Integer accountHolderAge = actualYear - accountHolderYear;

      if(accountHolderAge>=24) {
          checkingRepository.save(checking);
          System.out.println(ANSI_CYAN + "Edad del Usuario : "+ accountHolderAge + " ,cuenta bancaria creada" + ANSI_RESET);
      }
      //if the primaryOwner is less than 24, a StudentChecking account should be created
      Optional<AccountHolder> optionalsecondaryOwner = Optional.ofNullable(checking.getSecondaryOwner());
      if(optionalsecondaryOwner.isEmpty()){
          StudentChecking studentChecking = new StudentChecking(checking.getBalance(),checking.getPrimaryOwner(),checking.getCreationDate(),checking.getPenaltyFee(),checking.getSecretKey(),checking.getStatus());
          studentCheckingRepository.save(studentChecking);
          System.out.println(ANSI_CYAN + "Edad del Usuario : "+ accountHolderAge + " ,cuenta Joven creada" + ANSI_RESET);
      }else{
          StudentChecking studentChecking = new StudentChecking(checking.getBalance(),checking.getPrimaryOwner(),checking.getSecondaryOwner(),checking.getCreationDate(),checking.getPenaltyFee(),checking.getSecretKey(),checking.getStatus());
          studentCheckingRepository.save(studentChecking);
          System.out.println(ANSI_CYAN + "Edad del Usuario : "+ accountHolderAge + " ,cuenta Joven creada" + ANSI_RESET);
      }
    }


    public List<Account> getMyAccountsByOwner(String name, String password) {
        /*User findById(name) Optional
        if(user.passwrd == password parameter -> return data*/
        Optional<User> optionalUser = userReposiroty.findByName(name);
        if(optionalUser.isEmpty())throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        if(optionalUser.get().getPassword().equals(password)){
            List<Account> accountList = new ArrayList<>();
            accountList.addAll(accountRepository.findAllByPrimaryOwnerName(name));
            accountList.addAll(accountRepository.findAllBySecondaryOwnerName(name));
            return accountList;
        }else{
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "contraseña incorrecta");
        }
    }


    public void transferMoney(UserDTO userDTO, Integer senderId, BigDecimal amount, Integer receiverId) {
        //el usuario tiene que especificar que cuenta hará la transacción, pasar un nombre y contraseña para asegurar que es propietario,
        // id sender + amount y otro id de la cuenta a la que transferir los fondos
    }


}
