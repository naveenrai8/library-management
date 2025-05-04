package com.nr.authservice.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    private static final String SECRET = "abcdefghi";

    public String generateToken(String userId, String username, List<String> roles) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
        var now = new Date();
        return Jwts.builder()
                .subject(userId)
                .claim("username", username)
                .claim("roles", roles)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + Duration.ofHours(10).toMillis()))
                .signWith(key)
                .compact();
    }
}
