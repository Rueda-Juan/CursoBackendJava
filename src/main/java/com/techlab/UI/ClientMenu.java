package com.techlab.UI;
import com.techlab.utils.CONST;
public class ClientMenu extends Menu {
    public ClientMenu(MenuController controller) {
        super(controller);
    }
    @Override
    protected void setupActions() {
        actions.put(1, controller::listarProductos);
        actions.put(2, controller::buscarProductosUI);
        actions.put(3, controller::crearPedido);
        actions.put(4, controller::listarMisPedidos);
        actions.put(5, controller::cerrarSesion);
    }
    @Override
    public void display() {
        System.out.println("\n" + CONST.BLUE_BACKGROUND + CONST.WHITE_BOLD + "      MENÚ CLIENTE      " + CONST.RESET);
        System.out.println(CONST.BLUE + "1)" + CONST.RESET + " Ver catálogo de productos");
        System.out.println(CONST.BLUE + "2)" + CONST.RESET + " Buscar productos");
        System.out.println(CONST.BLUE + "3)" + CONST.RESET + " Realizar una compra");
        System.out.println(CONST.BLUE + "4)" + CONST.RESET + " Ver mis pedidos");
        System.out.println(CONST.BLUE + "5)" + CONST.RESET + " Cerrar sesión");
        System.out.println(CONST.BLUE + "6)" + CONST.RESET + " Salir");
    }
}

