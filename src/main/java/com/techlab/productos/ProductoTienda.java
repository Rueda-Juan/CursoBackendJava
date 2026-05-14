package com.techlab.productos;
import com.techlab.utils.CONST;
public class ProductoTienda {
  private Producto producto;
  private int stockActual;
  private int stockMinimo;
  public ProductoTienda(Producto producto, int stockActual, int stockMinimo) {
    this.producto = producto;
    this.stockActual = stockActual;
    this.stockMinimo = stockMinimo;
  }
  public Producto getProducto() {
    return this.producto;
  }
  public int getStockActual() {
    return this.stockActual;
  }
  public int getStockMinimo() {
    return this.stockMinimo;
  }
  public void setStockActual(int stockActual) {
    this.stockActual = stockActual;
  }
  public void setStockMinimo(int stockMinimo) {
    this.stockMinimo = stockMinimo;
  }
  public void setProducto(Producto producto) {
    this.producto = producto;
  }
  public void vender(int cantidad) throws com.techlab.excepciones.StockInsuficienteException {
    if (cantidad > this.stockActual) {
      throw new com.techlab.excepciones.StockInsuficienteException(
          "Stock insuficiente para " + producto.getNombre() + ". Disponible: " + this.stockActual);
    }
    this.stockActual -= cantidad;
  }
  public void actualizar(Double nuevoPrecio, Integer nuevoStock) {
    if (nuevoPrecio != null) {
      this.producto.setPrecio(nuevoPrecio);
    }
    if (nuevoStock != null) {
      this.stockActual = nuevoStock;
    }
  }
  @Override
  public String toString() {
    String colorStock = stockActual <= stockMinimo ? CONST.RED_BOLD : CONST.YELLOW;
    return String.format("%s | Stock: %s%d%s (Min: %d)", 
        producto.toString(), colorStock, stockActual, CONST.RESET, stockMinimo);
  }
}

