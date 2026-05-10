package com.techlab;

import com.techlab.productos.*;
import com.techlab.pedidos.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Tienda tienda = new Tienda("Supermercado Java");
    private static List<Compra> compras = new ArrayList<>();
    private static List<Usuario> usuarios = new ArrayList<>();
    private static Usuario usuarioLogueado = null;
    private static int nextId = 1;

    static {
        // Usuarios por defecto
        usuarios.add(new Usuario(1, "admin", "1234", "ADMIN", "Administrador"));
        usuarios.add(new Usuario(2, "cliente@techlab.com", "user123", "CLIENTE", "Juan Cliente"));
    }

    public static void main(String[] args) {
        // Carga automática desde CSV
        LectorCSV.cargarProductos(tienda, "src/main/resources/productos.csv");

        System.out.println(CONST.CYAN_BOLD + "BIENVENIDO A TECHLAB SYSTEM" + CONST.RESET);

        while (usuarioLogueado == null) {
            System.out.println("\n1) Iniciar Sesión | 2) Crear Cuenta | 3) Salir");
            int modo = InputScanner.readInt("Seleccione: ");
            if (modo == 1) {
                iniciarSesion();
            } else if (modo == 2) {
                registrarUsuario();
            } else if (modo == 3) {
                System.out.println("¡Adiós!");
                System.exit(0);
            }
        }

        boolean salir = false;
        while (!salir) {
            try {
                printMenu();
                int opcion = InputScanner.readInt("Elija una opción: ");

                if (usuarioLogueado.getRol().equals("ADMIN")) {
                    switch (opcion) {
                        case 1 -> agregarProducto();
                        case 2 -> listarProductos();
                        case 3 -> buscarActualizarProducto();
                        case 4 -> eliminarProducto();
                        case 5 -> listarPedidos();
                        case 6 -> {
                            usuarioLogueado = null;
                        }
                        case 7 -> salir = true;
                        default -> System.out.println(CONST.RED + "Opción no válida." + CONST.RESET);
                    }
                } else {
                    switch (opcion) {
                        case 1 -> listarProductos();
                        case 2 -> crearPedido();
                        case 3 -> listarMisPedidos();
                        case 4 -> {
                            usuarioLogueado = null;
                        }
                        case 5 -> salir = true;
                        default -> System.out.println(CONST.RED + "Opción no válida." + CONST.RESET);
                    }
                }

                // Si cerró sesión, volver al menú de inicio
                if (usuarioLogueado == null && !salir) {
                    while (usuarioLogueado == null) {
                        System.out.println("\n1) Iniciar Sesión | 2) Crear Cuenta | 3) Salir");
                        int modo = InputScanner.readInt("Seleccione: ");
                        if (modo == 1)
                            iniciarSesion();
                        else if (modo == 2)
                            registrarUsuario();
                        else if (modo == 3)
                            System.exit(0);
                    }
                }
            } catch (com.techlab.excepciones.ScannerException e) {
                System.out.println(CONST.RED + "Error de entrada: " + e.getMessage() + CONST.RESET);
            }
        }
        System.out.println(CONST.CYAN_BOLD + "¡Gracias por usar el sistema!" + CONST.RESET);
    }

    private static void registrarUsuario() {
        System.out.println("\n" + CONST.MAGENTA_BOLD + "--- Crear Nueva Cuenta ---" + CONST.RESET);
        String nombre = InputScanner.readString("Nombre Completo: ");
        String email = InputScanner.readString("Email: ");
        String pass = InputScanner.readString("Password: ");

        // El registro por defecto es siempre para clientes
        Usuario nuevo = new Usuario(usuarios.size() + 1, email, pass, "CLIENTE", nombre);
        usuarios.add(nuevo);
        System.out.println(CONST.GREEN + "Cuenta creada con éxito. Ahora puedes iniciar sesión." + CONST.RESET);
    }

    private static void iniciarSesion() {
        System.out.println("\n" + CONST.YELLOW_BOLD + "--- Inicio de Sesión ---" + CONST.RESET);
        String email = InputScanner.readString("Email: ");
        String pass = InputScanner.readString("Password: ");

        for (Usuario u : usuarios) {
            if (u.login(email, pass)) {
                usuarioLogueado = u;
                System.out
                        .println(CONST.GREEN + "Bienvenido, " + u.getNombre() + " [" + u.getRol() + "]" + CONST.RESET);
                return;
            }
        }
        System.out.println(CONST.RED + "Credenciales incorrectas." + CONST.RESET);
    }

    private static void printMenu() {
        System.out.println("\n" + CONST.BLUE_BACKGROUND + CONST.WHITE_BOLD + "      MENÚ " + usuarioLogueado.getRol()
                + "      " + CONST.RESET);
        if (usuarioLogueado.getRol().equals("ADMIN")) {
            System.out.println(CONST.BLUE + "1)" + CONST.RESET + " Agregar producto");
            System.out.println(CONST.BLUE + "2)" + CONST.RESET + " Listar productos");
            System.out.println(CONST.BLUE + "3)" + CONST.RESET + " Buscar/Actualizar producto");
            System.out.println(CONST.BLUE + "4)" + CONST.RESET + " Eliminar producto");
            System.out.println(CONST.BLUE + "5)" + CONST.RESET + " Listar TODOS los pedidos");
            System.out.println(CONST.BLUE + "6)" + CONST.RESET + " Cerrar sesión");
            System.out.println(CONST.BLUE + "7)" + CONST.RESET + " Salir");
        } else {
            System.out.println(CONST.BLUE + "1)" + CONST.RESET + " Ver catálogo de productos");
            System.out.println(CONST.BLUE + "2)" + CONST.RESET + " Realizar una compra");
            System.out.println(CONST.BLUE + "3)" + CONST.RESET + " Ver mis pedidos");
            System.out.println(CONST.BLUE + "4)" + CONST.RESET + " Cerrar sesión");
            System.out.println(CONST.BLUE + "5)" + CONST.RESET + " Salir");
        }
    }

    private static void agregarProducto() {
        System.out.println("\n" + CONST.GREEN_BOLD + "--- Agregar Producto ---" + CONST.RESET);
        System.out.println("Tipo: 1) Genérico, 2) Bebida, 3) Ropa, 4) Perecedero");
        int tipo = InputScanner.readInt("Seleccione tipo: ");

        String nombre = InputScanner.readString("Nombre: ");
        String marca = InputScanner.readString("Marca: ");
        double precio = InputScanner.readDouble("Precio: ");
        String desc = InputScanner.readString("Descripción: ");
        int stock = InputScanner.readInt("Stock inicial: ");
        int stockMin = InputScanner.readInt("Stock mínimo: ");

        Producto p = null;
        switch (tipo) {
            case 1 -> p = new Producto(nextId++, nombre, precio, desc, marca);
            case 2 -> {
                int ml = InputScanner.readInt("Mililitros: ");
                boolean alcohol = InputScanner.readBoolean("¿Es alcohólica?");
                p = new Bebida(nextId++, nombre, precio, desc, marca, ml, alcohol);
            }
            case 3 -> {
                String talle = InputScanner.readString("Talle: ");
                String color = InputScanner.readString("Color: ");
                p = new Ropa(nextId++, nombre, precio, desc, marca, talle, color);
            }
            case 4 -> {
                try {
                    String fechaStr = InputScanner.readString("Fecha de caducidad (YYYY-MM-DD): ");
                    LocalDate fecha = LocalDate.parse(fechaStr);
                    p = new PoductoPerecedero(nextId++, nombre, precio, desc, marca, fecha);
                } catch (Exception e) {
                    System.out.println(CONST.RED + "Fecha inválida, se usará la fecha actual." + CONST.RESET);
                    p = new PoductoPerecedero(nextId++, nombre, precio, desc, marca, LocalDate.now());
                }
            }
            default -> System.out.println(CONST.RED + "Tipo inválido." + CONST.RESET);
        }

        if (p != null) {
            try {
                tienda.agregarProducto(p, stock, stockMin, usuarioLogueado);
                System.out.println(CONST.GREEN + "Producto agregado con éxito." + CONST.RESET);
            } catch (Exception e) {
                System.out.println(CONST.RED + e.getMessage() + CONST.RESET);
            }
        }
    }

    private static void listarProductos() {
        System.out.println("\n" + CONST.MAGENTA_BOLD + "--- Listado de Productos ---" + CONST.RESET);
        if (tienda.getProductos().isEmpty()) {
            System.out.println(CONST.YELLOW + "No hay productos registrados." + CONST.RESET);
        } else {
            tienda.getProductos().forEach(System.out::println);
        }
    }

    private static void buscarActualizarProducto() {
        System.out.println("\n" + CONST.CYAN_BOLD + "--- Buscar/Actualizar ---" + CONST.RESET);
        String busqueda = InputScanner.readString("Ingrese ID o Nombre: ");

        ProductoTienda ptFound = null;
        try {
            int id = Integer.parseInt(busqueda);
            ptFound = tienda.buscarProducto(id).orElse(null);
        } catch (NumberFormatException e) {
            ptFound = tienda.buscarProducto(busqueda).orElse(null);
        }

        if (ptFound == null) {
            System.out.println(CONST.RED + "Producto no encontrado." + CONST.RESET);
            return;
        }

        System.out.println(CONST.WHITE + "Encontrado: " + CONST.RESET + ptFound);
        String rta = InputScanner.readString("¿Desea actualizar stock o precio? (s/n): ");
        if (rta.equalsIgnoreCase("s")) {
            // Solo admin puede actualizar stock/precio
            if (!usuarioLogueado.getRol().equals("ADMIN")) {
                System.out.println(
                        CONST.RED + "Error: Solo el administrador puede modificar precios o stock." + CONST.RESET);
                return;
            }
            double nuevoPrecio = InputScanner
                    .readDouble("Nuevo precio (actual: " + ptFound.getProducto().getPrecio() + "): ");
            int nuevoStock = InputScanner.readInt("Nuevo stock (actual: " + ptFound.getStockActual() + "): ");
            ptFound.getProducto().setPrecio(nuevoPrecio);
            ptFound.setStockActual(nuevoStock);
            System.out.println(CONST.GREEN + "Actualizado con éxito." + CONST.RESET);
        }
    }

    private static void eliminarProducto() {
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

    private static void crearPedido() {
        System.out.println("\n" + CONST.YELLOW_BOLD + "--- Crear Pedido ---" + CONST.RESET);
        List<DetalleCompra> detalles = new ArrayList<>();
        boolean agregarMas = true;

        while (agregarMas) {
            listarProductos();
            int id = InputScanner.readInt("ID del producto: ");
            ProductoTienda pt = tienda.buscarProducto(id).orElse(null);

            if (pt == null) {
                System.out.println(CONST.RED + "ID no existe." + CONST.RESET);
            } else {
                int cant = InputScanner.readInt("Cantidad: ");
                if (cant > pt.getStockActual()) {
                    System.out.println(
                            CONST.RED + "Stock insuficiente! Disponible: " + pt.getStockActual() + CONST.RESET);
                } else {
                    detalles.add(new DetalleCompra(pt.getProducto(), cant, pt.getProducto().getPrecio()));
                    pt.setStockActual(pt.getStockActual() - cant);
                    System.out.println(CONST.GREEN + "Agregado al carrito." + CONST.RESET);
                }
            }
            agregarMas = InputScanner.readBoolean("¿Agregar otro?");
        }

        if (!detalles.isEmpty()) {
            Compra c = new Compra(LocalDateTime.now(), detalles);
            c.setId(compras.size() + 1);
            compras.add(c);
            usuarioLogueado.getCompras().add(c); // Guardar en el historial del usuario
            System.out.println(CONST.GREEN_BOLD + "Pedido confirmado! Total: $" + c.getTotal() + CONST.RESET);
        }
    }

    private static void listarPedidos() {
        System.out.println("\n" + CONST.BLUE_BOLD + "--- Historial Global de Pedidos ---" + CONST.RESET);
        if (compras.isEmpty()) {
            System.out.println(CONST.YELLOW + "No hay pedidos realizados." + CONST.RESET);
        } else {
            mostrarCompras(compras);
        }
    }

    private static void listarMisPedidos() {
        System.out.println("\n" + CONST.BLUE_BOLD + "--- Mis Pedidos ---" + CONST.RESET);
        if (usuarioLogueado.getCompras().isEmpty()) {
            System.out.println(CONST.YELLOW + "Aún no has realizado compras." + CONST.RESET);
        } else {
            mostrarCompras(usuarioLogueado.getCompras());
        }
    }

    private static void mostrarCompras(List<Compra> lista) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        boolean esAdmin = usuarioLogueado.getRol().equals("ADMIN");

        for (Compra c : lista) {
            String header = esAdmin ? "Pedido #" + c.getId() : "Pedido";
            System.out.println(CONST.CYAN + header + " | Fecha: " + c.getFecha().format(dtf) + CONST.RESET);
            c.getDetalles().forEach(d -> System.out.println("  - " + d));
            System.out.println(CONST.GREEN_BOLD + "  TOTAL: $" + c.getTotal() + CONST.RESET);
            System.out.println("-----------------------------");
        }
    }
}
