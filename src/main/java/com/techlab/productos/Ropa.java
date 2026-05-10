package com.techlab.productos;

import com.techlab.CONST;

public class Ropa extends Producto {
    private String talle;
    private String color;

    public Ropa(int id, String nombre, double precio, String descripcion, String marca, String talle, String color) {
        super(id, nombre, precio, descripcion, marca);
        this.talle = talle;
        this.color = color;
    }

    public String getTalle() {
        return this.talle;
    }

    public void setTalle(String talle) {
        this.talle = talle;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | %sTalle: %s%s | %sColor: %s%s", 
            CONST.MAGENTA, talle, CONST.RESET, 
            CONST.MAGENTA, color, CONST.RESET);
    }
}