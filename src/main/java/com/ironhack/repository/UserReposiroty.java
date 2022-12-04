package com.ironhack.repository;

import com.ironhack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserReposiroty extends JpaRepository<User,String> {
    Optional<User> findByName(String name);
}
