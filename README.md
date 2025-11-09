# 🏍️ MotoTrack – API e Plataforma Web de Monitoramento

Este projeto é uma **API e aplicação web** desenvolvida em **Java Spring Boot**, que realiza o **monitoramento de motos e sensores RFID** para controle de frotas e acompanhamento em tempo real.
O sistema permite o **gerenciamento completo** de entidades como **Filiais, Motos, Sensores** e **Localizações**, além de integração com **mensageria (RabbitMQ e Kafka)** para comunicação assíncrona e **banco de dados em nuvem Azure SQL**.

---

## 👨‍💻 Integrantes

| Nome                        | RM     |
| --------------------------- | ------ |
| **Angello Turano da Costa** | 558576 |
| **Cauã Sanches de Santana** | 558317 |
| **Leonardo Bianchi**        | 558576 |

---

## ⚙️ Principais Tecnologias

### 🧩 Backend

* **Java 17**
* **Spring Boot 3**
* **Spring MVC / Thymeleaf** (frontend e views)
* **Spring Data JPA / Hibernate**
* **Spring Security** (controle de permissões)

  * `viewer` → acesso somente leitura
  * `editor` → pode criar, editar e excluir
* **Azure SQL Database** (banco de dados em nuvem)
* **RabbitMQ** (mensageria assíncrona)
* **Apache Kafka** (streaming e integração em tempo real)
* **Docker / Docker Compose**

---

## 🧱 Estrutura do Projeto

```
ApiRest_Challenge-main
 ┣ 📂 src/main/java/br/monitoramento/motu
 ┃ ┣ 📂 config           # Configurações gerais e segurança (Spring Security)
 ┃ ┣ 📂 controller       # Controladores REST e Web (Thymeleaf)
 ┃ ┣ 📂 dto              # Objetos de transferência de dados (DTOs)
 ┃ ┣ 📂 exception        # Exceções personalizadas
 ┃ ┣ 📂 kafka            # Configuração e Producer/Consumer do Kafka
 ┃ ┣ 📂 mapper           # Conversores Entity ↔ DTO
 ┃ ┣ 📂 model            # Entidades JPA (Moto, Filial, Sensor, Localização)
 ┃ ┣ 📂 rabbit           # Configuração e Producer/Consumer do RabbitMQ
 ┃ ┣ 📂 repository       # Repositórios Spring Data JPA
 ┃ ┣ 📂 service          # Lógica de negócio e integração com mensageria
 ┃ ┗ ApiMottuApplication.java  # Classe principal
 ┣ 📂 resources
 ┃ ┣ 📂 static           # Arquivos estáticos (CSS, JS)
 ┃ ┣ 📂 templates        # Templates Thymeleaf (HTML)
 ┃ ┣ 📂 i18n             # Arquivos de tradução (messages.properties)
 ┃ ┣ application.properties  # Configurações gerais (Azure + Kafka + Rabbit)
 ┣ 📜 Dockerfile
 ┣ 📜 docker-compose.kafka.yml  # Subida local do Kafka e Kafdrop
 ┣ 📜 docker-compose.rabbit.yml # Subida local do RabbitMQ
 ┣ 📜 pom.xml
 ┗ 📜 README.md
```

---

## 🌐 Interface Web

A aplicação conta com uma interface **moderna e responsiva (Bootstrap 5)**:

### 🔐 Login e Perfis de Acesso

* Usuário `viewer / viewer123` → acesso de leitura
* Usuário `editor / editor123` → acesso completo

### 🧭 Navegação

* Página inicial: `/home`
* Motos: `/view/motos`
* Sensores: `/view/sensores`
* Localizações: `/view/localizacoes`
* Filiais: `/view/filiais`

---

## 🔑 Endpoints REST (API)

### **/api/motos**

* `POST /api/motos` → Criar moto
* `GET /api/motos` → Listar motos
* `GET /api/motos/{id}` → Buscar por ID
* `PUT /api/motos/{id}` → Atualizar moto
* `DELETE /api/motos/{id}` → Excluir moto

