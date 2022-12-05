package com.ironhack.repository;

import com.ironhack.model.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckingRepository extends JpaRepository<Checking,Integer> {
    List<Checking> findAll();

}
