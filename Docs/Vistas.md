# Flujo UX (Vistas CLI)

## 1. Inicio
- **Opciones**: Iniciar Sesión, Crear Cuenta, Salir.
- **Acción**: Redirige al menú principal tras autenticación exitosa.

## 2. Menú Administrador
- **1) Agregar producto**: Abre formulario de creación. Redirige a Listar Productos.
- **2) Listar productos**: Muestra tabla de stock.
- **3) Buscar productos**: Permite buscar por término (nombre/marca/descripción).
- **4) Buscar/Actualizar**: Permite editar precio/stock de un item por ID/Nombre.
- **5) Eliminar producto**: Borra por ID tras confirmación.
- **6) Listar TODOS los pedidos**: Historial global con IDs visibles.

## 3. Menú Cliente
- **1) Ver catálogo**: Muestra productos disponibles.
- **2) Buscar productos**: Búsqueda por término (nombre/marca/descripción).
- **3) Realizar una compra**: Flujo interactivo de carrito -> confirmación.
- **4) Ver mis pedidos**: Historial personal del cliente logueado.

## 4. Sesión
- **Cerrar Sesión**: Limpia `usuarioLogueado` y vuelve al Inicio.
