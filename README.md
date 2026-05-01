# BasicJavaApi 🚀

API REST desenvolvida em Java com Spring Boot, criada para fins de aprendizado e boas práticas de desenvolvimento.
A ideia é ir melhorando no tempo livre, percorrendo todos os tópicos relevantes para construção de uma API RESTful.

---

## 📌 Sobre o projeto

Este projeto tem como objetivo praticar a construção de uma API RESTful em Java, abordando conceitos como:

- Arquitetura em camadas (Controller → Service → Repository)
- Persistência de dados com SQLite (provisório)
- Boas práticas de desenvolvimento com Spring Boot
- Soft delete de registros
- Hash de senha com BCrypt
- Autenticação stateless com JWT
- Segurança com Spring Security
- Documentação com Swagger/OpenAPI

---

## 🛠️ Tecnologias utilizadas

- Java 17
- Spring Boot 3.2.0
- Spring Security
- SQLite
- Maven
- jBCrypt 0.4
- JJWT 0.11.5
- SpringDoc OpenAPI (Swagger) 2.3.0

---

## ▶️ Como rodar localmente

**Pré-requisitos:** Java 17 e Maven instalados.

```bash
# Clone o repositório
git clone https://github.com/MirandaSoulcelo/BasicaJavaApi.git

# Entre na pasta do projeto
cd BasicaJavaApi
```

### ⚙️ Configurando o application.properties

Crie o arquivo `src/main/resources/application.properties` com o seguinte conteúdo:

```properties
jwt.secret=${JWT_SECRET:sua-chave-secreta-aqui-minimo-32-caracteres}
```

> ⚠️ Substitua o valor pelo seu próprio secret. Para gerar uma chave segura, rode no terminal:
>
> **Linux/Mac:**
> ```bash
> openssl rand -base64 32
> ```
> **Windows (PowerShell):**
> ```powershell
> [Convert]::ToBase64String((1..32 | ForEach-Object { Get-Random -Maximum 256 }))
> ```
> O secret deve ter **no mínimo 32 caracteres**.

```bash
# Rode a aplicação
./mvnw spring-boot:run
```

A aplicação vai subir em `http://localhost:8080`

Documentação Swagger disponível em `http://localhost:8080/swagger-ui/index.html`

---

## 📡 Endpoints

### 🔓 Públicos

| Método | URL | Descrição |
|--------|-----|-----------|
| POST | `/auth/login` | Realiza login e retorna o token JWT |
| POST | `/users` | Cria um novo usuário |
| GET | `/users` | Lista todos os usuários ativos |
| GET | `/users/{id}` | Busca usuário por ID |

### 🔒 Autenticados (requer Bearer Token)

| Método | URL | Descrição |
|--------|-----|-----------|
| PUT | `/users/{id}/update` | Atualiza os dados do usuário |
| PUT | `/users/{id}/inactivate` | Inativa o usuário (soft delete) |

---

## 🔑 Autenticação

A API utiliza autenticação stateless com **JWT (JSON Web Token)**.

**1. Faça login para obter o token:**
```json
POST /auth/login
{
    "email": "joao@email.com",
    "password": "123456"
}
```

**2. Use o token nas requisições protegidas:**

>Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
> No Swagger, clique em **Authorize 🔒** e cole o token para testar os endpoints protegidos.

---

## 📋 Exemplo de body

### POST /users
```json
{
    "name": "João",
    "email": "joao@email.com",
    "password": "123456",
    "cpf": "12345678900",
    "excluded": false,
    "isAdmin": false
}
```

---

## 🔒 Segurança

- Senhas armazenadas com hash **BCrypt**
- Soft delete: usuários não são removidos do banco, apenas inativados
- Autenticação via **JWT** com expiração de 24h
- Rotas sensíveis protegidas com Spring Security
- Chave secreta JWT via variável de ambiente
- Validação de email e CPF duplicados no cadastro

---

## 📚 Aprendizados

> Projeto desenvolvido com fins educacionais para explorar Java, Spring Boot e boas práticas de APIs REST.

---

## 🔜 Futuras implementações

- Migração para SQL Server
- Validação de campos com Bean Validation
- Validação de formato de CPF e Email
- Consultas mais limpas com QueryDSL + Q-classes
- Roles e permissões por endpoint (ROLE_ADMIN, ROLE_USER)
- Refresh token