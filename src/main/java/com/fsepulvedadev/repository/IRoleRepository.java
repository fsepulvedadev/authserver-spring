package com.fsepulvedadev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsepulvedadev.models.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByAuthority(String authority);
    

}
