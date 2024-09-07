package br.ufc.sistemapatrimonio.controller;

import br.ufc.sistemapatrimonio.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class RequisitanteViewController {

    private final Model model = new Model();
    @FXML
    private Button btnIrParaBens;

    @FXML
    private Button btnIrParaPatrimonios;

    @FXML
    private Button btnSair;

    @FXML
    private TextArea txtListaRequisicoes;

    @FXML
    void irParaBens(ActionEvent event) {
        // Implementar navegação para a tela de Bens
    }

    @FXML
    void irParaPatrimonios(ActionEvent event) {
        // Implementar navegação para a tela de Patrimonios
    }

    @FXML
    void sair(ActionEvent event) {
        // Implementar a lógica para sair da tela
    }

    private void atualizarListaRequisicoes() {
        txtListaRequisicoes.setText(model.getRequisitanteModel().listarRequisicoes());
    }

    public void removerRequisicao(ActionEvent actionEvent) {
    }

    public void adicionarRequisicao(ActionEvent actionEvent) {

    }

    public void irParaManutencoes(ActionEvent actionEvent) throws IOException {
        ScreenController.activate("telaAdminManutecoes");
    }
}
