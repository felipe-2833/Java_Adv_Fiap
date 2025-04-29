package br.com.fiap.cash_up_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.cash_up_api.model.Credentials;
import br.com.fiap.cash_up_api.model.Token;
import br.com.fiap.cash_up_api.model.TokenService;
import br.com.fiap.cash_up_api.service.AuthService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Token login(@RequestBody @Valid Credentials credentials) {
        log.info("Logando como: " + credentials);
        return authService.login(credentials);
    }

}
