package com.techlab.tienda;

import com.techlab.pedidos.*;
import com.techlab.productos.*;
import com.techlab.excepciones.*;
import com.techlab.usuario.Usuario;
import com.techlab.utils.CONST;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class Tienda {
  private String nombre;
  private List<ProductoTienda> productos;
  private List<Usuario> usuarios;
  private List<Compra> historialCompras;
  public Tienda(String nombre) {
    this.nombre = nombre;
    this.productos = new ArrayList<>();
    this.usuarios = new ArrayList<>();
    this.historialCompras = new ArrayList<>();
    this.usuarios.add(new Usuario(1, "admin", "1234", "ADMIN", "Administrador"));
    this.usuarios.add(new Usuario(2, "cliente@techlab.com", "user123", "CLIENTE", "Juan Cliente"));
  }
  public String getNombre() {
    return this.nombre;
  }
  public List<ProductoTienda> getProductos() {
    return this.productos;
  }
  public List<Compra> getHistorialCompras() {
    return this.historialCompras;
  }
  public List<Usuario> getUsuarios() {
    return this.usuarios;
  }
  public Optional<Usuario> autenticar(String email, String password) {
    return usuarios.stream()
        .filter(u -> u.login(email, password))
        .findFirst();
  }
  public void registrarUsuario(Usuario usuario) {
    this.usuarios.add(usuario);
  }
  public Compra procesarCompra(Carrito carrito, Usuario usuario) throws StockInsuficienteException {
    if (carrito.getItems().isEmpty()) {
      throw new IllegalArgumentException("El carrito está vacío.");
    }
    List<DetalleCompra> detalles = new ArrayList<>();
    for (ItemCarrito item : carrito.getItems()) {
      ProductoTienda pt = buscarProducto(item.getProducto().getId())
          .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado: " + item.getProducto().getNombre()));
      if (item.getCantidad() > pt.getStockActual()) {
        throw new StockInsuficienteException("Stock insuficiente para " + pt.getProducto().getNombre());
      }
    }
    for (ItemCarrito item : carrito.getItems()) {
      ProductoTienda pt = buscarProducto(item.getProducto().getId()).get();
      pt.vender(item.getCantidad());
      detalles.add(new DetalleCompra(item.getProducto(), item.getCantidad(), item.getProducto().getPrecio()));
    }
    Compra nuevaCompra = new Compra(LocalDateTime.now(), detalles);
    nuevaCompra.setId(historialCompras.size() + 1);
    historialCompras.add(nuevaCompra);
    usuario.getCompras().add(nuevaCompra);
    return nuevaCompra;
  }
  public void actualizarProducto(int id, Double nuevoPrecio, Integer nuevoStock, Usuario usuario) {
    validarAdmin(usuario);
    ProductoTienda pt = buscarProducto(id)
        .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado."));
    pt.actualizar(nuevoPrecio, nuevoStock);
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
  public Optional<ProductoTienda> buscarProductoPorIdONombre(String query) {
    try {
      int id = Integer.parseInt(query);
      return buscarProducto(id);
    } catch (NumberFormatException e) {
      return buscarProducto(query);
    }
  }
  public List<ProductoTienda> buscarProductos(String termino) {
    List<ProductoTienda> resultados = this.productos.stream()
        .filter(pt -> pt.getProducto().getNombre().toLowerCase().contains(termino.toLowerCase()) ||
                     pt.getProducto().getDescripcion().toLowerCase().contains(termino.toLowerCase()) ||
                     pt.getProducto().getMarca().toLowerCase().contains(termino.toLowerCase()))
        .toList();
    if (resultados.isEmpty()) {
      throw new ProductoNoEncontradoException("No se encontraron productos con el término: " + termino);
    }
    return resultados;
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

