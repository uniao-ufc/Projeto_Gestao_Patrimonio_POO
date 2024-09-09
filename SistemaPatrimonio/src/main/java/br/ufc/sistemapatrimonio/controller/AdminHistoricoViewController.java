package br.ufc.sistemapatrimonio.controller;

import br.ufc.sistemapatrimonio.enums.TipoReserva;
import br.ufc.sistemapatrimonio.exceptions.BemException;
import br.ufc.sistemapatrimonio.exceptions.ManutencaoException;
import br.ufc.sistemapatrimonio.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class AdminHistoricoViewController {

    Model model = new Model();

    @FXML
    private RadioButton radioBem;

    @FXML
    private RadioButton radioPatrimonio;

    @FXML
    private Button btnIrParaBens;

    @FXML
    private Button btnIrParaManutencoes;

    @FXML
    private Button btnIrParaPatrimonios;

    @FXML
    private Button btnRemover;

    @FXML
    private Button btnSair;

    @FXML
    private RadioButton radioManutencao;

    @FXML
    private ToggleGroup radioOpcoes;

    @FXML
    private RadioButton radioRequisicao;

    @FXML
    private TextArea txtHistoricoManutencoes;

    @FXML
    private TextArea txtHistoricoRequisicoes;

    @FXML
    private TextField txtRemover;

    public void atualizarListaManutencoes() {
        txtHistoricoManutencoes.setText(model.getAdminModel().listarManutencoes());
    }

    public void atualizarListaReservas() {
        txtHistoricoRequisicoes.setText(model.getAdminModel().listarReservas());
    }

    @FXML
    void removerDoHistorico(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtRemover.getText().trim());
            if (radioRequisicao.isSelected()) {
                if(radioPatrimonio.isSelected()){
                    String tipoReserva = "reserva";
                    TipoReserva tipopatrimonio = TipoReserva.PATRIMONIO;
                    model.getAdminModel().removerHistorico(id, tipoReserva, tipopatrimonio);
                    atualizarListaManutencoes();
                    atualizarListaReservas();
                } else if (radioBem.isSelected()) {
                    String tipoReserva = "reserva";
                    TipoReserva tipobem = TipoReserva.BEM;
                    model.getAdminModel().removerHistorico(id, tipoReserva, tipobem);
                    atualizarListaManutencoes();
                    atualizarListaReservas();
                }else {
                    model.mostrarPopup("Erro", "Por favor, selecione valores válidos.", Alert.AlertType.ERROR);
                }

            } else if (radioManutencao.isSelected()) {
                if(radioPatrimonio.isSelected()){
                    String tipoReserva = "manutencao";
                    TipoReserva tipopatrimonio = TipoReserva.PATRIMONIO;
                    model.getAdminModel().removerHistorico(id, tipoReserva, tipopatrimonio);
                    atualizarListaManutencoes();
                    atualizarListaReservas();
                } else if (radioBem.isSelected()) {
                    String tipoReserva = "manutencao";
                    TipoReserva tipobem = TipoReserva.BEM;
                    model.getAdminModel().removerHistorico(id, tipoReserva, tipobem);
                    atualizarListaManutencoes();
                    atualizarListaReservas();
                }else {
                    model.mostrarPopup("Erro", "Por favor, selecione valores válidos.", Alert.AlertType.ERROR);
                }
            } else {
                model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);
            }

            atualizarListaManutencoes();
            atualizarListaReservas();

            txtRemover.clear();
        } catch (NumberFormatException e) {
            model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);
            throw new RuntimeException(e);
        } catch (IOException | BemException e) {
            model.mostrarPopup("Erro", "Ocorreu um erro inesperado: " + e.getMessage(), Alert.AlertType.ERROR);
            throw new RuntimeException(e);
        }
    }

    @FXML
    void irParaBens(ActionEvent event) throws IOException {
        ScreenController.activate("telaAdminBens");
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
