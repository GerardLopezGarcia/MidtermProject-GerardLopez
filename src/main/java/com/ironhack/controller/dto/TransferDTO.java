package com.ironhack.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TransferDTO {
    @NotEmpty(message = "Introduzca un nombre porfavor")
    private String name;
    @NotEmpty(message = "Introduzca su contraseña porfavor")
    private String password;
    private Integer senderId;
    @NotNull(message = "Introduzca la cantidad a transferir porfavor")
    private BigDecimal amount;
    @NotNull(message = "Introduzca el id del destinatario porfavor")
    private Integer receiverId;


    public TransferDTO() {
    }

    public TransferDTO(String name, String password, Integer senderId, BigDecimal amount, Integer receiverId) {
        this.name = name;
        this.password = password;
        this.senderId = senderId;
        this.amount = amount;
        this.receiverId = receiverId;
    }

    public TransferDTO(String name, String password, BigDecimal amount, Integer receiverId) {
        this.name = name;
        this.password = password;
        this.amount = amount;
        this.receiverId = receiverId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
