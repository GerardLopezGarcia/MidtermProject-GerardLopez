package com.ironhack.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class StudentChecking extends Account {
    private String secretKey;
    @Enumerated(EnumType.STRING)
    private Status status;

    public StudentChecking() {
    }

    public StudentChecking(Money balance, AccountHolder primaryOwner, LocalDate creationDate, BigDecimal penaltyFee, String secretKey, Status status) {
        super(balance, primaryOwner, creationDate, penaltyFee);
        this.secretKey = secretKey;
        this.status = status;
    }
    //Constructor with secondaryOwner
    public StudentChecking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, LocalDate creationDate, BigDecimal penaltyFee, String secretKey, Status status) {
        super(balance, primaryOwner, secondaryOwner, creationDate, penaltyFee);
        this.secretKey = secretKey;
        this.status = status;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
