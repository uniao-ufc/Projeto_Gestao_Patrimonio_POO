package br.ufc.sistemapatrimonio.model;

import br.ufc.sistemapatrimonio.entities.Usuario;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

public class Model {

    public static List<Usuario> users = new ArrayList<>();
    public static List<Object> patrimonios = new ArrayList<>();
    public static List<Object> bens = new ArrayList<>();
    private Usuario usuarioAutenticado;
    public TelaLoginCadastroModel cadastroModel = new TelaLoginCadastroModel();

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

    public void deslogarUsuario() {
        usuarioAutenticado = null;
    }

    public void mostrarPopup(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}