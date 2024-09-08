package br.ufc.sistemapatrimonio.controller;

import br.ufc.sistemapatrimonio.exceptions.BemException;
import br.ufc.sistemapatrimonio.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminBensViewController {

    private final Model model = new Model();

    @FXML
    private TextField txtRemover;

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnIrParaPatrimonios;

    @FXML
    private Button btnIrParaRequisicoes;

    @FXML
    private Button btnRemover;

    @FXML
    private Button btnSair;

    @FXML
    private TextField txtDepressiacaoAdd;

    @FXML
    private TextField txtDepressiacaoEditar;

    @FXML
    private TextField txtDescricaoAdd;

    @FXML
    private TextField txtDescricaoEditar;

    @FXML
    private TextField txtIDAdd;

    @FXML
    private TextField txtIDEditar;

    @FXML
    private TextArea txtListaBens;

    @FXML
    private TextField txtNomeAdd;

    @FXML
    private TextField txtNomeEditar;

    @FXML
    private TextField txtTipoAdd;

    @FXML
    private TextField txtTipoEditar;

    @FXML
    void adicionarBem(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtIDAdd.getText());
            String nome = txtNomeAdd.getText();
            String descricao = txtDescricaoAdd.getText();
            int depreciacao = Integer.parseInt(txtDepressiacaoAdd.getText());
            String tipo = txtTipoAdd.getText();

            model.getAdminModel().adicionarBem(id, nome, descricao, depreciacao, tipo);

            txtIDAdd.clear();
            txtNomeAdd.clear();
            txtDescricaoAdd.clear();
            txtDepressiacaoAdd.clear();
            txtTipoAdd.clear();

            model.mostrarPopup("Sucesso", "Bem adicionado com sucesso!", Alert.AlertType.INFORMATION);

            atualizarListaBens();
        } catch (NumberFormatException e) {
            model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);
        } catch (BemException e) {
            model.mostrarPopup("Erro", "Ocorreu um erro inesperado: " + e.getMessage(), Alert.AlertType.ERROR);
            throw new RuntimeException(e);
        }
    }

    @FXML
    void editarBem(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtIDEditar.getText());
            String nome = txtNomeEditar.getText();
            String descricao = txtDescricaoEditar.getText();
            int depreciacao = Integer.parseInt(txtDepressiacaoEditar.getText());
            String tipo = txtTipoEditar.getText();

            model.getAdminModel().editarBem(id, nome, descricao, depreciacao, tipo);

            txtIDEditar.clear();
            txtNomeEditar.clear();
            txtDescricaoEditar.clear();
            txtDepressiacaoEditar.clear();
            txtTipoEditar.clear();

            model.mostrarPopup("Sucesso", "Bem editado com sucesso!", Alert.AlertType.INFORMATION);

            atualizarListaBens();
        } catch (NumberFormatException e) {
            model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);
        } catch (BemException e) {
            switch (e.getErroCode()) {
                case BemException.NAO_ENCONTRADO, BemException.ERRO ->
                        model.mostrarPopup("Erro", e.getMessage(), Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            model.mostrarPopup("Erro", "Ocorreu um erro inesperado: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void removerBem(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtRemover.getText());

            model.getAdminModel().removerBem(id);

            txtRemover.clear();

            model.mostrarPopup("Sucesso", "Bem removido com sucesso!", Alert.AlertType.INFORMATION);
            atualizarListaBens();
        } catch (NumberFormatException e) {
            model.mostrarPopup("Erro", "Por favor, insira um ID válido.", Alert.AlertType.ERROR);
        } catch (BemException e) {
            switch (e.getErroCode()) {
                case BemException.NAO_ENCONTRADO, BemException.ERRO ->
                        model.mostrarPopup("Erro", e.getMessage(), Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            model.mostrarPopup("Erro", "Ocorreu um erro inesperado: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void irParaPatrimonios(ActionEvent event) throws IOException {
        // Implementar navegação para a tela de Patrimonios
        ScreenController.activate("telaAdminPatrimonios");
    }

    @FXML
    void irParaRequisicoes(ActionEvent event) throws IOException {
        // Implementar navegação para a tela de Requisições
        ScreenController.activate("telaAdminRequisicoes");
    }

    @FXML
    void sair(ActionEvent event) throws IOException {
        // Implementar a lógica para sair da tela
        ScreenController.activate("telaLogin");
        Model.deslogarUsuario();
    }

    public void atualizarListaBens() {
        txtListaBens.setText(model.getAdminModel().listarBens());
    }
}
