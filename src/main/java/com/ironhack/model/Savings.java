package com.ironhack.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Savings extends Account{
    @NotEmpty(message = "Introduzca una contraseña")
    private String secretKey;
    @DecimalMin(value = "100",message = "El balance mínimo no puede ser inferior a 100")
    private BigDecimal minimumBalance;
    @DecimalMax(value = "0.5",message = "El interés no puede ser superior a 0.5")
    private BigDecimal interestRate;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Savings() {
    }

    public Savings(Money balance, AccountHolder primaryOwner, LocalDate creationDate, BigDecimal penaltyFee, String secretKey, BigDecimal minimumBalance, BigDecimal interestRate, Status status) {
        super(balance, primaryOwner, creationDate, penaltyFee);
        this.secretKey = secretKey;
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
        this.status = status;
    }
    //Constructor with secondaryOwner

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, LocalDate creationDate, BigDecimal penaltyFee, String secretKey, BigDecimal minimumBalance, BigDecimal interestRate, Status status) {
        super(balance, primaryOwner, secondaryOwner, creationDate, penaltyFee);
        this.secretKey = secretKey;
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
        this.status = status;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance == null ? new BigDecimal("1000") : minimumBalance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate == null ? new BigDecimal("0.0025") : interestRate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
