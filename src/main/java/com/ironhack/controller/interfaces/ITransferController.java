package com.ironhack.controller.interfaces;

import com.ironhack.controller.dto.TransferDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface ITransferController {

    void transferMoney(TransferDTO transferDTO);
    void thirdTransferMoney(TransferDTO transferDTO, String hashedKey);

    void recieveMoney(TransferDTO transferDTO, String hashedKey);
}
