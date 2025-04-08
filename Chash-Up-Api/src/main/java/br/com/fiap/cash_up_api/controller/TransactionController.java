package br.com.fiap.cash_up_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.fiap.cash_up_api.Repository.TransactionRepository;


@RestController
@RequestMapping("transactions")
public class TransactionController {
    
    @Autowired
    private TransactionRepository repository;

    @GetMapping
    public List<Transaction> index(){
        return repository.findAll();
    }



}
