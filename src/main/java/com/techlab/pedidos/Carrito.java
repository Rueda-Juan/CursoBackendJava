package com.techlab.pedidos;
import com.techlab.productos.Producto;
import com.techlab.utils.CONST;
import java.util.ArrayList;
import java.util.List;
public class Carrito {
  private List<ItemCarrito> productos;
  public Carrito() {
    this.productos = new ArrayList<ItemCarrito>();
  }
  public void agregarProducto(Producto producto, int cantidad) {
    ItemCarrito pc = new ItemCarrito(producto, cantidad);
    this.productos.add(pc);
  }
  public void eliminarProducto(Producto producto) {
    this.productos.removeIf(pc -> pc.getProducto().equals(producto));
  }
  public List<ItemCarrito> getItems() {
    return this.productos;
  }
  public double calcularTotal() {
    return productos.stream()
        .mapToDouble(ItemCarrito::getSubtotal)
        .sum();
  }
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(CONST.BLUE_BOLD).append("--- Carrito ---\n").append(CONST.RESET);
    for (ItemCarrito item : productos) {
      sb.append(item.toString()).append("\n");
    }
    sb.append(CONST.GREEN_BOLD).append("Total: $").append(calcularTotal()).append(CONST.RESET);
    return sb.toString();
  }
}

