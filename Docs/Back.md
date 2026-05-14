# Arquitectura del Servidor

## Stack Tecnológico
- **Lenguaje**: Java 17+
- **Gestor de Dependencias**: Maven
- **Testing**: JUnit 5

## Estructura de Carpetas
- `com.techlab.UI`: Gestión de la interfaz CLI (`Main`, `MenuController`, `Menu`, `AdminMenu`, `ClientMenu`).
- `com.techlab.productos`: Jerarquía de productos y `ProductoFactory`.
- `com.techlab.pedidos`: Lógica de carrito y compras.
- `com.techlab.excepciones`: Excepciones personalizadas.
- `com.techlab.tienda`: Lógica central del sistema.

## Lógica de Negocio
- **Tienda**: Facade central para el dominio. Gestiona el inventario y las compras.
- **Venta Atómica**: El método `procesarCompra` valida el stock de todos los productos antes de realizar cualquier descuento.
- **MenuController**: Orquestador de la UI. Separa la lógica de entrada/salida de la lógica de dominio.
- **Jerarquía de Menús**: Implementación de patrón Command (`MenuAction`) para mapear opciones a acciones dinámicamente.

## Endpoints (Métodos de Tienda)
- `autenticar(email, pass)`: Retorna el usuario si las credenciales coinciden.
- `procesarCompra(carrito, usuario)`: Crea una compra y descuenta stock.
- `actualizarProducto(id, precio, stock, admin)`: Permite cambios parciales en productos existentes.
- `agregarProducto(producto, stock, stockMin, admin)`: Registra un nuevo producto en el catálogo.
- `buscarProductoPorIdONombre(query)`: Busca un producto por su ID numérico o su nombre exacto.
- `buscarProductos(termino)`: Retorna una lista de productos que contienen el término.
