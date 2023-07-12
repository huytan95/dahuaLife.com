package com.example.demosecurityjwt.controller;

import com.example.demosecurityjwt.dto.LoginRequest;
import com.example.demosecurityjwt.dto.SignupRequest;
import com.example.demosecurityjwt.jwt.JwtTokenProvider;
import com.example.demosecurityjwt.model.User;
import com.example.demosecurityjwt.security.CustomerUserDetail;
import com.example.demosecurityjwt.service.IRoleService;
import com.example.demosecurityjwt.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final IUserService iUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final IRoleService iRoleService;

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomerUserDetail customerUserDetail = (CustomerUserDetail) authentication.getPrincipal();
        String token = jwtTokenProvider.generateToken(customerUserDetail);

        return ResponseEntity.ok(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest){
        if(iUserService.existUsername(signupRequest.getUsername())){
            return ResponseEntity.ok("username is existed");
        }
        User user = new User();
        user.setName(signupRequest.getName());
        user.setUsername(signupRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(signupRequest.getPassword()));
        user.getRoleSet().add(iRoleService.getRoleById(1l));
        iUserService.saveUser(user);
        return ResponseEntity.ok("signup success");
    }
}