> 📨 Ao criar, editar ou excluir uma moto, um evento é enviado automaticamente para o **RabbitMQ** e o **Kafka** (`mototrack.motos`).

### **/api/sensores**

* CRUD completo (POST, GET, PUT, DELETE)

### **/api/localizacoes**

* CRUD completo (POST, GET, PUT)

### **/api/filiais**

* CRUD completo (POST, GET, PUT, DELETE)

---

## 🐇 Integração com RabbitMQ

### Subir RabbitMQ via Docker

```bash
docker run -d --name rabbit -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

Acesse o painel:
👉 [http://localhost:15672](http://localhost:15672)
**Login padrão:** `guest / guest`

### Fila principal:

* `mototrack.motos`

Exemplo de mensagem publicada:

```json
{
  "id": 6,
  "placa": "XYZ-1234",
  "idModelo": 1,
  "idFilial": 2,
  "status": "Disponivel",
  "kmRodado": 15000,
  "type": "created"
}
```

---

## 🧩 Integração com Kafka

### Subir Kafka + Zookeeper + Kafdrop

```bash
docker compose -f docker-compose.kafka.yml up -d
```

Acesse o painel **Kafdrop**:
👉 [http://localhost:19000](http://localhost:19000)

### Tópico principal:

* `mototrack.motos`

Cada evento (create/update/delete) é publicado neste tópico para consumo em tempo real.

---

## 🧪 Testes Rápidos

### Criar Moto via API

```bash
curl -X POST http://localhost:8080/api/motos \
 -H "Content-Type: application/json" \
 -d '{"placa":"ABC-1234","idModelo":1,"idFilial":1,"status":"Disponivel","kmRodado":12000}'
```

Verifique:

* Logs → `[Rabbit]` e `[Kafka]`
* Painel RabbitMQ → fila `mototrack.motos`
* Painel Kafdrop → tópico `mototrack.motos`

---

## 🐳 Deploy e Execução com Docker

### Build da imagem

```bash
docker build -t mototrack-api .
```

### Executar o container

```bash
docker run -p 8080:8080 mototrack-api
```

Acesse:
👉 [http://localhost:8080](http://localhost:8080)

---

## 🧠 Banco de Dados em Nuvem (Azure SQL)

A aplicação utiliza **Azure SQL Database** (PaaS) hospedado na nuvem, substituindo o antigo banco H2.

### Configuração de conexão (`application.properties`)

```properties
spring.datasource.url=jdbc:sqlserver://srv-mottu-sql.database.windows.net:1433;database=db-mottu;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;
spring.datasource.username=rm558576
spring.datasource.password=Fiap@Devops2025
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.database-platform=org.hibernate.dialect.SQLServerDialect
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

> 💡 **Obs:** as tabelas foram criadas a partir do `schema.sql` original e migradas manualmente para o banco `db-mottu` na Azure.

---

## 🧾 Funcionalidades Implementadas

✅ CRUD completo de Filiais, Motos, Sensores e Localizações
✅ Controle de acesso com Spring Security (`viewer` / `editor`)
✅ Interface Web com Bootstrap e Thymeleaf
✅ Integração com RabbitMQ (Producer e Consumer)
✅ Integração com Apache Kafka (Producer e Consumer)
✅ Banco de Dados em Nuvem (Azure SQL)
✅ Build e Deploy via Docker
✅ Mensagens multilíngues (`messages.properties`)

---

## 🚀 Próximos Passos

* [ ] Adicionar monitoramento em tempo real (WebSocket)
* [ ] Implementar testes unitários com JUnit + Mockito
* [ ] Adicionar observabilidade (Spring Actuator / Prometheus)
* [ ] Publicar container no **Azure App Service**

---

💡 **Resumo Final:**

> O MotoTrack é uma plataforma robusta de **monitoramento de frotas** com **integração assíncrona em microserviços** via **RabbitMQ** e **Kafka**, interface web moderna e **banco de dados em nuvem (Azure SQL)** — pronta para ambiente corporativo e escalável.

---
