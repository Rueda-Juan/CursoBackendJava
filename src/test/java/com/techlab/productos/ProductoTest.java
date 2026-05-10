package com.techlab.productos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class ProductoTest {

    @Test
    void testConstructorAndGetters() {
        Producto p = new Producto(1, "Test", 100.0, "Desc", "Marca");
        assertEquals(1, p.getId());
        assertEquals("Test", p.getNombre());
        assertEquals(100.0, p.getPrecio());
    }

    @Test
    void testBebidaPolymorphism() {
        Producto b = new Bebida(1, "Coca", 50.0, "Refresco", "Coca-Cola", 500, false);
        assertTrue(b instanceof Producto);
        assertTrue(b.toString().contains("500ml"));
    }

    @Test
    void testProductoPerecederoExpiration() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        PoductoPerecedero p = new PoductoPerecedero(1, "Leche", 20.0, "Lacteo", "Sancor", tomorrow);
        assertEquals(tomorrow, p.getFechaCaducidad());
    }

    @Test
    void testExtremeValues() {
        Producto p = new Producto(Integer.MAX_VALUE, "", 0.0, null, "");
        assertEquals(Integer.MAX_VALUE, p.getId());
        assertEquals(0.0, p.getPrecio());
        assertNull(p.getDescripcion());
    }
}
