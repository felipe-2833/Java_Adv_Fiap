package br.com.fiap.cash_up_api.controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.fiap.cash_up_api.Repository.TransactionRepository;
import br.com.fiap.cash_up_api.model.Transaction;
import br.com.fiap.cash_up_api.specification.TransactionSpecification;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/transactions")
@Slf4j
public class TransactionController {
    
    public record TransactionFilter(String description, LocalDate startDate, LocalDate endDate){}

    @Autowired
    private TransactionRepository repository;

    @GetMapping
    public Page<Transaction> index(TransactionFilter filter, @PageableDefault(size=10, sort = "date", direction = Direction.DESC) Pageable pageable){
        //log.info("Buscando transações com descrição {} e data {}", filter.description(), filter.date());

        // var probe = Transaction.builder()
        //                 .description(filter.description())
        //                 .date(filter.date())
        //                 .amount(filter.amount())
        //                 .build();

        // var matcher = ExampleMatcher
        //                     .matchingAll()
        //                     .withIgnoreCase()
        //                     .withStringMatcher(StringMatcher.CONTAINING);
        
        // var example = Example.of(probe, matcher);
        //return repository.findAll(example);

        var specification = TransactionSpecification.withFilters(filter);

        return repository.findAll(specification, pageable);
    }



}
