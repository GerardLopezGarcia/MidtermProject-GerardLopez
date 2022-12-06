package com.ironhack.model;

import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;


@Entity
@PrimaryKeyJoinColumn(name = "name")
public class AccountHolder extends User{

    private LocalDate dateOfBirth;
    @Embedded
    private Address primaryAddress;

    @AttributeOverrides({
            @AttributeOverride(name="city",column=@Column(name="mailing_city")),
            @AttributeOverride(name="street",column=@Column(name="mailing_steet")),
            @AttributeOverride(name="houseAddress",column=@Column(name="mailing_house"))
    })
    @Embedded
    private Address mailingAddress;

    //por si hace falta que la relación sea bidireccional
    //añadir al constructor y G & S

//    @OneToMany(mappedBy = "primaryOwner")
//    @ToString.Exclude
//    private Set<Account> primayAccounts;
//
//    @OneToMany(mappedBy = "secondaryOwner")
//    @ToString.Exclude
//    private Set<Account> secondaryAccounts;

    public AccountHolder() {
    }
    //Override Constructor with optional mailingAddress
    public AccountHolder(String name, String password, String role, LocalDate dateOfBirth, Address primaryAddress, Address mailingAddress) {
        super(name, password, role);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }

    public AccountHolder(String name, String password, String role, LocalDate dateOfBirth, Address primaryAddress) {
        super(name, password, role);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
    }



    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }
}
