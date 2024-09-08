package br.ufc.sistemapatrimonio.controller;

import br.ufc.sistemapatrimonio.enums.TipoReserva;
import br.ufc.sistemapatrimonio.exceptions.BemException;
import br.ufc.sistemapatrimonio.exceptions.PatrimonioException;
import br.ufc.sistemapatrimonio.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class AdminRequisicoesViewController {
    Model model = new Model();
    @FXML
    private RadioButton radioPatrimonioRemover;
    @FXML
    private RadioButton radioBemRemover;
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
        try {
            if (radioBem.isSelected()) {
                // Adicionar bem
                int id = Integer.parseInt(txtIDAdd.getText().trim());
                String nome = txtNomeAdd.getText();
                String local = txtLocalAdd.getText();
                String descricao = txtDescricaoAdd.getText();
                TipoReserva tipo = TipoReserva.BEM;

                if (nome.trim().isEmpty() || local.trim().isEmpty() || descricao.trim().isEmpty()) {
                    model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);
                } else {
                    model.getAdminModel().adicionar(id, nome, local, descricao, tipo);

                    model.mostrarPopup("Sucesso", "Bem requisitado com sucesso!", Alert.AlertType.INFORMATION);

                    txtIDAdd.clear();
                    txtNomeAdd.clear();
                    txtLocalAdd.clear();
                    txtDescricaoAdd.clear();
                    atualizarListaRequisicoes();
                }
            } else if (radioPatrimonio.isSelected()) {
                // Adicionar patrimonio
                int id = Integer.parseInt(txtIDAdd.getText().trim());
                String nome = txtNomeAdd.getText();
                String local = txtLocalAdd.getText();
                String descricao = txtDescricaoAdd.getText();
                TipoReserva tipo = TipoReserva.PATRIMONIO;

                if (nome.trim().isEmpty() || local.trim().isEmpty() || descricao.trim().isEmpty()) {
                    model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);
                } else {
                    model.getAdminModel().adicionar(id, nome, local, descricao, tipo);

                    model.mostrarPopup("Sucesso", "Patrimonio requisitado com sucesso!", Alert.AlertType.INFORMATION);

                    //atualizarListaBens();
                    txtIDAdd.clear();
                    txtNomeAdd.clear();
                    txtLocalAdd.clear();
                    txtDescricaoAdd.clear();
                    atualizarListaRequisicoes();
                }
            } else {
                model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);
            }
        } catch (NumberFormatException e) {
            model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);
        } catch (BemException | PatrimonioException | IOException e) {
            model.mostrarPopup("Erro", "Ocorreu um erro inesperado: " + e.getMessage(), Alert.AlertType.ERROR);
            throw new RuntimeException(e);
        }
    }

    public void atualizarListaRequisicoes() {
        txtListaRequisicoes.setText(model.getAdminModel().listarReservas());
    }

    @FXML
    void removerRequisicao(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtRemover.getText().trim());
            if (radioPatrimonioRemover.isSelected()) {
                TipoReserva tipoReserva = TipoReserva.PATRIMONIO;
                model.getAdminModel().remover(id, tipoReserva);
            } else if (radioBemRemover.isSelected()) {
                TipoReserva tipoReserva = TipoReserva.BEM;
                model.getAdminModel().remover(id, tipoReserva);
            } else {
                model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);
            }

            atualizarListaRequisicoes();

            txtRemover.clear();
        } catch (NumberFormatException e) {
            model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);
            throw new RuntimeException(e);
        } catch (IOException | PatrimonioException | BemException e) {
            model.mostrarPopup("Erro", "Ocorreu um erro inesperado: " + e.getMessage(), Alert.AlertType.ERROR);
            throw new RuntimeException(e);
        }
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
