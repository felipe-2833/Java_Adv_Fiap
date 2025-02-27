package br.com.fiap.cash_up_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.cash_up_api.model.Category;

@RestController // component
public class CategoryController {

    private List<Category> repository = new ArrayList<>();

    // listar todas as categorias
    // GET :8080/categories -> json
    @GetMapping("/categories")
    public List<Category> index(){ //mocky
        return repository;
    }

    //cadastrar categoria
    //POST :8080/categories -> json
    @PostMapping("/categories")
    //retorna resposta adequada para o metodo
    //sempre retornar o recurso criado em metodos post
    //@ResponseStatus(code = HttpStatus.CREATED)
    //@requestbody verifica as mensagens passadas no body da requisisção
    public ResponseEntity<Category> create(@RequestBody Category category){
        System.out.println("Cadastrando categoria: " + category.getName());
        repository.add(category);
        //ResponseEntity da a possibilidade de configurar a resposta do metodo post 
        return ResponseEntity.status(201).body(category);
    }

    //retornar uma categoria
    // GET :8080/categories/id -> json
    @GetMapping("/categories/{id}")
    //Pathvariable indica que recebe informações do path (nome parametro = nome {})
    public ResponseEntity<Category> get(@PathVariable Long id){
        //stream, ao ivez de um for, permite percorrer uma lista e pegar apenas o dado nescessario, o filter vai indicar qual o filtro nescessario para isso;
        //findfirst retorna um Optional -> indicação se uma variavel tem valor ou não
        var category = repository.stream().filter(c -> c.getId().equals(id)).findFirst();

        if(category.isEmpty()){
            //build cria propria resposta
            return ResponseEntity.notFound().build(); //status(404) = notFound()
        }
        return ResponseEntity.ok(category.get()); //nunca chamar um metodo get de um optional se você não ter certeza de que tem alguma coisa dentro;
        //ok == status(200).body()
    }

    // apagar categoria

    // editar uma categorias
    
}
