package br.ufc.sistemapatrimonio.controller;

import br.ufc.sistemapatrimonio.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AdminPatrimoniosViewController {

    private final Model model = new Model();

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnIrParaBens;

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
    private TextArea txtListaPatrimonios;

    @FXML
    private TextField txtNomeAdd;

    @FXML
    private TextField txtNomeEditar;

    @FXML
    private TextField txtNumeroDeTombamentoAdd;

    @FXML
    private TextField txtNumeroDeTombamentoEditar;

    @FXML
    private TextField txtTipoAdd;

    @FXML
    private TextField txtTipoEditar;

    @FXML
    void adicionarPatrimonio(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtIDAdd.getText());
            String nome = txtNomeAdd.getText();
            String descricao = txtDescricaoAdd.getText();
            int depreciacao = Integer.parseInt(txtDepressiacaoAdd.getText());
            String tipo = txtTipoAdd.getText();
            int numeroTombamento = Integer.parseInt(txtNumeroDeTombamentoAdd.getText());

            model.getAdminPatrimoniosModel().adicionarPatrimonio(id, nome, descricao, depreciacao, tipo, numeroTombamento);

            model.mostrarPopup("Sucesso", "Patrimônio adicionado com sucesso!", Alert.AlertType.INFORMATION);
            atualizarListaPatrimonios();
        } catch (NumberFormatException e) {
            model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void editarPatrimonio(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtIDEditar.getText());
            String nome = txtNomeEditar.getText();
            String descricao = txtDescricaoEditar.getText();
            int depreciacao = Integer.parseInt(txtDepressiacaoEditar.getText());
            String tipo = txtTipoEditar.getText();
            int numeroTombamento = Integer.parseInt(txtNumeroDeTombamentoEditar.getText());

            model.getAdminPatrimoniosModel().editarPatrimonio(id, nome, descricao, depreciacao, tipo, numeroTombamento);

            model.mostrarPopup("Sucesso", "Patrimônio editado com sucesso!", Alert.AlertType.INFORMATION);
            atualizarListaPatrimonios();
        } catch (NumberFormatException e) {
            model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void irParaBens(ActionEvent event) {
        // Implementar navegação para a tela de Bens
    }

    @FXML
    void irParaRequisicoes(ActionEvent event) {
        // Implementar navegação para a tela de Requisições
    }

    @FXML
    void removerPatrimonio(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtIDAdd.getText());

            model.getAdminPatrimoniosModel().removerPatrimonio(id);

            model.mostrarPopup("Sucesso", "Patrimônio removido com sucesso!", Alert.AlertType.INFORMATION);
            atualizarListaPatrimonios();
        } catch (NumberFormatException e) {
            model.mostrarPopup("Erro", "Por favor, insira um ID válido.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void sair(ActionEvent event) {
        // Implementar a lógica para sair da tela
    }

    private void atualizarListaPatrimonios() {
        txtListaPatrimonios.setText(model.getAdminPatrimoniosModel().listarPatrimonios());
    }
}
