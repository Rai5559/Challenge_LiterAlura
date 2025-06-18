# Literalura

Literalura es una aplicación Java Spring Boot que permite buscar, guardar y consultar información de libros y autores a través de una API externa, almacenando los datos en una base de datos relacional. Ofrece tanto endpoints HTTP como un menú interactivo por consola para facilitar la interacción.

## Características principales
- **Búsqueda y guardado de libros**: Consulta una API externa, almacena solo el primer autor y el primer idioma de cada libro.
- **Gestión de autores**: Permite buscar autores por nombre o por año de vida, mostrando solo los autores vivos en un año dado.
- **Persistencia**: Utiliza Spring Data JPA para almacenar libros y autores en una base de datos relacional.
- **Interfaz de consola**: Menú interactivo que nunca se detiene ante errores, mostrando mensajes claros y amigables.
- **Endpoints HTTP**: Controladores REST para acceder a la información desde clientes externos.
- **Código modular y mantenible**: Separación clara entre DTOs, modelos, servicios, controladores, utilidades y repositorios.
- **Manejo robusto de errores**: Todos los errores se capturan y muestran mensajes amigables, nunca excepciones crudas.

## Estructura del proyecto
```
├── src/
│   ├── main/
│   │   ├── java/com/rai69/literalura/
│   │   │   ├── LiterAluraApplication.java      # Main y menú consola
│   │   │   ├── controller/                    # Controladores REST
│   │   │   ├── dto/                           # DTOs para API y persistencia
│   │   │   ├── model/                         # Entidades JPA
│   │   │   ├── repository/                    # Repositorios JPA
│   │   │   ├── service/                       # Lógica de negocio
│   │   │   └── util/                          # Utilidades (API, env, mapeo)
│   │   └── resources/
│   │       └── application.properties         # Configuración Spring Boot
│   └── test/                                  # Pruebas unitarias
├── pom.xml                                    # Dependencias Maven
└── README.md                                  # Este archivo
```

## Flujo de la aplicación
1. **Inicio**: Al ejecutar la app, se muestra un menú por consola con opciones para buscar libros, autores, autores por año, etc.
2. **Búsqueda de libros**: El usuario ingresa el nombre de un libro, la app consulta la API externa, guarda el libro y su primer autor/idioma en la base de datos.
3. **Consulta de autores**: Se puede buscar un autor por nombre o listar todos los autores vivos en un año específico.
4. **Endpoints HTTP**: Se puede acceder a la información mediante peticiones HTTP a los controladores REST.
5. **Manejo de errores**: Si ocurre un error (dato no encontrado, año inválido, etc.), la app muestra un mensaje claro y nunca se detiene.

## Ejemplo de uso por consola
```
Seleccione una opción:
1. Buscar y guardar libro
2. Buscar autor
3. Buscar autores vivos en un año
0. Salir
```

## Endpoints principales
- `GET /books/book?title=...` — Busca y guarda un libro por título.
- `GET /authors/author?name=...` — Busca un autor por nombre.
- `GET /authors/authoryear?year=...` — Lista autores vivos en el año indicado.

## Tecnologías utilizadas
- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- H2/PostgreSQL/MySQL (configurable)
- Maven

## Ejecución
1. Clona el repositorio y entra a la carpeta del proyecto.
2. Ejecuta:
   ```
   ./mvnw spring-boot:run
   ```
3. Accede al menú por consola o usa los endpoints HTTP.

## Notas de diseño
- **Solo se almacena el primer autor y primer idioma de cada libro** para mantener la simplicidad.
- **Todos los servicios y controladores** están protegidos contra errores y nunca lanzan excepciones sin capturar.
- **El menú de consola** permite probar toda la funcionalidad sin necesidad de herramientas externas.

## Créditos
Desarrollado por [rai69](https://github.com/rai69) para el challenge de Alura Latam.

---
¡Disfruta explorando el mundo de los libros con Literalura!
