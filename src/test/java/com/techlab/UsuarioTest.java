package com.techlab;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.techlab.usuario.Usuario;

public class UsuarioTest {

    @Test
    void testLoginExitoso() {
        Usuario u = new Usuario(1, "juan@test.com", "1234", "ADMIN", "Juan");
        assertTrue(u.login("juan@test.com", "1234"));
    }

    @Test
    void testLoginFallido() {
        Usuario u = new Usuario(1, "juan@test.com", "1234", "ADMIN", "Juan");
        assertFalse(u.login("juan@test.com", "wrong"));
        assertFalse(u.login("wrong@test.com", "1234"));
    }

    @Test
    void testCarritoInicializado() {
        Usuario u = new Usuario(1, "a@b.com", "p", "USER", "N");
        assertNotNull(u.getCarrito());
        assertEquals(0, u.getCompras().size());
    }
}
