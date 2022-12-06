package com.ironhack.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class BalanceDTO {
    @NotNull(message = "la cantidad no puede ser nula")
    private BigDecimal amount;

    public BalanceDTO() {
    }

    public BalanceDTO(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
