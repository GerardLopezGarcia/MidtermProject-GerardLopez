package com.ironhack.repository;

import com.ironhack.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    List<Account> findAllByPrimaryOwnerName(String name);
    List<Account> findAllBySecondaryOwnerName(String name);

}
