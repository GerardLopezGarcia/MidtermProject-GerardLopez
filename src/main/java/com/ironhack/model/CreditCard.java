package com.ironhack.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CreditCard extends Account{
    @DecimalMax(value = "100000", message = "El crédito mínimo no puede superar los 100000")
    private BigDecimal creditLimit;
    @DecimalMin(value = "0.1",message = "El interes no puede ser inferior a 0.1")
    private BigDecimal interestRate;

    public CreditCard() {
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, LocalDate creationDate, BigDecimal penaltyFee, BigDecimal creditLimit, BigDecimal interestRate) {
        super(balance, primaryOwner, creationDate, penaltyFee);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }
    //Constructor with optional secondaryOwner
    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, LocalDate creationDate, BigDecimal penaltyFee, BigDecimal creditLimit, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner, creationDate, penaltyFee);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit == null ? new BigDecimal("100") : creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate == null ? new BigDecimal("0.2") : interestRate;
    }
}
