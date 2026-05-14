package com.techlab;

import com.techlab.productos.*;
import com.techlab.tienda.Tienda;
import com.techlab.usuario.Usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TiendaTest {
    private Tienda tienda;
    private Usuario admin;
    private Usuario cliente;

    @BeforeEach
    void setUp() {
        tienda = new Tienda("Tienda Test");
        admin = new Usuario(1, "a@t.com", "1", "ADMIN", "Adm");
        cliente = new Usuario(2, "c@t.com", "1", "CLIENTE", "Cli");
    }

    @Test
    void testAgregarProductoNuevo() {
        Producto p = new Producto(1, "Pro1", 10.0, "D", "M");
        tienda.agregarProducto(p, 50, 10); // Usa la sobrecarga sin usuario
        
        assertTrue(tienda.buscarProducto(1).isPresent());
        assertEquals(50, tienda.buscarProducto(1).get().getStockActual());
    }

    @Test
    void testAgregarProductoExistenteAumentaStock() {
        Producto p = new Producto(1, "Pro1", 10.0, "D", "M");
        tienda.agregarProducto(p, 50, 10);
        tienda.agregarProducto(p, 20, 10);
        
        assertEquals(70, tienda.buscarProducto(1).get().getStockActual());
    }

    @Test
    void testEliminarProductoComoAdmin() {
        Producto p = new Producto(1, "Pro1", 10.0, "D", "M");
        tienda.agregarProducto(p, 50, 10);
        
        assertTrue(tienda.eliminarProducto(1, admin));
        assertFalse(tienda.buscarProducto(1).isPresent());
    }

    @Test
    void testEliminarProductoComoClienteLanzaExcepcion() {
        Producto p = new Producto(1, "Pro1", 10.0, "D", "M");
        tienda.agregarProducto(p, 50, 10);
        
        assertThrows(com.techlab.excepciones.AccesoDenegadoException.class, () -> {
            tienda.eliminarProducto(1, cliente);
        });
    }

    @Test
    void testBuscarPorNombreCaseInsensitive() {
        Producto p = new Producto(1, "Alfajor", 10.0, "D", "M");
        tienda.agregarProducto(p, 10, 5);
        
        assertTrue(tienda.buscarProducto("ALFAJOR").isPresent());
    }
}
