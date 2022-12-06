package com.ironhack.service.impl;

import com.ironhack.controller.dto.TransferDTO;
import com.ironhack.model.*;
import com.ironhack.repository.*;
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
    @Autowired
    SavingsRepository savingsRepository;


    public void saveCheckingAccount(Checking checking) {
      Integer accountHolderYear = checking.getPrimaryOwner().getDateOfBirth().getYear();
      Integer actualYear = LocalDate.now().getYear();
      Integer accountHolderAge = actualYear - accountHolderYear;

      if(accountHolderAge>=24) {
          checkingRepository.save(checking);
          System.out.println(ANSI_CYAN + "Edad del Usuario : "+ accountHolderAge + " ,cuenta bancaria creada" + ANSI_RESET);
      }else{
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
    }

    //spring security context @autentication!
    public List<Account> getMyAccountsByOwner(String name, String password) {

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


    public void transferMoney(TransferDTO transferDTO) {
        /*El usuario tiene que especificar que cuenta hará la transacción, pasar un nombre y contraseña para asegurar que es propietario,
        id sender + amount y otro id de la cuenta a la que transferir los fondos*/

        //comprobación de que tanto sender como receiver existen
        Optional<Account> optionalAccount = accountRepository.findById(transferDTO.getSenderId());
        Optional<Account> optionalReceiverAccount = accountRepository.findById(transferDTO.getReceiverId());
        validateEmptyAccount(optionalAccount,transferDTO);
        validateEmptyAccount(optionalAccount,transferDTO);

        String primaryOwnerName = optionalAccount.get().getPrimaryOwner().getName();
        String secondaryOwnerName = optionalAccount.get().getSecondaryOwner() ==null? "" : optionalAccount.get().getSecondaryOwner().getName();

        //verificación de correspondencia entre ID cuenta y propietario
        if(primaryOwnerName.equals(transferDTO.getName())){
            //verificación de contraseña
            if(optionalAccount.get().getPrimaryOwner().getPassword().equals(transferDTO.getPassword())){
                /*mirar fondos - hacer transacción - mirar si balance < balancemínimo para penalty - comprobar fraude */
                if (optionalAccount.get().getBalance().getAmount().compareTo(transferDTO.getAmount()) >=0){
                    //se realiza la transacción
                    transferFunds(optionalAccount,optionalReceiverAccount,transferDTO);

                }else throw new  ResponseStatusException(HttpStatus.FORBIDDEN, "Fondos insuficientes");
            }else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Contraseña incorrecta");

        } else if (secondaryOwnerName.equals(transferDTO.getName())) {
            //se repite el proceso para el secondary Owner
            //verificación de contraseña
            if(optionalAccount.get().getSecondaryOwner().getPassword().equals(transferDTO.getPassword())){
                /*mirar fondos - hacer transacción - mirar si balance < balancemínimo para penalty - comprobar fraude */
                if (optionalReceiverAccount.get().getBalance().getAmount().compareTo(BigDecimal.ZERO) >0){
                    //se realiza la transacción
                    transferFunds(optionalAccount,optionalReceiverAccount,transferDTO);
                }else throw new  ResponseStatusException(HttpStatus.FORBIDDEN, "Fondos insuficientes");
            }else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Contraseña incorrecta");

        }else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "El número de cuenta no corresponde con el propietario");
        }

    }



    public void transferFunds(Optional<Account> senderAccount,Optional<Account> receiverAccount,TransferDTO transferDTO){

        senderAccount.get().getBalance().decreaseAmount(transferDTO.getAmount());
        receiverAccount.get().getBalance().increaseAmount(transferDTO.getAmount());

        accountRepository.save(senderAccount.get());
        accountRepository.save(receiverAccount.get());
        System.out.println(senderAccount.get());
        System.out.println(receiverAccount.get());
        System.out.println("Transferencia realizada mostrando datos del estado actual: ");

        //comprobación balance -penalty
        penaltyValidation(senderAccount,transferDTO);

        System.out.println("balance actual de tu cuenta : " + senderAccount.get().getBalance().getAmount() +"\n"+
                "balance de la cuenta del destinatario : " + receiverAccount.get().getBalance().getAmount());
    }

    public void penaltyValidation(Optional<Account> account,TransferDTO transferDTO){
        Optional<Checking> optionalChecking= checkingRepository.findById(transferDTO.getSenderId());

        if(optionalChecking.isEmpty()){

            //comparo con Saving
            Optional<Savings> optionalSaving = savingsRepository.findById(transferDTO.getSenderId());
            BigDecimal minimumBalance = optionalSaving.get().getMinimumBalance();
            BigDecimal penalty = account.get().getPenaltyFee();
            if(optionalSaving.get().getBalance().getAmount().compareTo(minimumBalance) < 0){
                //Se aplica la penalty ya que balance < balance mínimo
                account.get().getBalance().decreaseAmount(penalty);
                System.out.println("Se ha aplicado una tarifa "+ penalty + " debido a que el balance es inferior al balance mínimo permitido");
            }
        }else{
            //comparo con Checking
            BigDecimal minimumBalance = optionalChecking.get().getMinimumBalance();
            BigDecimal penalty = account.get().getPenaltyFee();
            if(optionalChecking.get().getBalance().getAmount().compareTo(minimumBalance) < 0){
                //Se aplica la penalty ya que balance < balance mínimo
                account.get().getBalance().decreaseAmount(penalty);
                System.out.println("Se ha aplicado una tarifa "+ penalty + " debido a que el balance es inferior al balance mínimo permitido");
            }
        }
    }

    public void validateEmptyAccount(Optional<Account> account,TransferDTO transferDTO){
        if(account.isEmpty())throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cuenta de destino con id: " + transferDTO.getReceiverId() + " no encontrada");
    }


    public void deleteChecking(Integer id) {
        Optional<Checking> optionalChecking = checkingRepository.findById(id);
        if(optionalChecking.isEmpty())throw new ResponseStatusException(HttpStatus.NOT_FOUND,"La cuenta asociada a este id no existe");
        checkingRepository.deleteById(id);
    }
}
