# Aula 03/04 

1. Problema: Lista não tem exemplos padrão
- Adicionar seed de um banco de dados sql para salvar minhas categorias;
- adicionando disign pattern builder para não ter uma variedade exponencial de construtores (biblioteca lombok, tem a anotação @builder para realizar isto);

2.Problema: api sem documentação (necessitando usar o postman para ver as respostas)
- Open api/spring doc.org -> biblioteca para se criar a doc
- adicionar dependencia 
- rodar -> http://localhost:8080/v3/api-docs
- ver html -> http://localhost:8080/swagger-ui/index.html
- anotação  @Operation permite modificar a doc ex: description
- mudar aparencia do app -> @OpenAPIDefinition(info = @Info())
  
3. Problema: Api necessita ir no banco de dados diversas vezes para informar a mesma coisa
- spring.jpa.show-sql=true
- Criar cash
- @EnableCaching no app
- @Cacheable("") no metodo que quer um cash
- @CacheEvict(value = "categories", allEntries = true) -> quando novo item for criado, o cache é apagado e é criado um novo em uma nova requisição

# Aula 08/04
Usar passagem de parametros na busca: ?date=fdsfd&descricao=fdsfd
@RequestParam ex:String description 

@Slf4j -> cria log automatico
log.info(template, var)

# Aula 10/04

- Montagem clausula sql para salvar categoria.
- foi comentado as especificações de busca antiga e foi criado TransactionSpecifiction e add Jpa Specification npo repository;