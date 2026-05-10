package com.techlab.pedidos;

import com.techlab.productos.Producto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.ArrayList;

public class CompraTest {

    @Test
    void testCalcularTotalCompra() {
        Producto p1 = new Producto(1, "P1", 100.0, "D", "M");
        Producto p2 = new Producto(2, "P2", 50.0, "D", "M");
        
        DetalleCompra d1 = new DetalleCompra(p1, 2, 100.0); // 200
        DetalleCompra d2 = new DetalleCompra(p2, 3, 50.0);  // 150
        
        Compra c = new Compra(LocalDateTime.now(), Arrays.asList(d1, d2));
        
        assertEquals(350.0, c.getTotal());
    }

    @Test
    void testCompraVaciaTotalCero() {
        Compra c = new Compra(LocalDateTime.now(), new ArrayList<>());
        assertEquals(0.0, c.getTotal());
    }

    @Test
    void testDetalleSubtotal() {
        Producto p1 = new Producto(1, "P1", 10.0, "D", "M");
        DetalleCompra d = new DetalleCompra(p1, 10, 10.0);
        assertEquals(100.0, d.getSubtotal());
    }
}
