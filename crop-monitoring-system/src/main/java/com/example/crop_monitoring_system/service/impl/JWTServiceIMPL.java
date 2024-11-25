package com.example.crop_monitoring_system.service.impl;

import com.example.crop_monitoring_system.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTServiceIMPL implements JWTService {
    @Value("${spring.jwtKey}")
    String jwtKey;
    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimsResolve) {
        final Claims claims = getClaims(token);
        return claimsResolve.apply(claims);
    }

    //internal process
    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(token)
                .getBody();
    }
    private Key getSignKey() {
        byte [] decodedJWT = Decoders.BASE64.decode(jwtKey);
        return Keys.hmacShaKeyFor(decodedJWT);
    }


    @Override
    public String generateToken(UserDetails userDetails) {
        return genToken(new HashMap<>(),userDetails);
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        var username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    @Override
    public String refreshToken(UserDetails userDetails) {
        return refreshToken(new HashMap<>(),userDetails);
    }

    private String refreshToken(Map<String, Object> genClaimsRefresh, UserDetails userDetails) {
        genClaimsRefresh.put("role",userDetails.getAuthorities());
        Date now = new Date();
        Date refreshExpire = new Date(now.getTime() + 1000 * 600 * 600);
        return Jwts.builder().setClaims(genClaimsRefresh)
                .setSubject(userDetails.getUsername())
                .setExpiration(refreshExpire)
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private String genToken(Map<String, Object> genClaims, UserDetails userDetails) {
        genClaims.put("role",userDetails.getAuthorities());
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 10000 * 600);
        return Jwts.builder().setClaims(genClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }
}
