package com.techlab.pedidos;

import com.techlab.productos.Producto;
import com.techlab.CONST;

public class ItemCarrito {
  private Producto producto;
  private int cantidad;

  public ItemCarrito(Producto producto, int cantidad) {
    this.producto = producto;
    this.cantidad = cantidad;
  }

  public void setProducto(Producto producto) {
    this.producto = producto;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public Producto getProducto() {
    return this.producto;
  }

  public int getCantidad() {
    return this.cantidad;
  }

  public double getSubtotal() {
    return this.producto.getPrecio() * this.cantidad;
  }

  @Override
  public String toString() {
    return String.format("%s%-20s%s | Cantidad: %d | Subtotal: %s$%.2f%s", 
        CONST.WHITE_BOLD, producto.getNombre(), CONST.RESET, 
        cantidad, 
        CONST.GREEN, getSubtotal(), CONST.RESET);
  }
}
