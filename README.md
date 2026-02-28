# Alquimia Literaria

Insentivar a la gente a entrar a un mundo de fantasías desde sus cómodos hogares.

---

## Introducción / Contexto

- Facilitar la accesibilidad a la lectura de libros mediante una plataforma digital intuitiva y moderna.  
- **Justificación:** Este proyecto es relevante porque fomenta el hábito lector en entornos digitales, aporta valor educativo al facilitar el acceso a contenido literario y permite aplicar conocimientos de desarrollo de software moderno en un contexto real.  
- **Dominio:** Plataforma web de lectura digital donde los usuarios podrán explorar libros, organizarlos y leerlos desde cualquier dispositivo.

---

## Objetivos

**Objetivo General**  
Desarrollar una plataforma web que facilite el acceso a la lectura digital mediante una experiencia intuitiva, accesible y atractiva para los usuarios.

---

**Objetivos Específicos**  
- Diseñar una arquitectura backend robusta basada en Spring Boot y arquitectura por capas.
- Implementar autenticación y gestión de usuarios.
- Crear un catálogo digital de libros con funcionalidades de búsqueda y filtrado.
- Desarrollar una interfaz frontend moderna y responsiva.
- Integrar una base de datos relacional para almacenamiento persistente de la información.

---

## Alcance del Proyecto (Scope)

**Qué se va a desarrollar:**  
- Registro e inicio de sesión de usuarios.  
- Catálogo digital de libros.
- Búsqueda y filtrado por título, autor o género.
- Vista detallada de cada libro.
- Sistema básico de lectura dentro de la plataforma.
- Panel administrativo básico para gestión de libros.
- API REST documentada.

---

**Qué NO se va a desarrollar en esta versión (fuera de alcance):**  
- Sistema de pagos o suscripciones.  
- Version Aplicacion movil.  
- Lectura offline.  
- Recomendaciones avanzadas con inteligencia artificial.
- Funcionalidades sociales (comentarios o comunidad).
- Audiolibros.

---

## Tecnologías y Herramientas (Tech Stack)

- **Backend:** Spring Boot 3.x, Java 21, Spring Data JPA, PostgreSQL
- **Frontend:** React
- **Base de datos:** Prisma, PostgreSQL en producción / H2
- **Otras herramientas:** Git, GitHub, Postman, Swagger, Docker (posible implementación futura)

---

## Integrantes del Equipo

| Nombre                  | Rol principal              | Usuario GitHub     |
|-------------------------|----------------------------|--------------------|
| Santiago Sánchez Rojas  | Líder / Backend            | @piolin666satan    |
| Santiago Zapata Villada | Frontend Lead              | @SantiagoZVcesde   |
| [Nombre 3]              | Backend / Base de datos    | @[usuario]         |
| [Nombre 4]              | [rol]                      | @[usuario]         |

---

## Diagrama de Clases del Dominio (v1)

![Diagrama de Dominio v1](docs/diagrama-dominio-v1.png)  
*Diagrama inicial del modelo de dominio – versión 1. Se actualizará en futuras entregas.*

---

## 🚀 Instrucciones de Instalación y Ejecución (para desarrolladores)

Sigue estos pasos para configurar el entorno de desarrollo y ejecutar el proyecto "Biblioteca Digital Grupo 6" localmente.

### Prerrequisitos

Asegúrate de tener instalado lo siguiente en tu máquina:

*   **Node.js:** Versión 18 o superior. Verifica con `node -v`.
*   **npm** o **yarn:** Gestor de paquetes. Verifica con `npm -v` o `yarn -v`.
*   **PostgreSQL:** Versión 14 o superior (necesaria para correr la base de datos localmente).
*   **Git:** Para clonar el repositorio. Verifica con `git --version`.
*   **(Opcional) IDE:** Como VS Code (recomendado para proyectos con Prisma).

### Pasos para la Instalación

1.  **Clonar el repositorio**
    Abre tu terminal y ejecuta el siguiente comando:
    ```bash
    git clone https://github.com/piolin666satan/biblioteca-digital-grupo-6.git
    ```

2.  **Acceder al directorio del proyecto**
    ```bash
    cd biblioteca-digital-grupo-6
    ```

3.  **Instalar dependencias**
    ```bash
    npm install
    ```
    o si usas yarn:
    ```bash
    yarn install
    ```

4.  **Configurar la base de datos con Prisma**
    Prisma necesita una URL de conexión para acceder a tu base de datos PostgreSQL. Crea un archivo `.env` en la raíz del proyecto (si no existe) y añade las siguiente líneas:

    ```env
   DB_URL=jdbc:postgresql://localhost:5432/tu_base_de_datos
   DB_USERNAME=tu_usuario
   DB_PASSWORD=tu_contraseña
    ```
5.  **Sincronizar el esquema de Prisma con la base de datos**
    Una vez configurada la URL, ejecuta el siguiente comando para crear las tablas en tu base de datos según el esquema de Prisma:

    ```bash
    npx prisma db push
    ```

    > Este comando sincroniza tu esquema de Prisma con la base de datos sin crear migraciones. Si prefieres usar migraciones, puedes ejecutar:
    > ```bash
    > npx prisma migrate dev --name init
    > ```

6.  **Generar el cliente de Prisma**
    Para asegurarte de que el cliente de Prisma esté actualizado:
    ```bash
    npx prisma generate
    ```

7.  **(Opcional) Explorar la base de datos con Prisma Studio**
    Prisma incluye un explorador visual muy útil para ver y editar los datos:
    ```bash
    npx prisma studio
    ```
    Se abrirá automáticamente en `http://localhost:5555`

### Ejecutar la Aplicación

Una vez configurada la base de datos, puedes ejecutar la aplicación:

**Modo desarrollo:**
```bash
npm run dev