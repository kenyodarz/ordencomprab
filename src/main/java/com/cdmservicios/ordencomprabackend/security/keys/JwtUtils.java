package com.cdmservicios.ordencomprabackend.security.keys;

import com.cdmservicios.ordencomprabackend.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${KenyoDZ.app.jwtSecret}")
    private String jwtSecret;
    @Value("${KenyoDZ.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            LOGGER.error("Firma del JWT inválida: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            LOGGER.error("Token JWT inválido: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            LOGGER.error("El token JWT ha caducado: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOGGER.error("Token JWT sin soporte: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("el token JWT esta vacío: {}", e.getMessage());
        }
        return false;
    }

}
