package com.example.demosecurityjwt.service;

import com.example.demosecurityjwt.model.User;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {

    boolean existUsername(String username);

    void saveUser(User user);

    User getUser(Long id);
}
