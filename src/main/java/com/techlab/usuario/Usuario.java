package com.techlab.usuario;
import com.techlab.pedidos.Carrito;
import com.techlab.pedidos.Compra;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class Usuario {
  private int id;
  private String email;
  private String password;
  private String rol;
  private String nombre;
  private LocalDateTime fechaRegistro;
  private Carrito carrito;
  private List<Compra> compras;
  public Usuario(int id, String email, String password, String rol, String nombre) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.rol = rol;
    this.nombre = nombre;
    this.fechaRegistro = LocalDateTime.now();
    this.carrito = new Carrito();
    this.compras = new ArrayList<Compra>();
  }
  public int getId() {
    return this.id;
  }
  public String getNombre() {
    return nombre;
  }
  public String getRol() {
    return rol;
  }
  public boolean isAdmin() {
    return "ADMIN".equals(this.rol);
  }
  public boolean isCliente() {
    return "CLIENTE".equals(this.rol);
  }
  public LocalDateTime getFechaRegistro() {
    return fechaRegistro;
  }
  public Carrito getCarrito() {
    return carrito;
  }
  public List<Compra> getCompras() {
    return compras;
  }
  public boolean login(String email, String password) {
    return this.email.equals(email) && this.password.equals(password);
  }
}

