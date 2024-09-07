package br.ufc.sistemapatrimonio.controller;

import br.ufc.sistemapatrimonio.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AdminBensViewController {

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

            Model.getInstance().getAdminBensModel().adicionarBem(id, nome, descricao, depreciacao, tipo);

            Model.getInstance().mostrarPopup("Sucesso", "Bem adicionado com sucesso!", Alert.AlertType.INFORMATION);
            atualizarListaBens();
        } catch (NumberFormatException e) {
            Model.getInstance().mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);
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

            Model.getInstance().getAdminBensModel().editarBem(id, nome, descricao, depreciacao, tipo);

            Model.getInstance().mostrarPopup("Sucesso", "Bem editado com sucesso!", Alert.AlertType.INFORMATION);
            atualizarListaBens();
        } catch (NumberFormatException e) {
            Model.getInstance().mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void irParaPatrimonios(ActionEvent event) {
        // Implementar navegação para a tela de Patrimonios
    }

    @FXML
    void irParaRequisicoes(ActionEvent event) {
        // Implementar navegação para a tela de Requisições
    }

    @FXML
    void removerBem(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtIDAdd.getText());

            Model.getInstance().getAdminBensModel().removerBem(id);

            Model.getInstance().mostrarPopup("Sucesso", "Bem removido com sucesso!", Alert.AlertType.INFORMATION);
            atualizarListaBens();
        } catch (NumberFormatException e) {
            Model.getInstance().mostrarPopup("Erro", "Por favor, insira um ID válido.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void sair(ActionEvent event) {
        // Implementar a lógica para sair da tela
    }

    private void atualizarListaBens() {
        txtListaBens.setText(Model.getInstance().getAdminBensModel().listarBens());
    }
}
