# [Alquimia Literaria]

[Una frase corta que resuma el propósito – máximo 15 palabras]

## Introducción / Contexto

- Descripción del problema que se busca resolver  
- Justificación: ¿por qué es relevante? (impacto social, académico, empresarial, etc.)  
- Breve descripción del dominio / temática del proyecto integrador

## Objetivos

**Objetivo General**  
[Redactar el objetivo general del proyecto integrador – una frase clara y concreta]

**Objetivos Específicos**  
- [OE1 – descripción clara]  
- [OE2 – descripción clara]  
- [OE3 – descripción clara]  
- [OE4 – descripción clara]  
(Mínimo 3–5 objetivos específicos)

## Alcance del Proyecto (Scope)

**Qué se va a desarrollar:**  
- [Listar módulos principales y funcionalidades clave previstas en el semestre]

**Qué NO se va a desarrollar en esta versión (fuera de alcance):**  
- [Listar explícitamente lo que se excluye intencionalmente]

## Tecnologías y Herramientas (Tech Stack)

- **Backend**: Spring Boot [versión exacta], Java [17 o 21], Spring Data JPA, [PostgreSQL / MySQL / H2]  
- **Frontend**: [tecnología elegida – React / Angular / Vue / etc.]  
- **Base de datos**: [PostgreSQL en producción / H2 en desarrollo inicial / etc.]  
- **Otras herramientas**: Git, GitHub, [Docker si se usará más adelante], [Postman / Swagger], etc.

## Integrantes del Equipo

| Nombre                  | Rol principal              | Usuario GitHub     |
|-------------------------|----------------------------|--------------------|
| [Nombre 1]              | Líder / Backend            | @[usuario]         |
| Santiago Zapata Villada | Frontend Lead              | @SantiagoZVcesde   |
| [Nombre 3]              | Backend / Base de datos    | @[usuario]         |
| [Nombre 4]              | [rol]                      | @[usuario]         |
| ...                     | ...                        | ...                |

## Diagrama de Clases del Dominio (v1)

![Diagrama de Dominio v1](docs/diagrama-dominio-v1.png)  
*Diagrama inicial del modelo de dominio – versión 1. Se actualizará en futuras entregas.*

## Instrucciones de Instalación y Ejecución (para desarrolladores)

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
    Prisma necesita una URL de conexión para acceder a tu base de datos PostgreSQL. Crea un archivo `.env` en la raíz del proyecto (si no existe) y añade la siguiente línea:

    ```env
    > *   `{DB_URL}`: Lo sacas de la url de prisma.
    > *   `{DB_USERNAME}`: Lo sacas de la url de prisma.
    > *   `{DB_PASSWORD}`: Lo sacas de la url de prisma.
    ```
    
   ``` Ejemplo de cómo se ve una URL de Prisma:
    > *  `DB_URL=jdbc:postgresql://localhost:5432/tu_base_de_datos`
    > *  `DB_USERNAME=tu_usuario`
    > *  `DB_PASSWORD=tu_contraseña`
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
