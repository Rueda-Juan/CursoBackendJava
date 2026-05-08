package juan.rueda;

public class ProductoTienda {
  private Producto producto;
  private int stock;
  private int stockMinimo;

  public ProductoTienda(Producto producto, int stock, int stockMinimo) {
    this.producto = producto;
    this.stock = stock;
    this.stockMinimo = stockMinimo;
  }
}
