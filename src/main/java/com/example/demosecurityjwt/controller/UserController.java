package com.example.demosecurityjwt.controller;

import com.example.demosecurityjwt.Exception.TokenExpiredException;
import com.example.demosecurityjwt.dto.LoginRequest;
import com.example.demosecurityjwt.dto.LoginResponse;
import com.example.demosecurityjwt.dto.RefreshTokenRequest;
import com.example.demosecurityjwt.dto.SignupRequest;
import com.example.demosecurityjwt.jwt.JwtTokenProvider;
import com.example.demosecurityjwt.model.RefreshToken;
import com.example.demosecurityjwt.model.User;
import com.example.demosecurityjwt.security.CustomerUserDetail;
import com.example.demosecurityjwt.service.IRefreshTokenService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final IUserService iUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final IRoleService iRoleService;
    private final IRefreshTokenService iRefreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomerUserDetail customerUserDetail = (CustomerUserDetail) authentication.getPrincipal();
        String token = jwtTokenProvider.generateToken(customerUserDetail);
        RefreshToken refreshToken = iRefreshTokenService.addRefreshToken(customerUserDetail.getId());

        return ResponseEntity.ok(new LoginResponse(token, refreshToken.getToken()));
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
        user.getRoleSet().add(iRoleService.getRoleById(1L));
        iUserService.saveUser(user);
        return ResponseEntity.ok("signup success");
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Optional<?>> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        String refreshToken = refreshTokenRequest.getRefreshToken();
        Optional<RefreshToken> optional = iRefreshTokenService.getRefreshToken(refreshToken);
        if(optional.isEmpty()){
            String messenger = "Refresh token is not valid";
            return ResponseEntity.ok().body(Optional.of(messenger));
        }
        return ResponseEntity.ok().body(iRefreshTokenService.getRefreshToken(refreshToken)
                .map(iRefreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(CustomerUserDetail::mapToUserDetail)
                .map(customerUserDetail -> {
                    String token = jwtTokenProvider.generateToken(customerUserDetail);
                    return new LoginResponse(token, refreshToken);
                }));
    }
}
