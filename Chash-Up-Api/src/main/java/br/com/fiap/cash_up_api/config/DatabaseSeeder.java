package br.com.fiap.cash_up_api.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.fiap.cash_up_api.Repository.CategoryRepository;
import br.com.fiap.cash_up_api.Repository.TransactionRepository;
import br.com.fiap.cash_up_api.Repository.UserRepositoty;
import br.com.fiap.cash_up_api.model.Category;
import br.com.fiap.cash_up_api.model.Transaction;
import br.com.fiap.cash_up_api.model.TransactionType;
import br.com.fiap.cash_up_api.model.User;
import br.com.fiap.cash_up_api.model.UserRole;
import jakarta.annotation.PostConstruct;

@Component
@Profile("{ACTIVE_PROFILE}")
public class DatabaseSeeder {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepositoty userRepositoty;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        var joao = User.builder()
                        .email("joao@fiap.com.br")
                        .password(passwordEncoder.encode("12345"))
                        .role(UserRole.ADMIN)
                        .build();

        var maria = User.builder()
                        .email("maria@fiap.com.br")
                        .password(passwordEncoder.encode("12345"))
                        .role(UserRole.USER)
                        .build();

        userRepositoty.saveAll(List.of(joao, maria));

        var categories = List.of(
                Category.builder().name("Educação").icon("Book").user(joao).build(),
                Category.builder().name("Lazer").icon("Dices").user(joao).build(),
                Category.builder().name("Saúde").icon("Heart").user(joao).build(),
                Category.builder().name("Alimentação").icon("Apple").user(joao).build(),
                Category.builder().name("Transporte").icon("Bus").user(joao).build(),
                Category.builder().name("Transporte").icon("Bus").user(maria).build())
                ;

        categoryRepository.saveAll(categories);

        var descriptions = List.of(
                "Uber para casa",
                "Conta de Luz",
                "Faculdade",
                "Aluguel",
                "Supermercado",
                "Internet",
                "Cinema com amigos",
                "Compra na farmácia",
                "Assinatura do Spotify",
                "Assinatura da Netflix",
                "Academia",
                "Jantar com a família",
                "Padaria",
                "Manutenção do carro",
                "Livros da faculdade",
                "Café com colegas",
                "Roupas novas",
                "Presente de aniversário",
                "Delivery de comida",
                "Estacionamento no shopping");

        var transactions = new ArrayList<Transaction>();
        for (int i = 0; i < 50; i++) {
            transactions.add(Transaction.builder()
                    .description(descriptions.get(new Random().nextInt(descriptions.size())))
                    .amount(BigDecimal.valueOf(new Random().nextDouble() * 500))
                    .date(LocalDate.now().minusDays(new Random().nextInt(30)))
                    .type(new Random().nextBoolean() ? TransactionType.EXPENSE : TransactionType.INCOME)
                    .category(categories.get(new Random().nextInt(categories.size())))
                    .user(new Random().nextBoolean() ? joao : maria)
                    .build());
        }
        transactionRepository.saveAll(transactions);


    }

}