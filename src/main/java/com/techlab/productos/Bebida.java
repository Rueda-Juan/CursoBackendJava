package com.techlab.productos;

import com.techlab.CONST;

public class Bebida extends Producto {
    private int mililitros;
    private boolean esAlcoholico;

    public Bebida(int id, String nombre, double precio, String descripcion, String marca, int mililitros,
            boolean esAlcoholico) {
        super(id, nombre, precio, descripcion, marca);
        this.mililitros = mililitros;
        this.esAlcoholico = esAlcoholico;
    }

    public int getMililitros() {
        return this.mililitros;
    }

    public void setMililitros(int mililitros) {
        this.mililitros = mililitros;
    }

    public boolean isEsAlcoholico() {
        return esAlcoholico;
    }

    public void setEsAlcoholico(boolean esAlcoholico) {
        this.esAlcoholico = esAlcoholico;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | %s%dml%s | %s%s%s",
                CONST.BLUE, mililitros, CONST.RESET,
                esAlcoholico ? CONST.RED_ITALIC : CONST.GREEN_ITALIC,
                esAlcoholico ? "Alcohólica" : "Sin Alcohol", CONST.RESET);
    }
}
