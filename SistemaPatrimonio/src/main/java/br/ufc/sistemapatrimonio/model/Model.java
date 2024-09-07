package br.ufc.sistemapatrimonio.model;

import br.ufc.sistemapatrimonio.entities.Bem;
import br.ufc.sistemapatrimonio.entities.Patrimonio;
import br.ufc.sistemapatrimonio.entities.RequisicaoDeManutencao;
import br.ufc.sistemapatrimonio.entities.Usuario;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

public class Model {
    public static List<Usuario> users = new ArrayList<>();
    public static List<Patrimonio> patrimonios = new ArrayList<>();
    public static List<Bem> bens = new ArrayList<>();
    public static List<RequisicaoDeManutencao> manutencaoPatrimonio = new ArrayList<>();
    public static List<RequisicaoDeManutencao> manutencaoBem = new ArrayList<>();

    private static Usuario usuarioAutenticado;
    private AdminBensModel adminBensModel = new AdminBensModel();
    private AdminPatrimoniosModel adminPatrimoniosModel = new AdminPatrimoniosModel();
    private RequisitanteModel requisitanteModel = new RequisitanteModel();
    public TelaLoginCadastroModel cadastroModel = new TelaLoginCadastroModel();
    public UsuarioModel usuarioModel = new UsuarioModel();

    public static Usuario getUsuarioAutenticado() {
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

    // Métodos para AdminBensModel
    public AdminBensModel getAdminBensModel() {
        return adminBensModel;
    }

    // Métodos para AdminPatrimoniosModel
    public AdminPatrimoniosModel getAdminPatrimoniosModel() {
        return adminPatrimoniosModel;
    }

    // Métodos para RequisitanteModel
    public RequisitanteModel getRequisitanteModel() {
        return requisitanteModel;
    }
}
