package com.techlab.UI;

import com.techlab.pedidos.Carrito;
import com.techlab.pedidos.Compra;
import com.techlab.productos.Producto;
import com.techlab.productos.ProductoTienda;
import com.techlab.tienda.Tienda;
import com.techlab.usuario.Usuario;
import com.techlab.utils.CONST;
import com.techlab.utils.InputScanner;
import com.techlab.utils.ProductoFactory;
import com.techlab.excepciones.ProductoNoEncontradoException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
public class MenuController {
    private final Tienda tienda;
    private Usuario usuarioLogueado;
    private int nextId = 1;
    public MenuController(Tienda tienda) {
        this.tienda = tienda;
    }
    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }
    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioLogueado = usuario;
    }
    public void iniciarSesion() {
        System.out.println("\n" + CONST.YELLOW_BOLD + "--- Inicio de Sesión ---" + CONST.RESET);
        String email = InputScanner.readString("Email: ");
        String pass = InputScanner.readString("Password: ");
        Optional<Usuario> user = tienda.autenticar(email, pass);
        if (user.isPresent()) {
            usuarioLogueado = user.get();
            System.out.println(CONST.GREEN + "Bienvenido, " + usuarioLogueado.getNombre() + " [" + usuarioLogueado.getRol() + "]" + CONST.RESET);
            return;
        }
        System.out.println(CONST.RED + "Credenciales incorrectas." + CONST.RESET);
    }
    public void registrarUsuario() {
        System.out.println("\n" + CONST.MAGENTA_BOLD + "--- Crear Nueva Cuenta ---" + CONST.RESET);
        String nombre = InputScanner.readString("Nombre Completo: ");
        String email = InputScanner.readString("Email: ");
        String pass = InputScanner.readString("Password: ");
        int id = tienda.getUsuarios().size() + 1;
        Usuario nuevo = new Usuario(id, email, pass, "CLIENTE", nombre);
        tienda.registrarUsuario(nuevo);
        System.out.println(CONST.GREEN + "Cuenta creada con éxito. Ahora puedes iniciar sesión." + CONST.RESET);
    }
    public void cerrarSesion() {
        this.usuarioLogueado = null;
        System.out.println(CONST.YELLOW + "Sesión cerrada." + CONST.RESET);
    }
    public void agregarProducto() {
        System.out.println("\n" + CONST.GREEN_BOLD + "--- Agregar Producto ---" + CONST.RESET);
        System.out.println("Tipo: 1) Genérico, 2) Bebida, 3) Ropa, 4) Perecedero");
        int tipo = InputScanner.readInt("Seleccione tipo: ");
        String nombre = InputScanner.readString("Nombre: ");
        String marca = InputScanner.readString("Marca: ");
        double precio = InputScanner.readDouble("Precio: ");
        String desc = InputScanner.readString("Descripción: ");
        int stock = InputScanner.readInt("Stock inicial: ");
        int stockMin = InputScanner.readInt("Stock mínimo: ");
        try {
            Object[] extraParams = switch (tipo) {
                case 2 -> new Object[]{InputScanner.readInt("Mililitros: "), InputScanner.readBoolean("¿Es alcohólica?")};
                case 3 -> new Object[]{InputScanner.readString("Talle: "), InputScanner.readString("Color: ")};
                case 4 -> {
                    String fechaStr = InputScanner.readString("Fecha de caducidad (YYYY-MM-DD): ");
                    yield new Object[]{LocalDate.parse(fechaStr)};
                }
                default -> new Object[]{};
            };
            int id = tienda.getProductos().size() + 100 + nextId++;
            Producto p = ProductoFactory.crearProducto(tipo, id, nombre, precio, desc, marca, extraParams);
            tienda.agregarProducto(p, stock, stockMin, usuarioLogueado);
            System.out.println(CONST.GREEN + "Producto agregado con éxito." + CONST.RESET);
        } catch (Exception e) {
            System.out.println(CONST.RED + "Error al crear producto: " + e.getMessage() + CONST.RESET);
        }
    }
    public void listarProductos() {
        System.out.println("\n" + CONST.MAGENTA_BOLD + "--- Listado de Productos ---" + CONST.RESET);
        if (tienda.getProductos().isEmpty()) {
            System.out.println(CONST.YELLOW + "No hay productos registrados." + CONST.RESET);
        } else {
            tienda.getProductos().forEach(System.out::println);
        }
    }
    public void buscarProductosUI() {
        System.out.println("\n" + CONST.CYAN_BOLD + "--- Búsqueda de Productos ---" + CONST.RESET);
        String termino = InputScanner.readString("Ingrese término de búsqueda: ");
        try {
            List<ProductoTienda> resultados = tienda.buscarProductos(termino);
            System.out.println(CONST.GREEN + "Resultados encontrados:" + CONST.RESET);
            resultados.forEach(System.out::println);
        } catch (ProductoNoEncontradoException e) {
            System.out.println(CONST.RED + e.getMessage() + CONST.RESET);
        }
    }
    public void buscarActualizarProducto() {
        System.out.println("\n" + CONST.CYAN_BOLD + "--- Buscar/Actualizar ---" + CONST.RESET);
        String busqueda = InputScanner.readString("Ingrese ID o Nombre: ");
        ProductoTienda ptFound = tienda.buscarProductoPorIdONombre(busqueda).orElse(null);
        if (ptFound == null) {
            System.out.println(CONST.RED + "Producto no encontrado." + CONST.RESET);
            return;
        }
        System.out.println(CONST.WHITE + "Encontrado: " + CONST.RESET + ptFound);
        String rta = InputScanner.readString("¿Desea actualizar stock o precio? (s/n): ");
        if (rta.equalsIgnoreCase("s")) {
            try {
                double nuevoPrecio = InputScanner.readDouble("Nuevo precio (actual: " + ptFound.getProducto().getPrecio() + "): ");
                int nuevoStock = InputScanner.readInt("Nuevo stock (actual: " + ptFound.getStockActual() + "): ");
                tienda.actualizarProducto(ptFound.getProducto().getId(), nuevoPrecio, nuevoStock, usuarioLogueado);
                System.out.println(CONST.GREEN + "Actualizado con éxito." + CONST.RESET);
            } catch (Exception e) {
                System.out.println(CONST.RED + "Error al actualizar: " + e.getMessage() + CONST.RESET);
            }
        }
    }
    public void eliminarProducto() {
        int id = InputScanner.readInt("Ingrese ID a eliminar: ");
        try {
            if (tienda.eliminarProducto(id, usuarioLogueado)) {
                System.out.println(CONST.GREEN + "Eliminado correctamente." + CONST.RESET);
            } else {
                System.out.println(CONST.RED + "No se encontró el producto." + CONST.RESET);
            }
        } catch (Exception e) {
            System.out.println(CONST.RED + e.getMessage() + CONST.RESET);
        }
    }
    public void crearPedido() {
        System.out.println("\n" + CONST.YELLOW_BOLD + "--- Crear Pedido ---" + CONST.RESET);
        Carrito carrito = new Carrito();
        boolean agregarMas = true;
        while (agregarMas) {
            listarProductos();
            String query = InputScanner.readString("ID o Nombre del producto: ");
            ProductoTienda pt = tienda.buscarProductoPorIdONombre(query).orElse(null);
            if (pt == null) {
                System.out.println(CONST.RED + "ID no existe." + CONST.RESET);
            } else {
                int cant = InputScanner.readInt("Cantidad: ");
                carrito.agregarProducto(pt.getProducto(), cant);
                System.out.println(CONST.GREEN + "Agregado al carrito temporal." + CONST.RESET);
            }
            agregarMas = InputScanner.readBoolean("¿Agregar otro?");
        }
        if (!carrito.getItems().isEmpty()) {
            System.out.println("\n" + carrito);
            if (InputScanner.readBoolean("¿Confirmar compra?")) {
                try {
                    Compra c = tienda.procesarCompra(carrito, usuarioLogueado);
                    System.out.println(CONST.GREEN_BOLD + "Pedido confirmado! ID: " + c.getId() + " | Total: $" + c.getTotal() + CONST.RESET);
                } catch (Exception e) {
                    System.out.println(CONST.RED + "Error al procesar: " + e.getMessage() + CONST.RESET);
                }
            }
        }
    }
    public void listarPedidos() {
        System.out.println("\n" + CONST.BLUE_BOLD + "--- Historial Global de Pedidos ---" + CONST.RESET);
        if (tienda.getHistorialCompras().isEmpty()) {
            System.out.println(CONST.YELLOW + "No hay pedidos realizados." + CONST.RESET);
        } else {
            mostrarCompras(tienda.getHistorialCompras());
        }
    }
    public void listarMisPedidos() {
        System.out.println("\n" + CONST.BLUE_BOLD + "--- Mis Pedidos ---" + CONST.RESET);
        if (usuarioLogueado.getCompras().isEmpty()) {
            System.out.println(CONST.YELLOW + "Aún no has realizado compras." + CONST.RESET);
        } else {
            mostrarCompras(usuarioLogueado.getCompras());
        }
    }
    private void mostrarCompras(List<Compra> lista) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        boolean esAdmin = usuarioLogueado.isAdmin();
        for (Compra c : lista) {
            String header = esAdmin ? "Pedido #" + c.getId() : "Pedido";
            System.out.println(CONST.CYAN + header + " | Fecha: " + c.getFecha().format(dtf) + CONST.RESET);
            c.getDetalles().forEach(d -> System.out.println("  - " + d));
            System.out.println(CONST.GREEN_BOLD + "  TOTAL: $" + c.getTotal() + CONST.RESET);
            System.out.println("-----------------------------");
        }
    }
}

