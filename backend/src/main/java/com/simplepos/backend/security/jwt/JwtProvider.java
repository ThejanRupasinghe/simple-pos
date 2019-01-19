package com.simplepos.backend.security.jwt;

import com.simplepos.backend.security.UserPrincipal;
import io.jsonwebtoken.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Provides JWT token generation and validation
 */
@Component
@PropertySource("classpath:simplepos.properties")
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${simplepos.jwt.secret}")
    private String secret;

    @Value("${simplepos.jwt.expiration}")
    private int expiration;

    /**
     * Generates JWT token string
     *
     * @param authentication provided by AuthenticationManager in WebSecurityConfig
     * @return
     */
    public String generateJwtToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret) // TODO: 1/18/19 make the algo configurable
                .compact();
    }

    /**
     * Extracts username form JWT token
     *
     * @param token JWT token string
     * @return
     */
    String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    /**
     * Validates the token with jwt.io api
     *
     * @param token JWT token string
     * @return
     */
    boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty", e);
        }

        return false;
    }
}
