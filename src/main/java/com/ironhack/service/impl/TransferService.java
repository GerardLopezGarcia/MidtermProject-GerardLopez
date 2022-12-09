package com.ironhack.service.impl;

import com.ironhack.controller.dto.TransferDTO;
import com.ironhack.model.*;
import com.ironhack.repository.*;
import com.ironhack.service.interfaces.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransferService implements ITransferService {
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    public void transferMoney(TransferDTO transferDTO) {
        /*El usuario tiene que especificar que cuenta hará la transacción, pasar un nombre y contraseña para asegurar que es propietario,
        id sender + amount y otro id de la cuenta a la que transferir los fondos*/

        //comprobación de que tanto sender como receiver existen
        Optional<Account> optionalAccount = accountRepository.findById(transferDTO.getSenderId());
        Optional<Account> optionalReceiverAccount = accountRepository.findById(transferDTO.getReceiverId());
        validateEmptyAccount(optionalAccount,transferDTO);
        validateEmptyAccount(optionalReceiverAccount,transferDTO);

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

                }else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Fondos insuficientes");
            }else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Contraseña incorrecta");

        } else if (secondaryOwnerName.equals(transferDTO.getName())) {
            //se repite el proceso para el secondary Owner
            //verificación de contraseña
            if(optionalAccount.get().getSecondaryOwner().getPassword().equals(transferDTO.getPassword())){
                /*mirar fondos - hacer transacción - mirar si balance < balancemínimo para penalty - comprobar fraude */
                if (optionalAccount.get().getBalance().getAmount().compareTo(transferDTO.getAmount()) >=0){
                    //se realiza la transacción
                    transferFunds(optionalAccount,optionalReceiverAccount,transferDTO);
                }else throw new  ResponseStatusException(HttpStatus.FORBIDDEN, "Fondos insuficientes");
            }else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Contraseña incorrecta");

        }else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "El número de cuenta no corresponde con el propietario");
        }

    }

    public void retrieveMoney(TransferDTO transferDTO) {

        Optional<Account> optionalAccount = accountRepository.findById(transferDTO.getSenderId());
        validateEmptyAccount(optionalAccount,transferDTO);

        String primaryOwnerName = optionalAccount.get().getPrimaryOwner().getName();
        String secondaryOwnerName = optionalAccount.get().getSecondaryOwner() ==null? "" : optionalAccount.get().getSecondaryOwner().getName();

        //verificación de correspondencia entre ID cuenta y propietario
        if(primaryOwnerName.equals(transferDTO.getName())){
            //verificación de contraseña
            if(optionalAccount.get().getPrimaryOwner().getPassword().equals(transferDTO.getPassword())){
                /*mirar fondos - hacer transacción - mirar si balance < balancemínimo para penalty - comprobar fraude */
                if (optionalAccount.get().getBalance().getAmount().compareTo(transferDTO.getAmount()) >=0){
                    //se realiza la extracción de dinero
                    moneyExtraction(optionalAccount,transferDTO);

                }else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Fondos insuficientes");
            }else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Contraseña incorrecta");

        } else if (secondaryOwnerName.equals(transferDTO.getName())) {
            //se repite el proceso para el secondary Owner
            //verificación de contraseña
            if(optionalAccount.get().getSecondaryOwner().getPassword().equals(transferDTO.getPassword())){
                /*mirar fondos - hacer transacción - mirar si balance < balancemínimo para penalty - comprobar fraude */
                if (optionalAccount.get().getBalance().getAmount().compareTo(transferDTO.getAmount()) >=0){
                    //se realiza la extracción de dinero
                    moneyExtraction(optionalAccount,transferDTO);

                }else throw new  ResponseStatusException(HttpStatus.FORBIDDEN, "Fondos insuficientes");
            }else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Contraseña incorrecta");

        }else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "El número de cuenta no corresponde con el propietario");
        }
    }

    public void moneyExtraction(Optional <Account> account, TransferDTO transferDTO){
        account.get().getBalance().decreaseAmount(transferDTO.getAmount());
        accountRepository.save(account.get());
        System.out.println(account);
        System.out.println("Extracción de dinero realizada mostrando datos del estado actual: ");

        //comprobación balance -penalty
        penaltyValidation(account,transferDTO);

        System.out.println("balance de la cuenta: " + account.get().getBalance().getAmount());
    }

    public void thirdPartyTransferMoney(TransferDTO transferDTO, String name) {

        Optional<Account> optionalReceiverAccount = accountRepository.findById(transferDTO.getReceiverId());
        validateEmptyAccount(optionalReceiverAccount,transferDTO);
        Optional<ThirdParty> optionalThirdParty = thirdPartyRepository.findByName(name);
        if(optionalThirdParty.isEmpty())throw new ResponseStatusException(HttpStatus.FORBIDDEN, "La autenticación no corresponde con el usuario");

        String primaryOwnerName = optionalReceiverAccount.get().getPrimaryOwner().getName();
        String secondaryOwnerName = optionalReceiverAccount.get().getSecondaryOwner() ==null? "" : optionalReceiverAccount.get().getSecondaryOwner().getName();

        //verificación de correspondencia entre ID cuenta y propietario
        if(primaryOwnerName.equals(transferDTO.getName())){
            //verificación de contraseña
            if(optionalReceiverAccount.get().getPrimaryOwner().getPassword().equals(transferDTO.getPassword())){

                thirdTransferFunds(optionalReceiverAccount.get(),transferDTO);

            }else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Contraseña incorrecta");

        } else if (secondaryOwnerName.equals(transferDTO.getName())) {
            //se repite el proceso para el secondary Owner
            //verificación de contraseña
            if(optionalReceiverAccount.get().getSecondaryOwner().getPassword().equals(transferDTO.getPassword())){

                thirdTransferFunds(optionalReceiverAccount.get(),transferDTO);

            }else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Contraseña incorrecta");

        }else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "El número de cuenta no corresponde con el propietario");
        }
    }


    public void thirdPartyRecieveMoney(TransferDTO transferDTO, String name) {

        Optional<Account> optionalPayingAccount = accountRepository.findById(transferDTO.getReceiverId());
        validateEmptyAccount(optionalPayingAccount,transferDTO);
        Optional<ThirdParty> optionalThirdParty = thirdPartyRepository.findByName(name);
        if(optionalThirdParty.isEmpty())throw new ResponseStatusException(HttpStatus.FORBIDDEN, "La autenticación no corresponde con el usuario");

        String primaryOwnerName = optionalPayingAccount.get().getPrimaryOwner().getName();
        String secondaryOwnerName = optionalPayingAccount.get().getSecondaryOwner() ==null? "" : optionalPayingAccount.get().getSecondaryOwner().getName();

        //verificación de correspondencia entre ID cuenta y propietario
        if(primaryOwnerName.equals(transferDTO.getName())){
            //verificación de contraseña
            if(optionalPayingAccount.get().getPrimaryOwner().getPassword().equals(transferDTO.getPassword())){

                thirdRecieveFunds(optionalPayingAccount,transferDTO);

            }else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Contraseña incorrecta");

        } else if (secondaryOwnerName.equals(transferDTO.getName())) {
            //se repite el proceso para el secondary Owner
            //verificación de contraseña
            if(optionalPayingAccount.get().getSecondaryOwner().getPassword().equals(transferDTO.getPassword())){

                thirdRecieveFunds(optionalPayingAccount,transferDTO);

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

        Account account1 = account.get();

        if(account1 instanceof Checking){
            //comparo con Checking
            Optional<Checking> optionalChecking= checkingRepository.findById(transferDTO.getSenderId());
            BigDecimal minimumBalance = optionalChecking.get().getMinimumBalance();
            BigDecimal penalty = account.get().getPenaltyFee();
            if(optionalChecking.get().getBalance().getAmount().compareTo(minimumBalance) < 0){
                //Se aplica la penalty ya que balance < balance mínimo
                account.get().getBalance().decreaseAmount(penalty);
                System.out.println("Se ha aplicado una tarifa "+ penalty + " debido a que el balance es inferior al balance mínimo permitido");
            }
        }else if(account1 instanceof Savings){
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
            //la cuenta será Student o Credit Card y por lo tanto no existe balance mínimo
            System.out.println("No se aplica la tarifa penalty");
        }
    }

    public void validateEmptyAccount(Optional<Account> account,TransferDTO transferDTO){
        if(account.isEmpty())throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cuenta de destino con id: " + transferDTO.getReceiverId() + " no encontrada");
    }

    public void thirdTransferFunds(Account optionalReceiverAccount,TransferDTO transferDTO){
        optionalReceiverAccount.getBalance().increaseAmount(transferDTO.getAmount());
        accountRepository.save(optionalReceiverAccount);
        System.out.println(optionalReceiverAccount);
        System.out.println("Transferencia realizada mostrando datos del estado actual: ");
        System.out.println("balance de la cuenta del destinatario : " + optionalReceiverAccount.getBalance().getAmount());
    };

    public void thirdRecieveFunds(Optional <Account> optionalPayingAccount,TransferDTO transferDTO){
        optionalPayingAccount.get().getBalance().decreaseAmount(transferDTO.getAmount());
        accountRepository.save(optionalPayingAccount.get());
        System.out.println(optionalPayingAccount);
        System.out.println("Transferencia realizada mostrando datos del estado actual: ");

        //comprobación balance -penalty
        penaltyValidation(optionalPayingAccount,transferDTO);

        System.out.println("balance de la cuenta del destinatario : " + optionalPayingAccount.get().getBalance().getAmount());
    }
}
