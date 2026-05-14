package com.techlab.pedidos;
import com.techlab.productos.Producto;
import com.techlab.utils.CONST;
public class DetalleCompra {
    private Producto producto;
    private int cantidad;
    private double precio;
    public DetalleCompra(Producto producto, int cantidad, double precio) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    public Producto getProducto() {
        return this.producto;
    }
    public int getCantidad() {
        return this.cantidad;
    }
    public double getPrecio() {
        return this.precio;
    }
    public double getSubtotal() {
        return this.precio * this.cantidad;
    }
    @Override
    public String toString() {
        if (producto == null) return "Producto no disponible";
        return String.format("%s%-20s%s | Cantidad: %d | Subtotal: %s$%.2f%s",
                CONST.WHITE_BOLD, producto.getNombre(), CONST.RESET,
                cantidad,
                CONST.GREEN, getSubtotal(), CONST.RESET);
    }
}

