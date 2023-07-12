package com.example.demosecurityjwt.service;

import com.example.demosecurityjwt.model.Role;
import org.springframework.stereotype.Service;

@Service
public interface IRoleService {
    Role getRoleById(Long id);
}
