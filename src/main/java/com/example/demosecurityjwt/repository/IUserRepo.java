package com.example.demosecurityjwt.repository;

import com.example.demosecurityjwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User, Long> {
    User findByUsername (String username);

}
