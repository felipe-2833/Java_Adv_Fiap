package br.com.fiap.cash_up_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fiap.cash_up_api.Repository.UserRepositoty;
import br.com.fiap.cash_up_api.model.Credentials;
import br.com.fiap.cash_up_api.model.TokenService;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepositoty repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByEmail(username).orElseThrow(
            () -> new UsernameNotFoundException(username, null)
        );
        return user;
    }

    public void login(Credentials credentials) {
        var user = repository.findByEmail(credentials.email())
        .orElseThrow(
            () -> new UsernameNotFoundException("usario não encontrado", null)
        );

        if (passwordEncoder.matches(credentials.password(),  user.getPassword())) {
            throw new BadCredentialsException("Senha inválida");
        }

}
