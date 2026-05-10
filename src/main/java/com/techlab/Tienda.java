package com.techlab;

import com.techlab.productos.Producto;
import com.techlab.productos.ProductoTienda;
import com.techlab.excepciones.AccesoDenegadoException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Tienda {
  private String nombre;
  private List<ProductoTienda> productos;

  public Tienda(String nombre) {
    this.nombre = nombre;
    this.productos = new ArrayList<>();
  }

  public String getNombre() {
    return this.nombre;
  }

  public List<ProductoTienda> getProductos() {
    return this.productos;
  }

  public void agregarProducto(Producto producto, int stock, int stockMinimo, Usuario usuario) {
    validarAdmin(usuario);
    Optional<ProductoTienda> existing = buscarProducto(producto.getId());
    if (existing.isPresent()) {
      ProductoTienda pt = existing.get();
      pt.setStockActual(pt.getStockActual() + stock);
    } else {
      this.productos.add(new ProductoTienda(producto, stock, stockMinimo));
    }
  }

  // Sobrecarga para carga inicial (sin usuario)
  public void agregarProducto(Producto producto, int stock, int stockMinimo) {
    Optional<ProductoTienda> existing = buscarProducto(producto.getId());
    if (existing.isPresent()) {
      ProductoTienda pt = existing.get();
      pt.setStockActual(pt.getStockActual() + stock);
    } else {
      this.productos.add(new ProductoTienda(producto, stock, stockMinimo));
    }
  }

  public boolean eliminarProducto(int id, Usuario usuario) {
    validarAdmin(usuario);
    return this.productos.removeIf(pt -> pt.getProducto().getId() == id);
  }

  public Optional<ProductoTienda> buscarProducto(int id) {
    return this.productos.stream()
        .filter(pt -> pt.getProducto().getId() == id)
        .findFirst();
  }

  public Optional<ProductoTienda> buscarProducto(String nombre) {
    return this.productos.stream()
        .filter(pt -> pt.getProducto().getNombre().equalsIgnoreCase(nombre))
        .findFirst();
  }

  private void validarAdmin(Usuario usuario) {
    if (usuario == null || !"ADMIN".equals(usuario.getRol())) {
      throw new AccesoDenegadoException("Acceso denegado: Se requieren permisos de ADMINISTRADOR.");
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(CONST.BLUE_BOLD).append("=== ").append(nombre).append(" ===\n").append(CONST.RESET);
    if (productos.isEmpty()) {
      sb.append(CONST.YELLOW).append("Sin stock disponible.\n").append(CONST.RESET);
    } else {
      for (ProductoTienda pt : productos) {
        sb.append(pt.toString()).append("\n");
      }
    }
    return sb.toString();
  }
}
