package com.ironhack.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Checking extends Account{
    @NotEmpty(message = "Introduzca una contraseña")
    private String secretKey;

    private BigDecimal minimumBalance;

    private BigDecimal monthlyMaintenanceFee;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Checking() {
    }


    //Constructor with secondaryOwner
    public Checking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, LocalDate creationDate, BigDecimal penaltyFee, String secretKey, BigDecimal minimumBalance, BigDecimal monthlyMaintenanceFee, Status status) {
        super(balance, primaryOwner, secondaryOwner, creationDate, penaltyFee);
        this.secretKey = secretKey;
        setMinimumBalance(minimumBalance);
        setMonthlyMaintenanceFee(monthlyMaintenanceFee);
        this.status = status;
    }

    public Checking(Money balance, AccountHolder primaryOwner, LocalDate creationDate, BigDecimal penaltyFee, String secretKey, BigDecimal minimumBalance, BigDecimal monthlyMaintenanceFee, Status status) {
        super(balance, primaryOwner, creationDate, penaltyFee);
        this.secretKey = secretKey;
        setMinimumBalance(minimumBalance);
        setMonthlyMaintenanceFee(monthlyMaintenanceFee);
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
        this.minimumBalance = minimumBalance == null ? new BigDecimal("250") : minimumBalance;
    }

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(BigDecimal monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee == null ? new BigDecimal("12") : monthlyMaintenanceFee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
