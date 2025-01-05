package com.gestcomm.gestcomm.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    // Secret key for signing JWT (use a strong, random key in production)
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Use a securely generated key
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours
    // Generate JWT token
    public static String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role) // Add custom claims
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    // Parse JWT token and extract claims using Jwts.parserBuilder()
    public static Claims extractClaims(String token) {
        return Jwts.parser() // Get the parser builder
                .setSigningKey(key) // Set the signing key
                .build() // Build the JwtParser
                .parseClaimsJws(token) // Parse the JWT token
                .getBody(); // Extract the claims from the body
    }

    // Check if the token is expired
    public static boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // Validate the token
    public static boolean validateToken(String token, String username) {
        final String extractedUsername = extractClaims(token).getSubject();
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}
