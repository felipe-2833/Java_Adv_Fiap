package br.com.fiap.cash_up_api.Repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.cash_up_api.model.Transaction;
import br.com.fiap.cash_up_api.model.User;

public interface  TransactionRepository extends  JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction>{

    @Query("""
        SELECT COALESCE(SUM(t.amount), 0) FROM Transaction as t
        WHERE t.type = 'INCOME'
        AND Month(t.date) = Month(CURRENT_DATE)
        AND Year(t.date) = Year(CURRENT_DATE)
        AND t.user = :user
        """)//JPQL coalecencia(se não encontrar valor, retorna outro)
    BigDecimal sumIncomeByUserInThisMonth(User user); //@Param("user")

    @Query("""
        SELECT COALESCE(SUM(t.amount), 0) FROM Transaction as t
        WHERE t.type = 'EXPENSE'
        AND Month(t.date) = Month(CURRENT_DATE)
        AND Year(t.date) = Year(CURRENT_DATE)
        AND t.user = :user
        """)
    BigDecimal sumExpenseByUserinthismonth(User user);

    @Query("""
        SELECT COALESCE(t.amount, 0) FROM Transaction as t
        WHERE t.type = 'EXPENSE'
        AND Month(t.date) = Month(CURRENT_DATE)
        AND Year(t.date) = Year(CURRENT_DATE)
        AND t.user = :user
        ORDER BY t.amount DESC
        LIMIT 1
        """)
    BigDecimal topExpenseByUserthisMonth(User user);

    //List<Transaction> findByDescriptionContainingIgnoringCase(String description); // Query Methods

    //List<Transaction> findByDescriptionContainingIgnoringCaseAndDate(String description, LocalDate date);

   // List<Transaction> findByDate(LocalDate date);
    
}
