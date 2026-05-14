package com.techlab.utils;
import java.time.LocalDate;
import com.techlab.productos.Bebida;
import com.techlab.productos.Producto;
import com.techlab.productos.ProductoPerecedero;
import com.techlab.productos.Ropa;
public class ProductoFactory {
    public static Producto crearProducto(int tipo, int id, String nombre, double precio, String descripcion, String marca, Object... extra) {
        return switch (tipo) {
            case 1 -> new Producto(id, nombre, precio, descripcion, marca);
            case 2 -> {
                int ml = (int) extra[0];
                boolean alcohol = (boolean) extra[1];
                yield new Bebida(id, nombre, precio, descripcion, marca, ml, alcohol);
            }
            case 3 -> {
                String talle = (String) extra[0];
                String color = (String) extra[1];
                yield new Ropa(id, nombre, precio, descripcion, marca, talle, color);
            }
            case 4 -> {
                LocalDate fecha = (LocalDate) extra[0];
                yield new ProductoPerecedero(id, nombre, precio, descripcion, marca, fecha);
            }
            default -> throw new IllegalArgumentException("Tipo de producto no reconocido: " + tipo);
        };
    }
}

