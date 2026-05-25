# 📚 Catálogo de Peticiones API — Alquimia Literaria
 
---
 
## Árbol de Dependencias
 
```
Categorías
    └── Libros
            └── Préstamos
Clientes ──────┘
Compras (independiente)
```
 
> Un nivel superior debe existir antes de que el inferior pueda crearse.
 
---
 
## 1. Módulo de Categorías
 
> Entidad padre. No depende de ningún otro módulo.
 
### `POST /api/categorias`
 
Crea una nueva categoría.
 
```json
{
  "nombre": "Ciencia Ficción",
  "descripcion": "Libros sobre futuros posibles y tecnología"
}
```
 
---
 
### `GET /api/categorias/{id}`
 
Obtiene una categoría por su ID.
 
**Respuesta:**
 
```json
{
  "id": 1,
  "nombre": "Ciencia Ficción",
  "descripcion": "Libros sobre futuros posibles y tecnología"
}
```
 
---
 
## 2. Módulo de Libros
 
> Depende de la existencia previa de una **Categoría**.
 
### `POST /api/libros`
 
Crea un nuevo libro. Asume que el ID de la categoría ya creada es `1`.
 
```json
{
  "titulo": "Meridiano de sangre",
  "autor": "Mc Macarty",
  "isbn": "978-123468958",
  "editorial": "Debolsillo",
  "estado": "DISPONIBLE",
  "precio": 35000.0,
  "cantidad": 1,
  "categoriaId": 1 
}
```
 
---
 
### `GET /api/libros/{id}`
 
Obtiene un libro por su ID.
 
**Respuesta:**
 
```json
{
  "id": 2,
  "titulo": "Meridiano de sangre",
  "autor": "Mc Macarty",
  "isbn": "978-123468958",
  "editorial": "Debolsillo",
  "estado": "DISPONIBLE",
  "precio": 35000.0,
  "cantidad": 1,
  "categoriaId": 1 
}
```
 
---
 
## 3. Módulo de Clientes
 
> Entidad independiente. Creados para interactuar con el sistema.
 
### `POST /api/clientes`
 
Registra un nuevo cliente.
 
```json
{
  "nombre": "derek",
  "apellido": "naelson",
  "tipoIdentidad": "CEDULA",
  "numeroIdentidad": "13456770",
  "email": "prueba12347@gmail.com",
  "direccion": "Calle 123 #45-69",
  "telefono": "588-1234",
  "password": "Password12368!"
}
```
 
> ⚠️ El campo `password` debe ser encriptado antes de almacenarse en base de datos.
 
---
 
## 4. Módulo de Compras
 
> Ingreso de inventario. Entidad independiente.
 
### `POST /api/compras`
 
Registra una nueva compra a proveedor.
 
```json
{
  "clienteId": 1,
  "libroId": 2,
  "cantidad": 5,
  "proveedor": "Editorial Emecé S.A.",
  "monto": 175000.0
}
```
 
---
 
## 5. Módulo de Préstamos
 
> Eslabón final. Depende de **Clientes** y **Libros**.
 
### `POST /api/prestamos`
 
Registra un nuevo préstamo.
 
```json
{
  "clienteId": 1,
  "libroId": 2,
  "diaPrestamo": 7
}
```
 
---
 
## 💡 Orden de creación recomendado
 
| Paso | Módulo     | Dependencia previa        |
|------|------------|---------------------------|
| 1    | Categorías | Ninguna                   |
| 2    | Libros     | Categorías                |
| 3    | Clientes   | Ninguna                   |
| 4    | Compras    | Ninguna                   |
| 5    | Préstamos  | Clientes + Libros         |
