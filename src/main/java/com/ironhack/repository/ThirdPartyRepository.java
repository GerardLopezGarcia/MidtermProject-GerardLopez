package com.ironhack.repository;

import com.ironhack.model.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThirdPartyRepository extends JpaRepository<ThirdParty,String> {
    Optional<ThirdParty> findByHashedKey(String hashedKey);

    Optional<ThirdParty> findByName(String name);
}
