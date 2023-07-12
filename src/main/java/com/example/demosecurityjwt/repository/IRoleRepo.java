package com.example.demosecurityjwt.repository;

import com.example.demosecurityjwt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findById(Long id);
}
