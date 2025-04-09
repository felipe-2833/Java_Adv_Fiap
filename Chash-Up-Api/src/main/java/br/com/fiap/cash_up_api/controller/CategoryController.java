package br.com.fiap.cash_up_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.cash_up_api.Repository.CategoryRepository;
import br.com.fiap.cash_up_api.model.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController // component
@RequestMapping("/categories")
@Slf4j
public class CategoryController {

    //private final Logger log = LoggerFactory.getLogger(getClass());// declarando classe para log

    @Autowired // injeção de dependencias
    private CategoryRepository repository;

    // listar todas as categorias
    // GET :8080/categories -> json
    @GetMapping
    @Cacheable("Categories")
    // deprecated = false, hidden = true -> informa endpoint nao mais utilizado/
    // esconde o endpoint
    @Operation(description = "Listar todas as categorias", tags = "categories", summary = "Lista de categorias")
    public List<Category> index() { // mocky
        log.info("buscando todas categorias");
        return repository.findAll();
    }

    // cadastrar categoria
    // POST :8080/categories -> json
    @PostMapping
    @CacheEvict(value = "categories", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses = {
            @ApiResponse(responseCode = "400", description = "Falha na validação")
    })
    // retorna resposta adequada para o metodo
    // sempre retornar o recurso criado em metodos post
    // @ResponseStatus(code = HttpStatus.CREATED)
    // @requestbody verifica as mensagens passadas no body da requisisção
    public Category create(@RequestBody @Valid Category category) {
        log.info("Cadastrando categoria: " + category.getName());
        // ResponseEntity da a possibilidade de configurar a resposta do metodo post
        return repository.save(category);
    }

    // retornar uma categoria
    // GET :8080/categories/id -> json
    @GetMapping("/{id}")
    // Pathvariable indica que recebe informações do path (nome parametro = nome {})
    public Category get(@PathVariable Long id) {
        // stream, ao ivez de um for, permite percorrer uma lista e pegar apenas o dado
        // nescessario, o filter vai indicar qual o filtro nescessario para isso;
        // findfirst retorna um Optional -> indicação se uma variavel tem valor ou não
        log.info("buscando categorias");
        return getCategory(id); // nunca chamar um metodo get de um optional se você não ter certeza de que tem
                                // alguma coisa dentro;
        // ok == status(200).body()
    }

    // apagar categoria
    // escrever DelMap(ja acha aanotação)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("apagando categoria" + id);
        repository.delete(getCategory(id));
    }

    // editar uma categorias
    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody @Valid Category category) {
        log.info("alterando categoria: " + category.toString()); // Overide no toString(), permite que mudemos a
                                                                 // resposta de categpria, que antes retornava um hash
                                                                 // do objeto (escrevendo apenas category);
        getCategory(id);
        category.setId(id);
        return repository.save(category);
    }

    private Category getCategory(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não enocntrada"));// orElseThrow
                                                                                                             // retorna
                                                                                                             // uma
                                                                                                             // excessao
                                                                                                             // caso não
                                                                                                             // encontre
                                                                                                             // valor
    }

}
