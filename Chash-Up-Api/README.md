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

# Aula 22/04

- Criar controle de acesso. Autenticação(Verificar veracidade do usuario) + Autorização(Permissão do usuario para uma certa ação).

# Aula 24/04

- PEsoalizar detail service, retorna user detail que obtem detalhes do usuarios e o grantedAutorithes
- classe que é userdetailService, necessita ter um metodo load:

@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }

-Criar usuario valido na aplicação, AuthService, buscará usuario no banco e mostra-ra os detalhes do mesmo.

- Para isso a clesse user precisa fazer um contrato com UserDetalils -> implementando seus metodos obrigatorios ("clausulas do contrato"), o getAuthorits e o getusername, que buscam uma lista de autorização do usuario e o username será no caso o email;

* dica: implements == contrato