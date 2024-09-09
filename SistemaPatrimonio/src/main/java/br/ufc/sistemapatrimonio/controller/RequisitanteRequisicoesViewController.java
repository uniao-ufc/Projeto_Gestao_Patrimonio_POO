package br.ufc.sistemapatrimonio.controller;

import br.ufc.sistemapatrimonio.enums.TipoReserva;
import br.ufc.sistemapatrimonio.exceptions.BemException;
import br.ufc.sistemapatrimonio.exceptions.PatrimonioException;
import br.ufc.sistemapatrimonio.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class RequisitanteRequisicoesViewController {


    private final Model model = new Model();
    @FXML
    private RadioButton radioBemRemover;

    @FXML
    private RadioButton radioPatrimonioRemover;

    @FXML
    private TextField txtRemover;

    @FXML
    private Button btnRemover;

    @FXML
    private TextField txtIDAdd;

    @FXML
    private TextField txtNomeAdd;

    @FXML
    private TextField txtLocalAdd;

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnIrParaManutencoes;

    @FXML
    private RadioButton radioBemAdd;

    @FXML
    private RadioButton radioPatrimonioAdd;

    @FXML
    private TextField txtDescricaoAdd;

    @FXML
    private Button btnSair;

    @FXML
    private TextArea txtListaRequisicoes;

    public void initialize() {
        atualizarReservas();
    }

    public void atualizarReservas() {
        String reservasListadas = model.getRequisitanteModel().listarReservasUsuario(); // Chama a função que lista as reservas
        txtListaRequisicoes.setText(reservasListadas); // Atualiza o conteúdo da TextArea
    }

    public void atualizarListaRequisicoes() {
        txtListaRequisicoes.setText(model.getRequisitanteModel().listarReservasUsuario());
    }

    public void adicionarRequisicao(ActionEvent actionEvent) {
        try {
            if (radioBemAdd.isSelected()) {
                // Adicionar bem
                int id = Integer.parseInt(txtIDAdd.getText().trim());
                String nome = txtNomeAdd.getText();
                String local = txtLocalAdd.getText();
                String descricao = txtDescricaoAdd.getText();
                TipoReserva tipo = TipoReserva.BEM;

                if (nome.trim().isEmpty() || local.trim().isEmpty() || descricao.trim().isEmpty()) {
                    model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);
                } else {
                    model.getRequisitanteModel().adicionarRequisicao(id, nome, local, descricao, tipo);

                    model.mostrarPopup("Sucesso", "Bem requisitado com sucesso!", Alert.AlertType.INFORMATION);

                    //atualizarListaBens();
                    txtIDAdd.clear();
                    txtNomeAdd.clear();
                    txtLocalAdd.clear();
                    txtDescricaoAdd.clear();
                    atualizarListaRequisicoes();
                }
            } else if (radioPatrimonioAdd.isSelected()) {
                // Adicionar patrimonio
                int id = Integer.parseInt(txtIDAdd.getText().trim());
                String nome = txtNomeAdd.getText();
                String local = txtLocalAdd.getText();
                String descricao = txtDescricaoAdd.getText();
                TipoReserva tipo = TipoReserva.PATRIMONIO;

                if (nome.trim().isEmpty() || local.trim().isEmpty() || descricao.trim().isEmpty()) {
                    model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);
                } else {
                    model.getRequisitanteModel().adicionarRequisicao(id, nome, local, descricao, tipo);

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

    public void removerRequisicao(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(txtRemover.getText().trim());
            if (radioPatrimonioRemover.isSelected()) {
                TipoReserva tipoReserva = TipoReserva.PATRIMONIO;
                model.getRequisitanteModel().removerRequisicao(id, tipoReserva);
            } else if (radioBemRemover.isSelected()) {
                TipoReserva tipoReserva = TipoReserva.BEM;
                model.getRequisitanteModel().removerRequisicao(id, tipoReserva);
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

    public void irParaManutencoes(ActionEvent actionEvent) throws IOException {
        ScreenController.activate("telaRequisitanteManutencoes");
    }

    @FXML
    void sair(ActionEvent event) throws IOException {
        // Implementar a lógica para sair da tela
        ScreenController.activate("telaLogin");
        Model.deslogarUsuario();
    }

}
