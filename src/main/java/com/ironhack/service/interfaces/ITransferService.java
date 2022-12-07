package com.ironhack.service.interfaces;

import com.ironhack.controller.dto.TransferDTO;

public interface ITransferService {
    void transferMoney(TransferDTO transferDTO);

    void thirdPartyTransferMoney(TransferDTO transferDTO, String hashedKey);

    void thirdPartyRecieveMoney(TransferDTO transferDTO, String hashedKey);

    void retrieveMoney(TransferDTO transferDTO);
}
