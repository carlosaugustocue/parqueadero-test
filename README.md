
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

## Endpoints de la API

### 1. Registrar la entrada de un vehículo (POST)
- **Endpoint**: `/api/vehiculos`
- **Método**: `POST`
- **Body** (JSON):

  ```json
  {
    "placa": "ABC123",
    "tipoVehiculo": "carro"
  }
  ```

- **Descripción**: Este endpoint registra un nuevo vehículo en el parqueadero y asigna un espacio si está disponible.

### 2. Registrar la salida de un vehículo (PUT)
- **Endpoint**: `/api/vehiculos/salida/{placa}`
- **Método**: `PUT`
- **Descripción**: Registra la salida de un vehículo y calcula el costo según el tiempo de estancia.

### 3. Obtener todas las tarifas (GET)
- **Endpoint**: `/tarifas`
- **Método**: `GET`
- **Descripción**: Devuelve una lista de todas las tarifas registradas.

## Ejemplo de Pruebas

### 1. Probar con Postman:

- **Registrar vehículo**:
    - Método: `POST`
    - URL: `http://localhost:8080/api/vehiculos`
    - Body:
      ```json
      {
        "placa": "XYZ789",
        "tipoVehiculo": "moto"
      }
      ```

- **Registrar salida de vehículo**:
    - Método: `PUT`
    - URL: `http://localhost:8080/api/vehiculos/salida/XYZ789`

### 2. Verificar las tarifas:

- Método: `GET`
- URL: `http://localhost:8080/tarifas`

## Ejecución de Pruebas

Puedes ejecutar las pruebas del proyecto con Maven usando el siguiente comando:

```bash
mvn test
```

## Licencia
Este proyecto está licenciado bajo la [MIT License](LICENSE).
