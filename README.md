# Event Planner API

Esta é uma API RESTful para gerenciar eventos, desenvolvida com **Quarkus** e **Hibernate ORM com Panache**. A API suporta operações básicas de CRUD (Create, Read, Update, Delete) para eventos e agora também para participantes.

## Funcionalidades Implementadas

### Endpoints para Eventos
1. **GET /events**
   - Retorna uma lista de todos os eventos.
   - **Exemplo de Resposta (200 OK):**
     ```json
     [
         {
             "id": 1,
             "name": "Tech Conference 2025",
             "date": "2025-02-15T09:00:00",
             "location": "New York Convention Center",
             "participants": []
         },
         {
             "id": 2,
             "name": "Music Festival",
             "date": "2025-03-10T17:00:00",
             "location": "Central Park, NYC",
             "participants": []
         }
     ]
     ```

2. **POST /events**
   - Cria um novo evento.
   - **Body (JSON):**
     ```json
     {
         "name": "Hackathon 2025",
         "date": "2025-06-15T10:00:00",
         "location": "San Francisco",
         "participants": []
     }
     ```
   - **Exemplo de Resposta (201 Created):**
     ```json
     {
         "id": 4,
         "name": "Hackathon 2025",
         "date": "2025-06-15T10:00:00",
         "location": "San Francisco",
         "participants": []
     }
     ```

3. **PUT /events/{id}**
   - Atualiza os dados de um evento existente.
   - **URL:** `/events/4`
   - **Body (JSON):**
     ```json
     {
         "name": "Ano Novo 2025",
         "date": "2025-12-30T23:00:00",
         "location": "Brazil",
         "participants": []
     }
     ```
   - **Exemplo de Resposta (200 OK):**
     ```json
     {
         "id": 4,
         "name": "Ano Novo 2025",
         "date": "2025-12-30T23:00:00",
         "location": "Brazil",
         "participants": []
     }
     ```

4. **DELETE /events/{id}**
   - Remove um evento com base no ID.
   - **URL:** `/events/4`
   - **Resposta Esperada (204 No Content):**
     - Sem corpo de resposta.

### Endpoints para Participantes
1. **POST /events/{eventId}/participants**
   - Adiciona um participante a um evento específico.
   - **URL:** `/events/3/participants`
   - **Body (JSON):**
     ```json
     {
         "name": "Bruno Roberto",
         "email": "brunor.doe@example.com"
     }
     ```
   - **Exemplo de Resposta (201 Created):**
     ```json
     {
         "id": 10,
         "name": "Bruno Roberto",
         "email": "brunor.doe@example.com"
     }
     ```

2. **DELETE /events/{eventId}/participants/{participantId}**
   - Remove um participante específico de um evento.
   - **URL:** `/events/3/participants/10`
   - **Resposta Esperada (204 No Content):**
     - Sem corpo de resposta.

## Estrutura do Projeto

### Entidades
1. **Event**
   - Representa um evento com os seguintes campos:
     - `id` (Long): Identificador único gerado automaticamente.
     - `name` (String): Nome do evento.
     - `date` (LocalDateTime): Data e hora do evento.
     - `location` (String): Local do evento.
     - `participants` (List<Participant>): Lista de participantes.

2. **Participant** (Implementado)
   - Representa um participante com os seguintes campos:
     - `id` (Long): Identificador único gerado automaticamente.
     - `name` (String): Nome do participante.
     - `email` (String): E-mail do participante.
