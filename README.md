# 🚀 API Rest Challenge

Este projeto é uma API REST desenvolvida em **Java Spring Boot** para monitoramento de **motos e sensores RFID**, permitindo o gerenciamento de entidades como **Filiais, Motos, Localizações e Sensores**.

---

## 👨‍💻 Integrantes

- Angello Turano da Costa – RM 558576  
- Cauã Sanches de Santana – RM 558317  
- Leonardo Bianchi – RM 558576  

---

## 📌 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Security**
- **H2 Database** (ambiente de testes)
- **Docker**
- **Gradle**

---

## 📂 Estrutura do Projeto

```
ApiRest_Challenge-main
 ┣ 📂 src/main/java/br/monitoramento/motu
 ┃ ┣ 📂 controller   # Endpoints REST
 ┃ ┣ 📂 dto          # Data Transfer Objects
 ┃ ┣ 📂 mapper       # Conversão Entity ↔ DTO
 ┃ ┣ 📂 model        # Entidades JPA
 ┃ ┣ 📂 repository   # Repositórios Spring Data
 ┃ ┣ 📂 service      # Regras de Negócio
 ┃ ┗ ApiMottuApplication.java # Classe principal
 ┣ 📂 resources
 ┃ ┣ application.properties  # Configurações
 ┣ Dockerfile
 ┣ build.gradle
 ┣ settings.gradle
 ┗ README.md
```

---

## 🔑 Endpoints Principais

### **Filiais**
- `POST /api/filiais` – Criar filial
- `GET /api/filiais` – Listar filiais
- `GET /api/filiais/{id}` – Buscar filial por ID
- `PUT /api/filiais/{id}` – Atualizar filial
- `DELETE /api/filiais/{id}` – Remover filial

### **Motos**
- `POST /api/motos` – Criar moto
- `GET /api/motos` – Listar motos
- `GET /api/motos/{id}` – Buscar moto por ID
- `PUT /api/motos/{id}` – Atualizar moto
- `DELETE /api/motos/{id}` – Remover moto

### **Sensores RFID**
- `POST /api/sensores` – Criar sensor
- `GET /api/sensores` – Listar sensores
- `GET /api/sensores/{id}` – Buscar sensor por ID
- `PUT /api/sensores/{id}` – Atualizar sensor
- `DELETE /api/sensores/{id}` – Remover sensor

### **Localizações**
- `POST /api/localizacoes` – Registrar localização
- `GET /api/localizacoes` – Listar localizações
- `GET /api/localizacoes/{id}` – Buscar localização por ID

---

## 🐳 Executando com Docker

### **Build da imagem**
```bash
docker build -t api-mottu .
```

### **Rodando o container**
```bash
docker run -p 8080:8080 api-mottu
```

A API estará disponível em:  
👉 `http://localhost:8080`

---

## 📖 Banco de Dados

- Banco em memória **H2** configurado para testes:
  - URL: `jdbc:h2:mem:testdb`
  - Usuário: `sa`
  - Senha: *(em branco)*

Para acessar o console H2:  
👉 `http://localhost:8080/h2-console`

---

## ✅ Próximos Passos

- [ ] Adicionar persistência em banco de dados em nuvem (ex: Azure SQL, MySQL, PostgreSQL)  
- [ ] Criar coleção do Postman para testes automatizados  
- [ ] Implementar testes unitários  

---
