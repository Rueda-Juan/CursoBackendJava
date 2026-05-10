package com.techlab.productos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.techlab.CONST;

public class PoductoPerecedero extends Producto {
    private LocalDate fechaCaducidad;

    public PoductoPerecedero(int id, String nombre, double precio, String descripcion, String marca,
            LocalDate fechaCaducidad) {
        super(id, nombre, precio, descripcion, marca);
        this.fechaCaducidad = fechaCaducidad;
    }

    public LocalDate getFechaCaducidad() {
        return this.fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String colorFecha = (fechaCaducidad != null && fechaCaducidad.isBefore(LocalDate.now().plusDays(3)))
                ? CONST.RED_BLINK
                : CONST.YELLOW_ITALIC;
        String fechaStr = fechaCaducidad != null ? fechaCaducidad.format(formatter) : "N/A";
        return super.toString() + String.format(" | %sVence: %s%s",
                colorFecha, fechaStr, CONST.RESET);
    }
}
