# Equipos de fútbol

API para gestionar información de equipos de fútbol. Desarrollada con Spring Boot 3. Securizada con Spring Security.

## Tabla de Contenidos

1. [Instalación](#instalación)
2. [Uso](#uso)
3. [Endpoints de la API](#endpoints-de-la-api)
4. [Ejemplos](#ejemplos)
5. [Contacto](#contacto)

## Instalación

Instrucciones para clonar el repositorio, instalar las dependencias y ejecutar el proyecto.

```bash
git clone https://github.com/mateoymichis/equipos-futbol.git
cd equipos-futbol
./mvnw spring-boot:run
```
También se puede ejecutar el proyecto, luego de clonarlo, utilizando un IDE de Java.

## Uso

La API se ejecutará en el puerto 8080 (se puede cambiar el puerto desde `application.properties`).
Las peticiones a `/equipos`, se encuentran securizadas, por lo que primero se deberá registrar un usuario en la app y utilizar el token de la respuesta para que las peticiones sean exitosas.
El proyecto está destinado a pruebas, por lo que se puede acceder a la documentación de Swagger (http://localhost:8080/swagger-ui/index.html) y a la base de datos en memoria H2 (http://localhost:8080/h2-console) sin autenticarse.

## Endpoints de la API

| Método | Endpoint                | Descripción                             |
|--------|-------------------------|-----------------------------------------|
| PUT    | /auth/register          | Registrar usuario                       |
| PUT    | /auth/login             | Iniciar sesión                          |
| GET    | /equipos                | Obtener una lista con todos los equipos |
| GET    | /equipos/{id}           | Obtener equipo                          |
| POST   | /equipos                | Crear equipo                            |
| PUT    | /equipos                | Actualizar equipo                       |
| DELETE | /equipos                | Eliminar equipo                         |
| GET    | /equipos?nombre={valor} | Buscar equipo                           |


## Ejemplos

### Endpoint

```http request
PUT /auth/register
```

#### Request

```json
{
  "username": "test",
  "password": "12345",
  "firstname": "Juan",
  "lastname": "Perez",
  "email": "juanperez@mail.com"
}
```

#### Response (200 OK)

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNzEzNjIyODYyLCJleHAiOjE3MTM2MjQzMDJ9.NjoCoezA1VF6MXULzDO_Ozjjco05-JK5OMYSTWVqyos"
}
```
### Endpoint

```http request
PUT /auth/login
```

#### Request

```json
{
  "username": "test",
  "password": "12345"
}
```

#### Response (200 OK)

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNzEzNjIyODYyLCJleHAiOjE3MTM2MjQzMDJ9.NjoCoezA1VF6MXULzDO_Ozjjco05-JK5OMYSTWVqyos"
}
```
#### Response (401 Unauthorized)

### Endpoint

```http request
GET /equipos
```

#### Response (200 OK)

```json
[
  {
    "id": 2,
    "nombre": "FC Barcelona",
    "liga": "La Liga",
    "pais": "España"
  },
  {
    "id": 3,
    "nombre": "Manchester United",
    "liga": "Premier League",
    "pais": "Inglaterra"
  }
  // Otros equipos...
]
```

### Endpoint

```http request
GET /equipos/1
```

#### Response (200 OK)

```json
{
  "id": 2,
  "nombre": "FC Barcelona",
  "liga": "La Liga",
  "pais": "España"
}
```
#### Response (404 Not Found)

```json
{
  "mensaje": "Equipo no encontrado",
  "codigo": 404
}
```

### Endpoint

```http request
POST /equipos
```

#### Request

```json
{
  "nombre": "Boca Juniors",
  "liga": "Primera A",
  "pais": "Argentina"
}
```

#### Response (201 Created)

```json
{
  "id": 25,
  "nombre": "Boca Juniors",
  "liga": "Primera A",
  "pais": "Argentina"
}
```

#### Response (400 Bad Request)

```json
{
  "mensaje": "La solicitud es invalida",
  "codigo": 400
}
```

### Endpoint

```http request
PUT /equipos/1
```

#### Request

```json
{
  "nombre": "Boca Juniors",
  "liga": "Primera A",
  "pais": "Argentina"
}
```

#### Response (200 OK)

```json
{
  "id": 1,
  "nombre": "Boca Juniors",
  "liga": "Primera A",
  "pais": "Argentina"
}
```

#### Response (404 Not Found)

```json
{
  "mensaje": "Equipo no encontrado",
  "codigo": 404
}
```
### Endpoint

```http request
DELETE /equipos/1
```

#### Response (204 No Content)

#### Response (404 Not Found)

```json
{
  "mensaje": "Equipo no encontrado",
  "codigo": 404
}
```
### Endpoint

```http request
GET /equipos?nombre={valor}
```

#### Response (200 OK)

```json
[
  {
    "id": 2,
    "nombre": "FC Barcelona",
    "liga": "La Liga",
    "pais": "España"
  },
  {
    "id": 3,
    "nombre": "Manchester United",
    "liga": "Premier League",
    "pais": "Inglaterra"
  }
  // Otros equipos...
]
```

## Contacto

Mateo Fernandez

Email: mateo.rost@gmail.com