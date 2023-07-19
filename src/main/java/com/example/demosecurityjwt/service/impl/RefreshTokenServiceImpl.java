package com.example.demosecurityjwt.service.impl;

import com.example.demosecurityjwt.Exception.TokenExpiredException;
import com.example.demosecurityjwt.model.RefreshToken;
import com.example.demosecurityjwt.model.User;
import com.example.demosecurityjwt.repository.IRefreshTokenRepo;
import com.example.demosecurityjwt.service.IRefreshTokenService;
import com.example.demosecurityjwt.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements IRefreshTokenService {

    private final IRefreshTokenRepo iRefreshTokenRepo;
    private final IUserService iUserService;

    @Override
    public Optional<RefreshToken> getRefreshToken(String token){
        return iRefreshTokenRepo.findRefreshTokenByToken(token);
    }

    @Override
    public RefreshToken addRefreshToken(Long userId){
        User user = iUserService.getUser(userId);

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(8640000));

        return iRefreshTokenRepo.save(refreshToken);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken refreshToken) {
        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
            iRefreshTokenRepo.delete(refreshToken);
            throw new TokenExpiredException( "Refresh token was expired. Please make a new signin request");
        }

        return refreshToken;
    }
}
