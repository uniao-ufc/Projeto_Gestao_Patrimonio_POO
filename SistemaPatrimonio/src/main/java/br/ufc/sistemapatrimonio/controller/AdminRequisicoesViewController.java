package br.ufc.sistemapatrimonio.controller;

import br.ufc.sistemapatrimonio.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

public class AdminRequisicoesViewController {

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnIrParaBens;

    @FXML
    private Button btnIrParaHistoricos;

    @FXML
    private Button btnIrParaManutencoes;

    @FXML
    private Button btnIrParaPatrimonios;

    @FXML
    private Button btnRemover;

    @FXML
    private Button btnSair;

    @FXML
    private RadioButton radioBem;

    @FXML
    private ToggleGroup radioOpcoes;

    @FXML
    private RadioButton radioPatrimonio;

    @FXML
    private TextField txtDescricaoAdd;

    @FXML
    private TextField txtIDAdd;

    @FXML
    private TextArea txtListaRequisicoes;

    @FXML
    private TextField txtLocalAdd;

    @FXML
    private TextField txtNomeAdd;

    @FXML
    private TextField txtRemover;

    @FXML
    void adicionarRequisicao(ActionEvent event) {

    }

    @FXML
    void removerRequisicao(ActionEvent event) {

    }

    @FXML
    void irParaBens(ActionEvent event) throws IOException {
        ScreenController.activate("telaAdminBens");
    }

    @FXML
    void irParaHistoricos(ActionEvent event) throws IOException {
        ScreenController.activate("telaAdminHistorico");
    }

    @FXML
    void irParaManutencoes(ActionEvent event) throws IOException {
        ScreenController.activate("telaAdminManutencoes");
    }

    @FXML
    void irParaPatrimonios(ActionEvent event) throws IOException {
        ScreenController.activate("telaAdminPatrimonios");
    }

    @FXML
    void sair(ActionEvent event) throws IOException {
        // Implementar a lógica para sair da tela
        ScreenController.activate("telaLogin");
        Model.deslogarUsuario();
    }

}
