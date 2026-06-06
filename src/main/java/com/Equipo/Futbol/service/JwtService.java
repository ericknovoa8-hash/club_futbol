package com.Equipo.Futbol.service;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2 // 
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;
    @Value("${security.jwt.token-expiration}")
    private Long tokenExpiration;
    private SecretKey getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateToken(Long userId, String rolId, String Username) {
        return Jwts.builder()
               .claims(Map.of("userId", userId))
               .claims(Map.of("rolId", rolId))
               .subject(Username)//pertenece al token
               .issuedAt(new Date())// fecha de creación
               .expiration(new Date(System.currentTimeMillis() + tokenExpiration))// Expiración del token
               .signWith(getSignKey())//Firma con llave
               .compact();//Construye el string final
    }
    public Boolean isTokenValid(String token){
         try {
            //El parser intenta descifrar la firma con nuestra llave secreta
            Jwts.parser().verifyWith(getSignKey()).build().parseSignedClaims(token);
            return true;

        } catch(JwtException e){
            log.error("Token is invalid: " + e.getMessage());
            return false;

        } catch(Exception e){
            log.error("Ocurrio un error inesperado: " + e.getMessage());
            return false;
        }
    }
    public <T> T extractClaims(String token, Function<Claims, T> resolver) {
        final Claims claims = Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return resolver.apply(claims);
        

    }
    /**
     * 
     * @param token
     * @return
     */
    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    
    }
    /**
     * extraer el id del rol
     * @param token
     * @return
     */
    public Long extractUserId(String token){
        return extractClaims(token, claims -> claims.get( "userId", Long.class));
    }
    public String extractRolId(String token){
        return extractClaims(token, claims -> claims.get( "rolId", String.class));

    }
    public String refreshToken(String token) throws Exception {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .verifyWith(getSignKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e){
           throw new Exception("token is expired " + e.getMessage());
        } catch(JwtException e){
            throw new Exception("Token is invalid " + e.getMessage());
        } catch (Exception e){
            throw new Exception("Server error " + e.getMessage());
        }
        
        return generateToken(
            claims.get("userId", Long.class), 
        claims.get( "rolId", String.class), claims.getSubject());
    }
}
