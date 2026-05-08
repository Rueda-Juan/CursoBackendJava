package juan.rueda;

public class Tienda {
  private String nombre;
  private ProductoTienda[] productos;

  public Tienda(String nombre, ProductoTienda[] productos) {
    this.nombre = nombre;
    this.productos = productos;
  }

  public ProductoTienda[] getProductos() {
    return this.productos;
  }

  public void agregarProducto(Producto producto, Usuario usuario) {
    if(usuario.getId() == 1) {
      
    } else {
      System.out.println("Solo los administradores pueden agregar productos.");
    }
    
  }
  public void eliminarProducto(Producto producto, Admin admin) {
    // Lógica para eliminar un producto de la tienda
  }
  
}
