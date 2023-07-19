package com.example.demosecurityjwt.jwt;

import com.example.demosecurityjwt.security.CustomerUserDetail;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    public final String jwtKey = "Day_la-_ma_bao_mat";

    public final int jwtExpiration = 360000;

    public String generateToken(CustomerUserDetail customerUserDetail) {
        Date now = new Date();
        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtExpiration))
                .setSubject(customerUserDetail.getUsername())
                .signWith(SignatureAlgorithm.HS512, jwtKey)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isTokenExpired(String token) {
        Claims claims = extractAllClaims(token);
        if (claims != null) {
            return claims.getExpiration().before(new Date());
        }
        return true;
    }

    public String refreshToken(String token) {
        final Claims claims = extractAllClaims(token);
        final Date createdDate = new Date();
        Date now = new Date();
        final Date expirationDate = new Date(now.getTime() + jwtExpiration);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtKey)
                .compact();
    }



    public String getUsernameByToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtKey)
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtKey)
                    .parseClaimsJws(token).getBody();
            return true;
        } catch (IllegalArgumentException | ExpiredJwtException | MalformedJwtException | UnsupportedJwtException e) {
            e.printStackTrace();
        }
        return false;
    }
}
