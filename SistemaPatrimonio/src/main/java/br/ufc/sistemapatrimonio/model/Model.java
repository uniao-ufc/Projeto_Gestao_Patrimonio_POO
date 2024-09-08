package br.ufc.sistemapatrimonio.model;

import br.ufc.sistemapatrimonio.entities.Bem;
import br.ufc.sistemapatrimonio.entities.Patrimonio;
import br.ufc.sistemapatrimonio.entities.RequisicaoDeManutencao;
import br.ufc.sistemapatrimonio.entities.Usuario;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private static final List<Usuario> users = new ArrayList<>();
    private static final List<Patrimonio> patrimonios = new ArrayList<>();
    private static final List<Bem> bens = new ArrayList<>();
    private static final List<RequisicaoDeManutencao> manutencaoPatrimonio = new ArrayList<>();
    private static final List<RequisicaoDeManutencao> manutencaoBem = new ArrayList<>();
    private static Usuario usuarioAutenticado;
    private final AdminBensModel adminBensModel = new AdminBensModel();
    private final AdminPatrimoniosModel adminPatrimoniosModel = new AdminPatrimoniosModel();
    private final RequisitanteModel requisitanteModel = new RequisitanteModel();
    private final TelaLoginCadastroModel cadastroModel = new TelaLoginCadastroModel();
    private final UsuarioModel usuarioModel = new UsuarioModel(getUsuarioAutenticado());

    public static List<Patrimonio> getPatrimonios() {
        return patrimonios;
    }

    public static List<Bem> getBens() {
        return bens;
    }

    public static List<RequisicaoDeManutencao> getManutencaoPatrimonio() {
        return manutencaoPatrimonio;
    }

    public static List<RequisicaoDeManutencao> getManutencaoBem() {
        return manutencaoBem;
    }

    public static List<Usuario> getUsers() {
        return users;
    }

    public TelaLoginCadastroModel getCadastroModel() {
        return cadastroModel;
    }

    public UsuarioModel getUsuarioModel() {
        return usuarioModel;
    }

    public static Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

    public static void deslogarUsuario() {
        usuarioAutenticado = null;
    }

    public void mostrarPopup(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    // Métodos para AdminBensModel.java
    public AdminBensModel getAdminBensModel() {
        return adminBensModel;
    }

    // Métodos para AdminPatrimoniosModel.java
    public AdminPatrimoniosModel getAdminPatrimoniosModel() {
        return adminPatrimoniosModel;
    }

    // Métodos para RequisitanteModel.java
    public RequisitanteModel getRequisitanteModel() {
        return requisitanteModel;
    }
}
