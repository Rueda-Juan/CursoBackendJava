package juan.rueda;

import java.util.Date;

public class Cliente extends Usuario {
  private Carrito carrito;
  private String nombre;
  private Date fechaRegistro;

  public Cliente(String email, String password, String nombre) {
    super(email, password);
    this.nombre = nombre;
    this.fechaRegistro = new Date();
    this.carrito = new Carrito();
  }
}
