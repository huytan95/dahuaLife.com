package com.example.demosecurityjwt.service;

import com.example.demosecurityjwt.model.RefreshToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IRefreshTokenService {
    Optional<RefreshToken> getRefreshToken(String token);

    RefreshToken addRefreshToken(Long userId);

    RefreshToken verifyExpiration(RefreshToken refreshToken);
}
