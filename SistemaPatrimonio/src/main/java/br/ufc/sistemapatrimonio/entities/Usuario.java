package br.ufc.sistemapatrimonio.entities;
import br.ufc.sistemapatrimonio.enums.TipoUsuario;

public class Usuario {
    private static int id = 0;

    private final int idUser;
    private String username;
    private String password;
    private TipoUsuario tipoUsuario;

    public Usuario(String username, String password, TipoUsuario tipoUsuario) {
        this.username = username;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.idUser = id;
        id++;
    }

    // Getter para username
    public String getUsername() {
        return username;
    }

    // Setter para username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter para password
    public String getPassword() {
        return password;
    }

    // Setter para password
    public void setPassword(String password) {
        this.password = password;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public int getId() {
        return idUser;
    }
}
