package com.techlab.pedidos;

import com.techlab.productos.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarritoTest {
    private Carrito carrito;

    @BeforeEach
    void setUp() {
        carrito = new Carrito();
    }

    @Test
    void testAgregarAlCarrito() {
        Producto p = new Producto(1, "P1", 10.0, "D", "M");
        carrito.agregarProducto(p, 5);
        
        assertEquals(1, carrito.getItems().size());
        assertEquals(50.0, carrito.calcularTotal());
    }

    @Test
    void testEliminarDelCarrito() {
        Producto p = new Producto(1, "P1", 10.0, "D", "M");
        carrito.agregarProducto(p, 5);
        carrito.eliminarProducto(p);
        
        assertEquals(0, carrito.getItems().size());
        assertEquals(0.0, carrito.calcularTotal());
    }

    @Test
    void testSubtotalItem() {
        Producto p = new Producto(1, "P1", 15.0, "D", "M");
        ItemCarrito item = new ItemCarrito(p, 3);
        assertEquals(45.0, item.getSubtotal());
    }
}
