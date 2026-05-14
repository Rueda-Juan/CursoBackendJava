package com.techlab.pedidos;
import java.time.LocalDateTime;
import java.util.List;
public class Compra {
    private int id;
    private LocalDateTime fecha;
    private List<DetalleCompra> detalles;
    public Compra(LocalDateTime fecha, List<DetalleCompra> detalles) {
        this.fecha = fecha;
        this.detalles = detalles;
    }
    public int getId() {
        return this.id;
    }
    public LocalDateTime getFecha() {
        return this.fecha;
    }
    public List<DetalleCompra> getDetalles() {
        return this.detalles;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public void setDetalles(List<DetalleCompra> detalles) {
        this.detalles = detalles;
    }
    public double getTotal() {
        return detalles.stream()
                .mapToDouble(DetalleCompra::getSubtotal)
                .sum();
    }
}

