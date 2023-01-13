package com.example.pgdbapi.controller;

import com.example.pgdbapi.dto.TokenDto;
import com.example.pgdbapi.model.Usuario;
import com.example.pgdbapi.service.JWTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JWTokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody Usuario usuario) {
        UsernamePasswordAuthenticationToken dadosAuth = usuario.toAuth();
        try {
            Authentication authentication = authManager.authenticate(dadosAuth);
            String token = tokenService.makeToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
