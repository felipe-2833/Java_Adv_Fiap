package br.com.fiap.cash_up_api.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.cash_up_api.Repository.CategoryRepository;
import br.com.fiap.cash_up_api.Repository.TransactionRepository;
import br.com.fiap.cash_up_api.model.Category;
import br.com.fiap.cash_up_api.model.Transaction;
import br.com.fiap.cash_up_api.model.TransactionType;
import jakarta.annotation.PostConstruct;

@Component
public class DatabaseSeeder {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @PostConstruct
    public void Init() {

        var categories = List.of(
                Category.builder().name("Educação").icon("Book").build(),
                Category.builder().name("Lazer").icon("Dices").build(),
                Category.builder().name("Saúde").icon("Heart").build(),
                Category.builder().name("Alimentação").icon("Apple").build(),
                Category.builder().name("Transporte").icon("Bus").build());

        categoryRepository.saveAll(categories);

        var descriptions = List.of(
            "Uber para casa",
            "Conta de Luz",
            "Faculdade",
            "Aluguel",
            "Supermercado",
            "Farmácia",
            "Combustível",
            "Cinema com amigos",
            "Assinatura Netflix",
            "Internet",
            "Conta de Água",
            "Cartão de crédito",
            "Manutenção do carro",
            "Academia",
            "Delivery de comida",
            "Presente de aniversário",
            "Consulta médica",
            "Material escolar",
            "Compras no shopping",
            "Pet shop",
            "Estacionamento"
        );

        var transactions = new ArrayList<Transaction>();

        for(int i = 0; i < 50; i++){
            transactions.add(Transaction
            .builder()
            .description(descriptions.get(new Random().nextInt(descriptions.size())))
            .amount(BigDecimal.valueOf(new Random().nextDouble() * 500))
            .date(LocalDate.now().minusDays(new Random().nextInt(30)))
            .type(TransactionType.EXPENSE)
            .category(categories.get(new Random().nextInt(categories.size())))
            .build());
        }
        transactionRepository.saveAll(transactions);
    }

}
