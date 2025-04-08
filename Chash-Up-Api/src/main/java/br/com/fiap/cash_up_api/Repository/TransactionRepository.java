package br.com.fiap.cash_up_api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.cash_up_api.model.Transaction;

public interface  TransactionRepository extends  JpaRepository<Transaction, Long>{
    
}
