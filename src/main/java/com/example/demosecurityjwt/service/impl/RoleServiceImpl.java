package com.example.demosecurityjwt.service.impl;

import com.example.demosecurityjwt.model.Role;
import com.example.demosecurityjwt.repository.IRoleRepo;
import com.example.demosecurityjwt.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleRepo iRoleRepo;

    @Override
    public Role getRoleById(Long id){
        Optional<Role> optionalRole = iRoleRepo.findById(id);
        return optionalRole.orElse(null);
    }
}
