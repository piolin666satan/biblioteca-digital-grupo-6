# 📚 Catálogo de Peticiones API - Alquimia Literaria

---

## 1. 🛒 Compras

> **Nota:** Estructura base validada para evitar el Error 400 por campos incompletos.

### ➤ POST `/api/compras`  
**Crear una compra**

```json
{
  "proveedor": "Editorial Planeta",
  "monto": 45000.50
}
```

### ➤ GET `/api/compras/{id}`  
**Ejemplo de respuesta esperada**

```json
{
  "id": 1,
  "proveedor": "Editorial Planeta",
  "monto": 45000.50,
  "fechaCompra": "2026-05-24T12:00:00"
}
```

---

## 2. 📖 Préstamos

> **Nota:** Ajustado para usar `diaPrestamo` y permitir que el backend calcule las fechas automáticamente.

### ➤ POST `/api/prestamos`  
**Crear un préstamo**

```json
{
  "clienteId": 1,
  "libroId": 2,
  "diaPrestamo": 7
}
```

### ➤ GET `/api/prestamos/1`  
**Respuesta con datos relacionales listos para React**

```json
{
  "id": 1,
  "nombreCliente": "Administrador Sistema",
  "tituloLibro": "El Aleph",
  "fechaSalida": "2026-05-24T13:11:17.5820715",
  "fechaDevolucionEsperada": "2026-05-31T13:11:17.5820715",
  "estado": "ACTIVO"
}
```

---

## 3. 👤 Clientes

> **Nota:** Incluye la variable temporal de `password` visible para pruebas locales.

### ➤ POST `/api/clientes`  
**Registrar un cliente nuevo**

```json
{
  "nombre": "derek",
  "apellido": "naelson",
  "tipoIdentidad": "CEDULA",
  "numeroIdentidad": "13456770",
  "email": "prueba12347@gmail.com",
  "direccion": "Calle 123 #45-69",
  "telefono": "588-1234",
  "password": "MiClaveSuperSecreta123"
}
```

### ➤ GET `/api/clientes/7`  
**Respuesta mapeada incluyendo el password crudo**

```json
{
  "id": 7,
  "nombre": "derek",
  "apellido": "naelson",
  "tipoIdentidad": "CEDULA",
  "numeroIdentidad": "13456770",
  "email": "prueba12347@gmail.com",
  "direccion": "Calle 123 #45-69",
  "telefono": "588-1234",
  "password": "MiClaveSuperSecreta123"
}
```

---

## 🚀 Notas Finales

- Todas las rutas siguen el prefijo base `/api`
- Las respuestas están pensadas para integrarse fácilmente con frontend en React
- Validar siempre los datos antes de enviarlos al backend
