package br.ufc.sistemapatrimonio.controller;

import br.ufc.sistemapatrimonio.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class RequisitanteRequisicoesViewController {

    private final Model model = new Model();
    @FXML
    private Button btnIrParaBens;

    @FXML
    private Button btnIrParaPatrimonios;

    @FXML
    private Button btnSair;

    @FXML
    private TextArea txtListaRequisicoes;


    private void atualizarListaRequisicoes() {
        txtListaRequisicoes.setText(model.getRequisitanteModel().listarRequisicoes());
    }

    public void removerRequisicao(ActionEvent actionEvent) {
    }

    public void adicionarRequisicao(ActionEvent actionEvent) {

    }

    @FXML
    void irParaBens(ActionEvent event) throws IOException {
        // Implementar navegação para a tela de Bens
        ScreenController.activate("telaAdminBens");
    }

    @FXML
    void irParaPatrimonios(ActionEvent event) throws IOException {
        // Implementar navegação para a tela de Patrimonios
        ScreenController.activate("telaAdminPatrimonios");
    }

    public void irParaManutencoes(ActionEvent actionEvent) throws IOException {
        ScreenController.activate("telaAdminManutencoes");
    }

    @FXML
    void sair(ActionEvent event) throws IOException {
        // Implementar a lógica para sair da tela
        ScreenController.activate("telaLogin");
        Model.deslogarUsuario();
    }
}
