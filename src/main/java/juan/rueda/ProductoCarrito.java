package juan.rueda;

public class ProductoCarrito {
  private Producto producto;
   private int cantidad;

  public  ProductoCarrito(Producto producto, int cantidad) {
    this.producto = producto;
    this.cantidad = cantidad;
  }

  public void setProducto(Producto producto2) {
    this.producto = producto2;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public Producto getProducto() {
    return this.producto;
  }

  public double getCantidad() {
    return this.cantidad;
  }
  
}
