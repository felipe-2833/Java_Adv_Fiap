package br.com.fiap.cash_up_api.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.cash_up_api.model.User;

public interface UserRepositoty extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String username);
    

}
