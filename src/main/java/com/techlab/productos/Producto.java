package com.techlab.productos;

import com.techlab.CONST;

public class Producto {
  private int id;
  private String nombre;
  private String marca;
  private double precio;
  private String descripcion;

  public Producto(int id, String nombre, double precio, String descripcion, String marca) {
    this.id = id;
    this.nombre = nombre;
    this.precio = precio;
    this.descripcion = descripcion;
    this.marca = marca;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public double getPrecio() {
    return precio;
  }

  public void setPrecio(double precio) {
    this.precio = precio;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  @Override
  public String toString() {
    return String.format(
        "%sID: %d%s | %s%-20s%s | %sMarca: %-15s%s | %sPrecio: $%.2f%s",
        CONST.CYAN, id, CONST.RESET,
        CONST.WHITE_BOLD, nombre, CONST.RESET,
        CONST.YELLOW, marca, CONST.RESET,
        CONST.GREEN, precio, CONST.RESET
    );
  }
}
