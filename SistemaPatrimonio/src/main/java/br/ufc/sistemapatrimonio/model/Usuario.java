package br.ufc.sistemapatrimonio.model;
import br.ufc.sistemapatrimonio.enums.TipoUsuario;

public class Usuario {
    private String username;
    private String password;
    private TipoUsuario tipoUsuario;

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
}
