# BasicJavaApi 🚀

API REST básica desenvolvida em Java com Spring Boot, criada para fins de aprendizado, exploração e boas práticas de desenvolvimento.
A ideia é ir melhorando ele no meu tempo livre, então pretendo percorrer todos os tópicos relevantes para construção de uma API RESTful.

---

## 📌 Sobre o projeto

Este projeto tem como objetivo praticar a construção de uma API RESTful em Java, abordando conceitos como:

- Arquitetura em camadas (Controller → Service → Repository)
- Persistência de dados com SQLite (provisório)
- Boas práticas de desenvolvimento com Spring Boot
- Soft delete de registros
- Hash de senha com BCrypt

---

## 🛠️ Tecnologias utilizadas

- Java 17
- Spring Boot 3.2.0
- SQLite
- Maven
- jBCrypt 0.4

---

## ▶️ Como rodar localmente

**Pré-requisitos:** Java 17 e Maven instalados.

```bash
# Clone o repositório
git clone https://github.com/MirandaSoulcelo/BasicaJavaApi.git

# Entre na pasta do projeto
cd BasicaJavaApi

# Rode a aplicação
./mvnw spring-boot:run
```

A aplicação vai subir em `http://localhost:8080`

---

## 📡 Endpoints

| Método | URL | Descrição |
|--------|-----|-----------|
| POST | `/users` | Cria um novo usuário |
| GET | `/users` | Lista todos os usuários ativos |
| GET | `/users/{id}` | Busca usuário por ID |
| PUT | `/users/{id}` | Atualiza os dados do usuário |
| PUT | `/users/{id}/inactivate` | Inativa o usuário (soft delete) |

### Exemplo de body (POST /users)

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

- Senhas armazenadas com hash BCrypt
- Soft delete: usuários não são removidos do banco, apenas inativados

---

## 📚 Aprendizados

> Projeto desenvolvido com fins educacionais para explorar Java, Spring Boot e boas práticas de APIs REST.

---

## 🔜 Futuras implementações porque a vida não é um morango:

- Adição do Swagger
- Adição de validators conforme a complexidade dos endpoints forem aumentando
- Autenticação JWT
- Migração para SQL Server
- Uso de interfaces para escalabilidade e manutenção
- Consultas mais limpas com QueryDSL + Q-classes
- Validação de campos ao serem salvos
- Validação de CPF, Email e CNPJ