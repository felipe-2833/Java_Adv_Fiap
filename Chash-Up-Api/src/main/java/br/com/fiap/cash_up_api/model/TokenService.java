package br.com.fiap.cash_up_api.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class TokenService {
    
    private final Long duration = 10L; // 10 minutoss
    private final Algorithm alg = Algorithm.HMAC256("secret");

    public Token createToken(User user) {
        JWT.create()
            .withSubject(user.getId().toString())
            .withClaim("email", user.getEmail())
            .withClaim("role", user.getRole().toString())
            .withExpiresAt(LocalDateTime.now().plusMinutes(duration).toInstant(ZoneOffset.ofHours(-3)))
            .sign(alg);
        return new Token("Token", 21315656L, "Bearer", "ADMIN");
        
    
    
    }

   


}
