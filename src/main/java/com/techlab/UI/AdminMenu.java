package com.techlab.UI;
import com.techlab.utils.CONST;
public class AdminMenu extends Menu {
    public AdminMenu(MenuController controller) {
        super(controller);
    }
    @Override
    protected void setupActions() {
        actions.put(1, controller::agregarProducto);
        actions.put(2, controller::listarProductos);
        actions.put(3, controller::buscarProductosUI);
        actions.put(4, controller::buscarActualizarProducto);
        actions.put(5, controller::eliminarProducto);
        actions.put(6, controller::listarPedidos);
        actions.put(7, controller::cerrarSesion);
    }
    @Override
    public void display() {
        System.out.println("\n" + CONST.BLUE_BACKGROUND + CONST.WHITE_BOLD + "      MENÚ ADMINISTRADOR      " + CONST.RESET);
        System.out.println(CONST.BLUE + "1)" + CONST.RESET + " Agregar producto");
        System.out.println(CONST.BLUE + "2)" + CONST.RESET + " Listar productos");
        System.out.println(CONST.BLUE + "3)" + CONST.RESET + " Buscar productos");
        System.out.println(CONST.BLUE + "4)" + CONST.RESET + " Buscar/Actualizar producto");
        System.out.println(CONST.BLUE + "5)" + CONST.RESET + " Eliminar producto");
        System.out.println(CONST.BLUE + "6)" + CONST.RESET + " Listar TODOS los pedidos");
        System.out.println(CONST.BLUE + "7)" + CONST.RESET + " Cerrar sesión");
        System.out.println(CONST.BLUE + "8)" + CONST.RESET + " Salir");
    }
}

