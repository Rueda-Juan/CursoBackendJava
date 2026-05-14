package com.techlab.UI;

import com.techlab.tienda.Tienda;
import com.techlab.utils.CONST;
import com.techlab.utils.InputScanner;
import com.techlab.utils.LectorCSV;
public class Main {
    private static final Tienda tienda = new Tienda("Supermercado Java");
    private static final MenuController controller = new MenuController(tienda);
    public static void main(String[] args) {
        LectorCSV.cargarProductos(tienda, "src/main/resources/productos.csv");
        System.out.println(CONST.CYAN_BOLD + "BIENVENIDO A TECHLAB SYSTEM" + CONST.RESET);
        while (true) {
            gestionarAutenticacion();
            ejecutarMenuPrincipal();
        }
    }
    private static void gestionarAutenticacion() {
        while (controller.getUsuarioLogueado() == null) {
            System.out.println("\n1) Iniciar Sesión | 2) Crear Cuenta | 3) Salir");
            int modo = InputScanner.readInt("Seleccione: ");
            if (modo == 1) {
                controller.iniciarSesion();
            } else if (modo == 2) {
                controller.registrarUsuario();
            } else if (modo == 3) {
                System.out.println("¡Adiós!");
                System.exit(0);
            }
        }
    }
    private static void ejecutarMenuPrincipal() {
        boolean salir = false;
        while (controller.getUsuarioLogueado() != null && !salir) {
            try {
                Menu menu = controller.getUsuarioLogueado().isAdmin() 
                    ? new AdminMenu(controller) 
                    : new ClientMenu(controller);
                menu.display();
                int opcion = InputScanner.readInt("Elija una opción: ");
                int opcionSalir = controller.getUsuarioLogueado().isAdmin() ? 8 : 6;
                if (opcion == opcionSalir) {
                    System.out.println(CONST.CYAN_BOLD + "¡Gracias por usar el sistema!" + CONST.RESET);
                    System.exit(0);
                } else {
                    menu.dispatch(opcion);
                }
            } catch (com.techlab.excepciones.ScannerException e) {
                System.out.println(CONST.RED + "Error de entrada: " + e.getMessage() + CONST.RESET);
            }
        }
    }
}

