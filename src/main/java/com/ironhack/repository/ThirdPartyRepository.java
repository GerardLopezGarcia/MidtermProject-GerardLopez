package com.ironhack.repository;

import com.ironhack.model.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThirdPartyRepository extends JpaRepository<ThirdParty,Integer> {
    void deleteById(String name);

    Optional<ThirdParty> findById(String name);
}
