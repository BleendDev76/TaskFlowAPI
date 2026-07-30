# TaskFlow API

TaskFlow es una API RESTful construida con Spring Boot para la gestión de tareas. Permite a los usuarios crear, leer, actualizar y eliminar tareas, así como filtrarlas por diferentes criterios como nombre o prioridad.

## Características

- **Crear tareas**: Añade nuevas tareas con descripción, responsable, prioridad y más.
- **Consultar tareas**: Obtén una lista de todas las tareas o busca tareas específicas por ID, nombre o prioridad.
- **Actualizar tareas**: Modifica los detalles de una tarea existente.
- **Eliminar tareas**: Borra tareas del sistema.

## Requisitos Previos

- Java 17 o superior.
- Maven 3.3+.

## Cómo Empezar

1.  **Clona el repositorio:**

    ```bash
    git clone https://github.com/zBleend/TaskFlow--Evaluacion_FullStack-
    cd TaskFlow--Evaluacion_FullStack-
    ```

2.  **Ejecuta la aplicación:**
    Puedes ejecutar la aplicación usando el wrapper de Maven incluido:
    ```bash
    ./mvnw spring-boot:run
    ```
    La API estará disponible en `http://localhost:8080`.

## Documentación de la API con Swagger

Este proyecto utiliza Swagger para generar documentación interactiva de la API. Una vez que la aplicación esté en ejecución, puedes acceder a la interfaz de Swagger UI para explorar y probar los endpoints.

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI Spec (JSON)**: http://localhost:8080/v3/api-docs

Desde la interfaz de Swagger, podrás ver todos los endpoints disponibles, sus parámetros, y ejecutar peticiones directamente desde el navegador.

## Arquitectura y Funcionamiento

La API de TaskFlow está construida siguiendo una arquitectura RESTful de tres capas, común en aplicaciones Spring Boot:

1.  **Capa de Controlador (`@RestController`)**: Expone los endpoints de la API (`/api/v1/tareas`). Se encarga de recibir las peticiones HTTP, validarlas y delegar la lógica de negocio a la capa de servicio.
2.  **Capa de Servicio (`@Service`)**: Contiene la lógica de negocio principal. Coordina las operaciones, como crear, actualizar o buscar tareas, y se comunica con la capa de repositorio para acceder a los datos.
3.  **Capa de Repositorio (`@Repository`)**: Gestiona la interacción con la base de datos. Spring Data JPA se encarga de implementar las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre la entidad `TaskModel`.

### Conexión con la Base de Datos (Neon)

Para que la aplicación se conecte a una base de datos PostgreSQL como la que ofrece **Neon**, necesitas configurar las propiedades de conexión en el archivo `src/main/resources/application.properties`.

Neon te proporcionará una URL de conexión que incluye el usuario, la contraseña, el host y el nombre de la base de datos. Debes añadirla a tu archivo de configuración de la siguiente manera:

```properties
spring.datasource.url=jdbc:postgresql://<user>:<password>@<host>.neon.tech/<dbname>?sslmode=require
spring.datasource.username=<user>
spring.datasource.password=<password>
spring.jpa.hibernate.ddl-auto=update
```

Este proyecto utiliza Swagger para generar documentación interactiva de la API. Una vez que la aplicación esté en ejecución, puedes acceder a la interfaz de Swagger UI para explorar y probar los endpoints.

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI Spec (JSON)**: http://localhost:8080/v3/api-docs

Desde la interfaz de Swagger, podrás ver todos los endpoints disponibles, sus parámetros, y ejecutar peticiones directamente desde el navegador.

## Guía de Endpoints de la API

A continuación se muestran ejemplos de cómo interactuar con cada endpoint usando `curl`.

### 1. Crear una Tarea

- **Endpoint**: `POST /api/v1/tareas`
- **Descripción**: Crea una nueva tarea.
- **Ejemplo de `curl`**:
  ```bash
  curl -X POST http://localhost:8080/api/v1/tareas \
  -H "Content-Type: application/json" \
  -d '{
      "nombreTarea": "Diseñar la base de datos",
      "descripcion": "Crear el diagrama entidad-relación para el nuevo módulo.",
      "estadoDeTarea": "PENDIENTE",
      "prioridad": "ALTA",
      "responsable": "Ana García",
      "fechasAsociadas": "2025-01-01"
  }'
  ```

### 2. Obtener Todas las Tareas

- **Endpoint**: `GET /api/v1/tareas`
- **Descripción**: Devuelve una lista de todas las tareas existentes.
- **Ejemplo de `curl`**:
  ```bash
  curl -X GET http://localhost:8080/api/v1/tareas
  ```

### 3. Obtener Tarea por ID

- **Endpoint**: `GET /api/v1/tareas/{id}`
- **Descripción**: Busca y devuelve una tarea por su ID.
- **Ejemplo de `curl`** (para la tarea con ID 1):
  ```bash
  curl -X GET http://localhost:8080/api/v1/tareas/1
  ```

### 4. Obtener Tarea por Nombre

- **Endpoint**: `GET /api/v1/tareas/tarea-especifica/{nombreTarea}`
- **Descripción**: Busca una tarea por su nombre exacto.
- **Ejemplo de `curl`**:
  ```bash
  curl -X GET "http://localhost:8080/api/v1/tareas/tarea-especifica/Diseñar la base de datos"
  ```

### 5. Obtener Tareas por Prioridad

- **Endpoint**: `GET /api/v1/tareas/prioridad/{prioridad}`
- **Descripción**: Devuelve una lista de tareas que coinciden con la prioridad especificada.
- **Ejemplo de `curl`** (para prioridad "Alta"):
  ```bash
  curl -X GET http://localhost:8080/api/v1/tareas/prioridad/Alta
  ```

### 6. Actualizar una Tarea

- **Endpoint**: `PUT /api/v1/tareas/{id}`
- **Descripción**: Modifica una tarea existente identificada por su ID.
- **Ejemplo de `curl`** (para actualizar la tarea con ID 1):
  ```bash
  curl -X PUT http://localhost:8080/api/v1/tareas/1 \
  -H "Content-Type: application/json" \
  -d '{
      "nombreTarea": "Diseñar la base de datos v2",
      "descripcion": "Crear el diagrama entidad-relación para el nuevo módulo.",
      "estadoDeTarea": "EN_PROGRESO",
      "prioridad": "MEDIA",
      "responsable": "Ana García",
      "fechasAsociadas": "2025-01-20"
  }'
  ```

### 7. Eliminar una Tarea

- **Endpoint**: `DELETE /api/v1/tareas/{id}`
- **Descripción**: Elimina una tarea por su ID.
- **Ejemplo de `curl`** (para eliminar la tarea con ID 1):
  ```bash
  curl -X DELETE http://localhost:8080/api/v1/tareas/1
  ```
