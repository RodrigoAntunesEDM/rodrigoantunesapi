# 📦 rodrigoantunesapi — Gestão de Entregas em um Condomínio

Este projeto foi desenvolvido para **gerir as entregas na portaria de um condomínio**, permitindo o controle de moradores, porteiros, contatos, endereços e objetos entregues.

---

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.3.4**
  - Spring Web
  - Spring Data JPA
  - Spring Validation
  - Spring Cloud OpenFeign
- **H2 Database** (banco de dados em memória)
- **Maven** (gerenciamento de dependências)

---

## 🧩 Estrutura do Projeto

```
br.edu.infnet.rodrigoantunesapi
├── rodrigoantunesapi   # Classe principal para inicialização da aplicação com execução de loaders para carga de porteiros, moradores e objetos entregues
├── controllers         # Endpoints REST (Morador, Porteiro, Objeto)
├── clients             # Endpoints REST serviços externos
├── Model              
│ ├── domain            # Entidades (Morador, Porteiro, Contato, Endereco, Objeto)
│ ├── repository        # Interfaces Spring Data JPA
│ └── service           # Regras de negócio
└── exceptions          # Exceções personalizadas
  └── handler           # Tratamento global de erros

```

---

## 🧩 Principais Dependências

- **spring-boot-starter-web** → Criação da API REST.  
- **spring-boot-starter-validation** → Validação de dados de entrada.  
- **spring-boot-starter-data-jpa** → Integração com o banco de dados via JPA.  
- **com.h2database:h2** → Banco de dados em memória/local para desenvolvimento.  
- **spring-cloud-starter-openfeign** → Comunicação entre serviços externos.  

---

## 🧱 Controllers

A aplicação fornece endpoints RESTful para gerenciar moradores, porteiros e objetos.  
Todos os endpoints seguem o padrão `/api/<entidade>`.

---

### 👥 MoradorController

**Endpoint base:** `/api/moradores`

Operações disponíveis:
- `POST /` — Cadastra um novo morador  
- `PUT /{id}` — Atualiza um morador existente  
- `GET /` — Lista todos os moradores  
- `GET /{id}` — Busca morador por ID  
- `PATCH /{id}/inativar` — Inativa um morador  
- `DELETE /{id}` — Remove um morador  

---

### 🧍 PorteiroController

**Endpoint base:** `/api/porteiros`

Operações disponíveis:
- `POST /` — Cadastra um novo porteiro  
- `PUT /{id}` — Atualiza um porteiro  
- `GET /` — Lista todos os porteiros  
- `GET /{id}` — Busca porteiro por ID  
- `PATCH /{id}/inativar` — Inativa um porteiro  
- `DELETE /{id}` — Remove um porteiro  

---

### 📦 ObjetoController

**Endpoint base:** `/api/objetos`

Operações disponíveis:
- `POST /` — Registra um novo objeto entregue  
- `PUT /{id}` — Atualiza dados do objeto  
- `GET /` — Lista todos os objetos  
- `GET /{id}` — Busca objeto por ID  
- `DELETE /{id}` — Remove o objeto  

---

## 🧪 Teste via Postman

Está disponível no repositório o arquivo **rodrigoantunesapi.postman_collection.json** para importação e validação dos endpoints.

---

## 🧠 Serviços e Regras de Negócio

Os *services* implementam as regras de negócio de cada entidade, realizando:
- Validações específicas (como CPF, ativo/inativo, associação com endereço e contatos)
- Integrações com repositórios JPA
- Tratamento de entidades associadas (`@OneToMany`, `@ManyToOne`)

---

## 🛡️ Tratamento Global de Exceções

A aplicação utiliza a classe `GlobalExceptionHandler` (anotada com `@ControllerAdvice`)  
para capturar e padronizar as respostas de erro lançadas durante a execução dos controllers.

Esse mecanismo centraliza o tratamento de exceções como:
- Entidades não encontradas (`*NaoEncontradoException`)
- Dados inválidos (`*InvalidoException`)
- Violação de integridade (`DataIntegrityViolationException`)
- Erros genéricos (`RuntimeException`)

Todas as respostas de erro são retornadas em formato JSON estruturado.

---

## 🧪 Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/RodrigoAntunesEDM/rodrigoantunesapi.git
   ```
2. Acesse o diretório:
   ```bash
   cd rodrigoantunesapi
   ```
3. Execute o projeto com Maven:
   ```bash
   mvn clean spring-boot:run
   ```
4. A aplicação estará rodando em:
   ```
   http://localhost:8080/
   ```

---


### 🧩 Banco de Dados
O projeto utiliza **H2 Database** em memória.  
Acesse o console H2 após iniciar a aplicação:

- **URL:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- **JDBC URL:** `jdbc:h2:~/arquitetura`
- **Usuário:** `sa`
- **Senha:** *(em branco)*


---

## 👨‍💻 Autor

**Rodrigo Antunes**  
📦 [github.com/RodrigoAntunesEDM](https://github.com/RodrigoAntunesEDM)  
💡 Projeto desenvolvido para estudos de **Desenvolvimento Java com Spring Boot (Instituto INFNET)**.

