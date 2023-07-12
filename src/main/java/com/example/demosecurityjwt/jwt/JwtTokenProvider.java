package com.example.demosecurityjwt.jwt;

import com.example.demosecurityjwt.security.CustomerUserDetail;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    public final String jwtKey = "Day_la-_ma_bao_mat";

    public final int jwtExpiration = 86400000;

    public String generateToken(CustomerUserDetail customerUserDetail) {
        Date now = new Date();
        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtExpiration))
                .setSubject(customerUserDetail.getUsername())
                .signWith(SignatureAlgorithm.HS512, jwtKey)
                .compact();
    }

    public String getUsernameByToken(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtKey)
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token){
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtKey)
                    .parseClaimsJws(token).getBody();
            return true;
        } catch (IllegalArgumentException | ExpiredJwtException | MalformedJwtException | UnsupportedJwtException e){
            e.printStackTrace();
        }
        return false;
    }
}
