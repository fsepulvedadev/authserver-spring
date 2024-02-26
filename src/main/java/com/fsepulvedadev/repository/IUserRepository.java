package com.fsepulvedadev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsepulvedadev.models.ApplicationUser;

@Repository
public interface IUserRepository extends JpaRepository<ApplicationUser, Integer> {

    
    Optional<ApplicationUser> findByUsername (String username);

}
