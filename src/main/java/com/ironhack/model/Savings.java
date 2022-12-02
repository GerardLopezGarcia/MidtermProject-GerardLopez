package com.ironhack.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Savings extends Account{
    private String secretKey;

    private BigDecimal minimumBalance;
    private BigDecimal interestRate;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Savings() {
    }

    public Savings(Money balance, AccountHolder primaryOwner, LocalDate creationDate, BigDecimal penaltyFee, String secretKey, BigDecimal minimumBalance, BigDecimal interestRate, Status status) {
        super(balance, primaryOwner, creationDate, penaltyFee);
        this.secretKey = secretKey;
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
        this.status = status;
    }
    //Constructor with secondaryOwner

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, LocalDate creationDate, BigDecimal penaltyFee, String secretKey, BigDecimal minimumBalance, BigDecimal interestRate, Status status) {
        super(balance, primaryOwner, secondaryOwner, creationDate, penaltyFee);
        this.secretKey = secretKey;
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
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
        this.minimumBalance = minimumBalance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
