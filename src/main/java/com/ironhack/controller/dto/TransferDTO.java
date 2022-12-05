package com.ironhack.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TransferDTO {
    @NotEmpty(message = "Introduzca un nombre porfavor")
    private String name;
    @NotEmpty(message = "Introduzca su contraseña porfavor")
    private String password;
    @NotNull(message = "Introduzca su id de cuenta porfavor")
    private Integer senderId;
    @NotNull(message = "Introduzca la cantidad a transferir porfavor")
    private BigDecimal amount;
    @NotNull(message = "Introduzca el id del destinatario porfavor")
    private Integer receiverId;


    public TransferDTO() {
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
