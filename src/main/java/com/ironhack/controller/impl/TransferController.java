package com.ironhack.controller.impl;

import com.ironhack.controller.dto.TransferDTO;
import com.ironhack.controller.interfaces.ITransferController;
import com.ironhack.repository.AccountRepository;
import com.ironhack.service.impl.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TransferController implements ITransferController {


    @Autowired
    TransferService transferService;

    //PATCH transfers
    @PatchMapping("/transfer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transferMoney(@RequestBody @Valid TransferDTO transferDTO){
        transferService.transferMoney(transferDTO);
    }

    //PATCH transfers
    @PatchMapping("/retrieveMoney")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void retrieveMoney(@RequestBody @Valid TransferDTO transferDTO){
        transferService.retrieveMoney(transferDTO);
    }

    //Transfers para terceros
    @PatchMapping("/thirdpartyusers/{hashedKey}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void thirdTransferMoney(@RequestBody @Valid TransferDTO transferDTO, @PathVariable String hashedKey){
        transferService.thirdPartyTransferMoney(transferDTO,hashedKey);
    }

    //recibir dinero (Terceros)
    @PatchMapping("/thirdpartyusers/recieve/{hashedKey}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void recieveMoney(@RequestBody @Valid TransferDTO transferDTO, @PathVariable String hashedKey){
        transferService.thirdPartyRecieveMoney(transferDTO,hashedKey);
    }
}
