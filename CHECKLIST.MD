# CHECKLIST de separação (IoC/DI)

Aluno: Arthur Monsores
Disciplina: Laboratório de Programação FullStack

- [x] Controladores não fazem `new` de implementações; dependem de interfaces.
    - O `ProdutoController` recebe um `ProdutoModel` (interface) pelo construtor. Não existe `new ProdutoModelMemoria()` nem `new ProdutoModelSql()` em nenhum controller.

- [x] Há ao menos duas implementações `@Service` de `ProdutoModel`.
    - `ProdutoModelMemoria` (bean `produtoModelMemoria`) e `ProdutoModelSql` (bean `produtoModelSql`).

- [x] A seleção é feita por `@Primary` ou `@Qualifier` ou `@Profile`.
    - Estratégia usada: `@Profile`. A classe de memória é ativada com o profile `mem` e a SQL com o profile `sql`. A troca é feita na propriedade `spring.profiles.active` do `application.properties`.

- [x] Injeção é por construtor; nenhum `@Autowired` em campo.
    - O controller usa construtor. Não há `@Autowired` em nenhum arquivo do projeto.

- [x] `ProdutoController` continua retornando 201/200/404 corretos.
    - POST /produto retorna 201 com o cabeçalho Location.
    - GET /produto/{id} retorna 200 quando existe e 404 quando não existe.
    - GET /produto retorna 200 com a lista (filtros opcionais).

- [x] Validação em `ProdutoCreateRequest` continua ativa (`@Valid`).
    - O controller usa `@Valid @RequestBody`, e o DTO tem as regras de nome, descrição, valor e categoria.

- [x] JSON de erro 400 continua padronizado via `@RestControllerAdvice`.
    - O `GlobalExceptionHandler` devolve um `ApiError` com timestamp, status, error, message, path e a lista de campos inválidos.

## Prova da troca de implementação

| Profile ativo | Primeiro POST /produto | Header Location |
|---|---|---|
| `mem` | id 1 | /produto/1 |
| `sql` | id 1000 | /produto/1000 |

O controller, o mapper, os DTOs e o tratador de erros não mudam. Só o bean injetado muda.