package juan.rueda;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
  private List<ProductoCarrito> productos;

  public Carrito() {
    this.productos = new ArrayList<ProductoCarrito>();
  }
  
  public void agregarProducto(Producto producto, int cantidad) {
    ProductoCarrito pc = new ProductoCarrito(producto, cantidad);
    this.productos.add(pc);
  }

  public void eliminarProducto(Producto producto) {
    this.productos.removeIf(pc -> pc.getProducto().equals(producto));
  }

  public double calcularTotal() {
    double total = productos.stream()
      .mapToDouble(p -> p.getProducto().getPrecio() * p.getCantidad())
      .sum();
    
    return total;
  }
}
