package br.ufc.sistemapatrimonio.controller;

import br.ufc.sistemapatrimonio.enums.TipoReserva;
import br.ufc.sistemapatrimonio.exceptions.BemException;
import br.ufc.sistemapatrimonio.exceptions.ManutencaoException;
import br.ufc.sistemapatrimonio.exceptions.PatrimonioException;
import br.ufc.sistemapatrimonio.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class RequisitanteManutencoesViewController {

    Model model = new Model();
    @FXML
    private Button btnAdicionar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnIrParaRequisicoes;
    @FXML
    private Button btnRemover;
    @FXML
    private Button btnSair;
    @FXML
    private RadioButton radioBemAdd;
    @FXML
    private RadioButton radioBemEditar;
    @FXML
    private ToggleGroup radioOpcoesAdd;
    @FXML
    private ToggleGroup radioOpcoesEditar;
    @FXML
    private RadioButton radioPatrimonioAdd;
    @FXML
    private RadioButton radioPatrimonioEditar;
    @FXML
    private TextField txtDefeitoAdd;
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
    private TextField txtRemover;
    @FXML
    private TextField txtTipoEditar;

    public void atualizarListaManutencoes() {
        txtListaPatrimonios.setText(model.getRequisitanteModel().listarManutencoes());
    }


    @FXML
    void adicionarManutencao(ActionEvent event) {
        try {
            if (radioBemAdd.isSelected()) {
                // Adicionar bem
                int id = Integer.parseInt(txtIDAdd.getText().trim());
                String nome = txtNomeAdd.getText();
                String descricao = txtDefeitoAdd.getText();
                TipoReserva tipo = TipoReserva.BEM;

                if (nome.trim().isEmpty() || descricao.trim().isEmpty()) {
                    model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);
                } else {
                    model.getRequisitanteModel().adicionarManutencao(id, nome, descricao, tipo);

                    model.mostrarPopup("Sucesso", "Manutenção de Bem requisitada com sucesso!", Alert.AlertType.INFORMATION);

                    atualizarListaManutencoes();

                    txtIDAdd.clear();
                    txtNomeAdd.clear();
                    txtDefeitoAdd.clear();
                }
            } else if (radioPatrimonioAdd.isSelected()) {
                // Adicionar patrimonio
                int id = Integer.parseInt(txtIDAdd.getText().trim());
                String nome = txtNomeAdd.getText();
                String descricao = txtDefeitoAdd.getText();
                TipoReserva tipo = TipoReserva.PATRIMONIO;

                if (nome.trim().isEmpty() || descricao.trim().isEmpty()) {
                    model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);
                } else {
                    model.getRequisitanteModel().adicionarManutencao(id, nome, descricao, tipo);

                    model.mostrarPopup("Sucesso", "Manutenção de Patrimonio requisitada com sucesso!", Alert.AlertType.INFORMATION);

                    atualizarListaManutencoes();

                    txtIDAdd.clear();
                    txtNomeAdd.clear();
                    txtDefeitoAdd.clear();
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

    @FXML
    void editarManutencao(ActionEvent event) {
        try {
            if (radioBemEditar.isSelected()) {
                // Editar bem
                int id = Integer.parseInt(txtIDEditar.getText().trim());
                String nome = txtNomeEditar.getText();
                String descricao = txtTipoEditar.getText();
                TipoReserva tipo = TipoReserva.BEM;

                if (nome.trim().isEmpty() || descricao.trim().isEmpty()) {
                    model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);
                } else {
                    model.getRequisitanteModel().editarManutencao(id, nome, descricao, tipo);

                    model.mostrarPopup("Sucesso", "Manutenção editada!", Alert.AlertType.INFORMATION);

                    atualizarListaManutencoes();

                    txtIDEditar.clear();
                    txtNomeEditar.clear();
                    txtTipoEditar.clear();
                }
            } else if (radioPatrimonioAdd.isSelected()) {
                // Adicionar patrimonio
                int id = Integer.parseInt(txtIDEditar.getText().trim());
                String nome = txtNomeEditar.getText();
                String descricao = txtTipoEditar.getText();
                TipoReserva tipo = TipoReserva.PATRIMONIO;

                if (nome.trim().isEmpty() || descricao.trim().isEmpty()) {
                    model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);
                } else {
                    model.getRequisitanteModel().editarManutencao(id, nome, descricao, tipo);

                    model.mostrarPopup("Sucesso", "Manutenção editada!", Alert.AlertType.INFORMATION);

                    atualizarListaManutencoes();

                    txtIDEditar.clear();
                    txtNomeEditar.clear();
                    txtTipoEditar.clear();
                }
            } else {
                model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);
            }
        } catch (NumberFormatException e) {
            model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);
        } catch (IOException | ManutencaoException e) {
            model.mostrarPopup("Erro", "Ocorreu um erro inesperado: " + e.getMessage(), Alert.AlertType.ERROR);
            throw new RuntimeException(e);
        }
    }

    @FXML
    void removerManutencao(ActionEvent event) {
       /* try {
            int id = Integer.parseInt(txtRemover.getText().trim());
            if (radioPatrimonioRemover.isSelected()) {
                TipoReserva tipoReserva = TipoReserva.PATRIMONIO;
                model.getRequisitanteModel().remover(id, tipoReserva);
            } else if (radioBemRemover.isSelected()) {
                TipoReserva tipoReserva = TipoReserva.BEM;
                model.getRequisitanteModel().remover(id, tipoReserva);
            } else {
                model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);
            }

            atualizarListaManutencoes();

            txtRemover.clear();
        } catch (NumberFormatException e) {
            model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);
            throw new RuntimeException(e);
        } catch (IOException | PatrimonioException | BemException e) {
            model.mostrarPopup("Erro", "Ocorreu um erro inesperado: " + e.getMessage(), Alert.AlertType.ERROR);
            throw new RuntimeException(e);
        }*/
    }

    @FXML
    void irParaRequisicoes(ActionEvent event) throws IOException {
        ScreenController.activate("telaRequisitanteRequisicoes");
    }

    @FXML
    void sair(ActionEvent event) throws IOException {
        // Implementar a lógica para sair da tela
        ScreenController.activate("telaLogin");
        Model.deslogarUsuario();
    }

}
