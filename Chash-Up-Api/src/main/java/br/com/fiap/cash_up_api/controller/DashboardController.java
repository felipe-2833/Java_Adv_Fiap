package br.com.fiap.cash_up_api.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.cash_up_api.Repository.TransactionRepository;
import br.com.fiap.cash_up_api.model.User;

@RestController
@RequestMapping("dashboard")
public class DashboardController {

    @Autowired
    private TransactionRepository repository;

    record DashboardData(BigDecimal totalIncome, BigDecimal totalExpense, BigDecimal topExpense ){}

    @GetMapping
    public DashboardData getData(@AuthenticationPrincipal User user){
        BigDecimal totalIncome = repository.sumIncomeByUserInThisMonth(user);
        BigDecimal totalExpense = repository.sumExpenseByUserinthismonth(user);
        BigDecimal topExpense = repository.topExpenseByUserthisMonth(user);
        return new DashboardData(totalIncome, totalExpense, topExpense);
    }

}
