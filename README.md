# Frota de Veículos

Controle de uma frota: cadastro de veículos, mudança de status (em uso / manutenção) e listagem filtrada por tipo.

Projeto de estudo desenvolvido durante o curso de **Análise e Desenvolvimento de Sistemas** (SENAI-SP Vila Leopoldina), na disciplina de desenvolvimento web com Spring Boot.

## Stack

- Java 21
- Spring Boot (Spring Web + Thymeleaf)
- Bean Validation (Jakarta)
- Lombok
- Maven

Os dados ficam **em memória** (uma `List` dentro do repositório). Não há banco de dados — ao reiniciar a aplicação, os registros são perdidos.

## Como rodar

```bash
./mvnw spring-boot:run
```

Ou abra o projeto no IntelliJ e execute a classe `FrotaVeiculosApplication`.

Depois acesse: **http://localhost:8080/veiculos**

## Rotas

| Método | Rota | O que faz |
|---|---|---|
| GET | `/veiculos` | Lista todos os veículos |
| GET | `/veiculos/novo` | Exibe o formulário de cadastro |
| POST | `/veiculos` | Valida e salva o veículo |
| GET | `/veiculos/{id}` | Detalhes de um veículo |
| GET | `/veiculos/motos` | Lista apenas as motos |
| POST | `/veiculos/{id}/usar` | Marca o veículo como EM_USO |
| POST | `/veiculos/{id}/devolver` | Devolve o veículo para DISPONIVEL |
| POST | `/veiculos/{id}/enviarManutencao` | Envia o veículo para MANUTENCAO |

## Estrutura

```
src/main/java/org/example/frotaveiculos/
├── model/Veiculo.java
├── model/TipoVeiculo.java      (enum)
├── model/StatusVeiculo.java    (enum)
├── repository/VeiculoRepository.java
└── controller/VeiculoController.java
src/main/resources/templates/
├── veiculos.html    (listagem)
├── form.html        (cadastro)
├── detalhes.html    (um veículo)
└── motos.html       (listagem filtrada)
```

## O que esse projeto exercita

- `@Repository` com injeção de dependência via construtor
- Enums como opções de `<select>` e como máquina de estados
- `@PathVariable` e rotas com `{id}`
- Regras de negócio dentro do model (`usar()`, `devolver()`, `enviarManutencao()`) — o Controller nunca altera o status diretamente
- Botões de ação condicionais com `th:if`
- Listagem filtrada em tela separada
