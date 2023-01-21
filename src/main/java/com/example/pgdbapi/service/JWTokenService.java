package com.example.pgdbapi.service;


import com.example.pgdbapi.model.Usuario;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import java.util.Date;

@Service
public class JWTokenService {
    @Value("${pgdb.jwt.expiration}")
    private String expiration;

    @Value("${pgdb.jwt.secret}")
    private String secret;
    public String makeToken(Authentication authentication) {

        Usuario logado = (Usuario) authentication.getPrincipal();
        Date today = new Date();
        Date dataExpiracao = new Date(today.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API PGDB")
                .setSubject(String.valueOf(logado.getId()))
                .setIssuedAt(today)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUsuario(String token) {
        return Long.parseLong(Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().getSubject());
    }
}
