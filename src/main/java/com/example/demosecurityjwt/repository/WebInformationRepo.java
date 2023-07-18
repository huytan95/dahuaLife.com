package com.example.demosecurityjwt.repository;

import com.example.demosecurityjwt.model.WebInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WebInformationRepo extends JpaRepository<WebInformation, Long> {
    Optional<WebInformation> findById(Long id);
}
