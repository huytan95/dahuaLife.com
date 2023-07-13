package com.example.demosecurityjwt.repository;

import com.example.demosecurityjwt.model.Messenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMessengerRepo extends JpaRepository<Messenger, Long> {
    @Override
    Optional<Messenger> findById(Long aLong);
}
