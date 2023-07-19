package com.example.demosecurityjwt.service.impl;

import com.example.demosecurityjwt.model.User;
import com.example.demosecurityjwt.repository.IUserRepo;
import com.example.demosecurityjwt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepo iUserRepo;

    @Override
    public boolean existUsername(String username){
        User user = iUserRepo.findByUsername(username);
        return user != null;
    }

    @Override
    public void saveUser(User user){
        iUserRepo.save(user);
    }

    @Override
    public User getUser(Long id){
        return iUserRepo.findById(id).orElse(null);
    }
}
