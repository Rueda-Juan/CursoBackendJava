package com.techlab.excepciones;

/**
 * Excepción lanzada cuando se intenta realizar un pedido con una cantidad
 * superior al stock disponible en la tienda.
 */
public class StockInsuficienteException extends Exception {
    public StockInsuficienteException(String message) {
        super(message);
    }
}
