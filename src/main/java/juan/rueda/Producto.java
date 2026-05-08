package juan.rueda;

public class Producto {
  private int id;
  private String nombre;
  private double precio;
  private String descripcion;


  public Producto(int id, String nombre, double precio, String descripcion, int stock, int StockMinimo) {
    this.id = id;
    this.nombre = nombre;
    this.precio = precio;
    this.descripcion = descripcion;
  }

  public double getPrecio() {
    return precio;
  }
  
}
