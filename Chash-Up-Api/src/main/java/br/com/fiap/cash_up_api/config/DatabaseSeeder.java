package br.com.fiap.cash_up_api.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.cash_up_api.Repository.CategoryRepository;
import br.com.fiap.cash_up_api.model.Category;
import jakarta.annotation.PostConstruct;

@Component
public class DatabaseSeeder {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionRepository 

    @PostConstruct
    public void Init() {
        categoryRepository.saveAll(
                List.of(
                        Category.builder().name("educação").icon("Book").build(),
                        Category.builder().name("Lazer").icon("Dices").build(),
                        Category.builder().name("Saúde").icon("Heart").build()));
        var transactions = new ArrayList<Transaction>()
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
    "IPTU",
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
        for(int i=0; i<50; i++){
            transactions.add(Transaction
            .builder()
            .description(descriptions.get(new Ramdom().nextInt(descriptions.size())))
            .amount(BigDecimal.valueOf(new Ramdom().nextDouble() * 500))
            .date(LocalDate.now().minusDays(new Ramdom().nextInt(30)))

            .build())
        }
        transactionRepository.saveAll(transactions)
    }

}
