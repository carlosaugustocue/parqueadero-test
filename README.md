
# Sistema de Parqueadero

## Descripción
El **Sistema de Parqueadero** es una aplicación web que permite gestionar el ingreso y salida de vehículos en un parqueadero. El sistema asigna espacios de parqueo disponibles a los vehículos, registra el tiempo de estancia y calcula las tarifas basadas en el tipo de vehículo (carro, moto, etc.).

## Funcionalidades
- **Registro de vehículos**: Permite registrar vehículos al ingresar al parqueadero.
- **Asignación de espacios**: Controla los espacios disponibles y asigna un espacio al vehículo.
- **Control del tiempo de estancia**: Calcula el tiempo que el vehículo ha permanecido en el parqueadero.
- **Cálculo de tarifas**: Genera el costo por el tiempo de permanencia según las tarifas definidas para cada tipo de vehículo.
- **Salida de vehículos**: Registra la salida del vehículo y libera el espacio de parqueo.

## Tecnologías utilizadas
- **Backend**: Java con Spring Boot
- **Frontend**: HTML, CSS, JavaScript (puede ser con un framework como React o Angular, si está planificado)
- **Persistencia**: JPA con Hibernate
- **Base de datos**: MySQL
- **Herramienta de compilación**: Maven
- **Servidor web embebido**: Tomcat (Spring Boot)

## Requisitos previos

- **JDK 17+** (Java 17 o superior)
- **Maven 3.6+** instalado
- **MySQL 5.7+** o **MySQL 8.0+**
- **Postman** (o cualquier otra herramienta para probar APIs REST)
- **Git** (para clonar el repositorio)

## Instalación y Configuración

1. **Clonar el repositorio**:

   ```bash
   git clone https://github.com/usuario/sistema-parqueadero.git
   cd sistema-parqueadero
   ```

2. **Configurar la base de datos**:
    - Crea una base de datos MySQL llamada `parqueadero`:

      ```sql
      CREATE DATABASE parqueadero;
      ```

    - Actualiza el archivo `src/main/resources/application.properties` con los datos de conexión de tu base de datos:

      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/parqueadero
      spring.datasource.username=tu_usuario
      spring.datasource.password=tu_contraseña
      spring.jpa.hibernate.ddl-auto=update
      spring.jpa.show-sql=true
      ```

3. **Compilar el proyecto**:

   Ejecuta el siguiente comando en la raíz del proyecto para compilar la aplicación:

   ```bash
   mvn clean install
   ```

4. **Ejecutar la aplicación**:

   Ejecuta la aplicación usando el siguiente comando:

   ```bash
   mvn spring-boot:run
   ```

5. **Acceder a la aplicación**:

   La aplicación estará corriendo en `http://localhost:8080`.


# API de Parqueadero - Documentación de Endpoints

Esta documentación describe los endpoints disponibles en la API de parqueadero. Los endpoints permiten registrar la entrada y salida de vehículos, y consultar los detalles de los mismos.

## Endpoints

### 1. Registrar la entrada de un vehículo
- **Método**: `POST`
- **URL**: `/api/vehiculos/entrada`
- **Descripción**: Registra la entrada de un vehículo al parqueadero. Verifica si el vehículo ya está presente y si hay espacio disponible.
- **Cuerpo de la solicitud (JSON)**:
  ```json
  {
    "placa": "ABC123",
    "tipoVehiculo": "Carro"
  }
  ```
- **Respuesta (JSON)**:
  ```json
  {
    "placa": "ABC123",
    "mensaje": "Vehículo registrado con éxito."
  }
  ```

### 2. Registrar la salida de un vehículo
- **Método**: `PUT`
- **URL**: `/api/vehiculos/salida/{placa}`
- **Descripción**: Registra la salida de un vehículo, calcula el costo de la estancia y elimina el vehículo del parqueadero.
- **Parámetro de consulta**:
    - `cobrarPorMinuto` (booleano): Indica si el cálculo se realiza por minuto (`true`) o por hora (`false`).
- **Ejemplo de URL**:
  ```http
  /api/vehiculos/salida/ABC123?cobrarPorMinuto=false
  ```
- **Respuesta (JSON)**:
  ```json
  {
    "placa": "ABC123",
    "mensaje": "Vehículo retirado. Costo total: $15,000.00 COP"
  }
  ```

### 3. Obtener detalles de un vehículo
- **Método**: `GET`
- **URL**: `/api/vehiculos/detalles/{placa}`
- **Descripción**: Devuelve detalles de un vehículo en el parqueadero, incluyendo la hora de ingreso y el valor facturado hasta el momento.
- **Parámetro de consulta**:
    - `cobrarPorMinuto` (booleano): Especifica si el cálculo del valor facturado se debe hacer por minuto (`true`) o por hora (`false`).
- **Ejemplo de URL**:
  ```http
  /api/vehiculos/detalles/ABC123?cobrarPorMinuto=true
  ```
- **Respuesta (JSON)**:
  ```json
  {
    "placa": "ABC123",
    "horaIngreso": "2024-11-04T14:00:00",
    "valorFacturado": "$12,000.00 COP"
  }
  ```

## Notas para el equipo de frontend
- **Formato de fechas**: La hora de ingreso se devuelve en formato ISO 8601 (`yyyy-MM-dd'T'HH:mm:ss`).
- **Valores facturados**: Los valores están formateados en pesos colombianos (COP) con comas y puntos decimales para facilitar la visualización.
- **Parámetro `cobrarPorMinuto`**: Permite al frontend especificar cómo calcular el valor de la estancia, ofreciendo flexibilidad según el contexto de uso.

## Ejemplos de Peticiones con `curl`

### 1. Registrar la entrada de un vehículo
```bash
curl -X POST http://localhost:8080/api/vehiculos/entrada \
-H "Content-Type: application/json" \
-d '{"placa": "ABC123", "tipoVehiculo": "Carro"}'
```

### 2. Registrar la salida de un vehículo
```bash
curl -X PUT "http://localhost:8080/api/vehiculos/salida/ABC123?cobrarPorMinuto=false"
```

### 3. Obtener detalles de un vehículo
```bash
curl -X GET "http://localhost:8080/api/vehiculos/detalles/ABC123?cobrarPorMinuto=true"
```

## Contacto
Para preguntas o más información, contacta al equipo de desarrollo backend.


## Licencia
Este proyecto está licenciado bajo la [MIT License](LICENSE).
