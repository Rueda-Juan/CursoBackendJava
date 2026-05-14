# Documentación de Base de Datos (Memoria)

Como aplicación de consola, actualmente se utiliza persistencia en memoria y carga inicial desde archivos CSV.

## Entidades Principales

### Producto
- **id**: Integer (PK)
- **nombre**: String
- **precio**: Double
- **descripcion**: String
- **marca**: String

### ProductoTienda (Stock)
- **producto**: Producto (FK)
- **stockActual**: Integer
- **stockMinimo**: Integer

### Usuario
- **id**: Integer (PK)
- **email**: String (Unique)
- **password**: String
- **rol**: Enum (ADMIN, CLIENTE)
- **nombre**: String

### Compra
- **id**: Integer (PK)
- **fecha**: LocalDateTime
- **total**: Double
- **detalles**: List<DetalleCompra>

### DetalleCompra
- **producto**: Producto (FK)
- **cantidad**: Integer
- **precioUnitario**: Double
