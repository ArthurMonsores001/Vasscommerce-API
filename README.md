# VassCommerce API

Projeto desenvolvido por **Arthur Monsores** para a disciplina de Laboratório de Programação FullStack, na Universidade de Vassouras Prof. Me. Tássio Auad.

## O que é

Uma API REST de e-commerce, feita com Java e Spring Boot. Nesta etapa o foco é o cadastro e a consulta de **produtos**, usando Inversão de Controle (IoC) e Injeção de Dependências (DI).

## O que ela faz

| Ação | Rota | Resposta |
|---|---|---|
| Cadastrar produto | POST /produto | 201 Created, com o endereço do novo produto no cabeçalho Location |
| Consultar produto por id | GET /produto/{id} | 200 OK, ou 404 se não existir |
| Buscar produtos | GET /produto | 200 OK, com filtros opcionais por nome e faixa de valor |

Quando os dados enviados são inválidos (nome vazio, valor negativo, categoria ausente), a API responde **400 Bad Request** com uma mensagem padronizada que mostra quais campos estão errados.

## O que foi feito nesta etapa

- Criei um **contrato** (interface) para as regras de produto, que define o que pode ser feito, sem dizer como.
- Criei **duas implementações** desse contrato:
    - **Memória:** guarda os produtos numa lista em memória.
    - **SQL simulado:** representa um banco de dados como Postgres ou MySQL (por enquanto também usa uma lista, mas é uma classe separada).
- O **controller** conhece apenas o contrato, nunca uma implementação concreta. Quem escolhe qual usar é o Spring, e a injeção é feita pelo construtor.
- A escolha da implementação é feita por **profile**: basta trocar o profile ativo no arquivo de configuração para a API passar a usar a outra implementação, sem mexer no controller.
- Mantive tudo da etapa anterior: DTOs de entrada e saída, validação dos dados, mapeamento entre DTO e entidade, e tratamento padronizado de erros.

## Como a troca de implementação foi comprovada

Com o profile da memória, o primeiro produto criado recebe o id 1. Com o profile SQL, o primeiro produto recebe o id 1000. O controller e as respostas são os mesmos, só a implementação muda.

## Organização do projeto

- **controller:** recebe as requisições e devolve as respostas.
- **model:** o contrato e as duas implementações.
- **entity:** a classe Produto.
- **dto:** os formatos de entrada e saída da API.
- **mapper:** converte entre DTO e entidade.
- **exception:** o formato padronizado dos erros e o tratador global.


## Como executar

1. Abra o projeto no IntelliJ IDEA.
2. No arquivo de configuração, escolha o profile ativo: mem (memória) ou sql (SQL simulado).
3. Execute a classe principal da aplicação.
4. A API fica disponível na porta 8080.

## Autor

Arthur Monsores