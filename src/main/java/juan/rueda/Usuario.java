package juan.rueda;

public abstract class Usuario {
  private int id;
  private String email;
  private String password;

  public Usuario(int id, String email, String password) {
    this.id = id;
    this.email = email;
    this.password = password;
  }

  public int getId() {
    return this.id;
  }

  public boolean login(String email, String password) {
    return this.email.equals(email) && this.password.equals(password);
  }

}
