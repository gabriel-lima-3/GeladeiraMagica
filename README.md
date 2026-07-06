# GeladeiraMagica — MagicFridgeAI

API REST que gerencia os itens da sua geladeira e sugere receitas com base nos ingredientes cadastrados, usando inteligência artificial generativa.

## Como funciona

O usuário cadastra os alimentos disponíveis na geladeira via API. Ao chamar o endpoint de sugestão, a aplicação busca todos os itens cadastrados no banco de dados, monta um prompt com esses ingredientes e envia para o modelo de linguagem do Google Gemini. A resposta é uma sugestão de receita prática baseada exatamente no que está disponível.

O fluxo completo é:

```
POST /food/criar       → cadastra ingredientes no banco
GET  /recipe/sugerir   → busca ingredientes → monta prompt → chama Gemini → retorna receita
```

## Tecnologias

- **Java 17**
- **Spring Boot 3.2.5**
- **Spring Data JPA + Hibernate** — mapeamento objeto-relacional e persistência
- **Spring WebFlux (WebClient)** — cliente HTTP reativo para chamadas à API externa
- **H2 Database** — banco de dados relacional em memória para desenvolvimento
- **Flyway** — versionamento e migration do schema do banco
- **Lombok** — redução de boilerplate nas classes Java
- **Google Gemini API (gemini-2.5-flash)** — modelo de linguagem responsável pela geração das receitas

## Estrutura do Projeto

```
src/main/java/MagicFridgeAI/
├── config/
│   └── WebClientConfig.java       # Configuração do WebClient com a URL base do Gemini
├── Controller/
│   ├── FoodItemController.java    # Endpoints do CRUD de itens
│   └── RecipeController.java      # Endpoint de sugestão de receitas
├── DTO/
│   └── FoodItemDTO.java
├── enums/
│   └── Categoria.java             # Enum de categorias (FRUTA, LEGUME, LATICINIOS, CARNE, BEBIDA, OUTRO)
├── Mapper/
│   └── FoodItemMapper.java        # Conversão entre Entity e DTO
├── Model/
│   └── FoodItem.java
├── Repository/
│   └── FoodItemRepository.java
└── Service/
    ├── FoodItemService.java
    └── GeminiService.java         # Integração com a API do Gemini
```

## Como Rodar

### Pré-requisitos

- Java 17+
- Maven
- Chave de API do Google Gemini — obtenha gratuitamente em [aistudio.google.com](https://aistudio.google.com)

### Configuração

1. Clone o repositório:

```bash
git clone https://github.com/gabriel-lima-3/GeladeiraMagica.git
cd GeladeiraMagica
```

2. Crie o arquivo de propriedades baseado no exemplo:

```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

3. Configure a variável de ambiente com sua chave do Gemini:

```bash
export GEMINI_API_KEY=sua_chave_aqui
```

4. Rode a aplicação:

```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.
O console do H2 estará disponível em `http://localhost:8080/h2-console`.

## Endpoints

### Itens da Geladeira

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/food/criar` | Cadastra um novo item |
| GET | `/food/listar` | Lista todos os itens |
| GET | `/food/listar/{id}` | Busca item por ID |
| PUT | `/food/atualizar/{id}` | Atualiza um item |
| DELETE | `/food/deletar/{id}` | Remove um item |

### Receitas

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/recipe/sugerir` | Gera sugestão de receita com os itens cadastrados |

### Exemplo de cadastro de item

```json
POST /food/criar
{
    "name": "Banana",
    "categoria": "FRUTA",
    "quantidade": 5,
    "validade": "2026-12-01T00:00:00"
}
```

### Categorias disponíveis

```
FRUTA, LEGUME, LATICINIOS, CARNE, BEBIDA, OUTRO
```

## Variáveis de Ambiente

| Variável | Descrição |
|----------|-----------|
| `GEMINI_API_KEY` | Chave de API do Google Gemini |

## Observações

- O banco de dados H2 é em memória — os dados são perdidos ao reiniciar a aplicação. Para ambiente de produção, a configuração deve ser migrada para PostgreSQL.
- O frontend da aplicação ainda não foi desenvolvido. A próxima etapa do projeto é construir uma interface web em React que consuma esta API, permitindo ao usuário gerenciar os itens da geladeira e visualizar as sugestões de receitas de forma mais acessível.

## Autor

Gabriel Lima — [github.com/gabriel-lima-3](https://github.com/gabriel-lima-3)
Linkedin - www.linkedin.com/in/gabriel-dev-lima
