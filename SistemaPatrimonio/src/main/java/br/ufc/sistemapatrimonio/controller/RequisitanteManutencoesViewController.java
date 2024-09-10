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
    private RadioButton radioBemRemover;  // botão de opção para remover bem
    @FXML
    private RadioButton radioPatrimonioRemover;  // botão de opção para remover patrimônio
    @FXML
    private Button btnAdicionar;  // botão para adicionar manutenção
    @FXML
    private Button btnEditar;  // botão para editar manutenção
    @FXML
    private Button btnIrParaRequisicoes;  // botão para ir para a tela de requisições
    @FXML
    private Button btnRemover;  // botão para remover manutenção
    @FXML
    private Button btnSair;  // botão para sair
    @FXML
    private RadioButton radioBemAdd;  // botão de opção para adicionar bem
    @FXML
    private RadioButton radioBemEditar;  // botão de opção para editar bem
    @FXML
    private ToggleGroup radioOpcoesAdd;  // grupo de botões de opção para adicionar
    @FXML
    private ToggleGroup radioOpcoesEditar;  // grupo de botões de opção para editar
    @FXML
    private RadioButton radioPatrimonioAdd;  // botão de opção para adicionar patrimônio
    @FXML
    private RadioButton radioPatrimonioEditar;  // botão de opção para editar patrimônio
    @FXML
    private TextField txtDefeitoAdd;  // campo para descrição do defeito na adição
    @FXML
    private TextField txtIDAdd;  // campo para ID na adição
    @FXML
    private TextField txtIDEditar;  // campo para ID na edição
    @FXML
    private TextArea txtListaManutencoes;  // área de texto para listar manutenções
    @FXML
    private TextField txtNomeAdd;  // campo para nome na adição
    @FXML
    private TextField txtNomeEditar;  // campo para nome na edição
    @FXML
    private TextField txtRemover;  // campo para ID na remoção
    @FXML
    private TextField txtTipoEditar;  // campo para tipo na edição

    public void atualizarListaManutencoes() {
        txtListaManutencoes.setText(model.getRequisitanteModel().listarManutencoesUsuario());  // atualiza a lista de manutenções
    }

    @FXML
    void adicionarManutencao(ActionEvent event) {
        try {
            if (radioBemAdd.isSelected()) {
                // adicionar bem
                int id = Integer.parseInt(txtIDAdd.getText().trim());  // obtém o ID
                String nome = txtNomeAdd.getText();  // obtém o nome
                String descricao = txtDefeitoAdd.getText();  // obtém a descrição do defeito
                TipoReserva tipo = TipoReserva.BEM;  // define o tipo como bem

                if (nome.trim().isEmpty() || descricao.trim().isEmpty()) {
                    model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);  // mostra um popup se informações não forem inseridas
                } else {
                    model.getRequisitanteModel().adicionarManutencao(id, nome, descricao, tipo);  // adiciona a manutenção

                    model.mostrarPopup("Sucesso", "Manutenção de Bem requisitada com sucesso!", Alert.AlertType.INFORMATION);  // mostra um popup de sucesso

                    atualizarListaManutencoes();  // atualiza a lista de manutenções

                    txtIDAdd.clear();  // limpa o campo de ID
                    txtNomeAdd.clear();  // limpa o campo de nome
                    txtDefeitoAdd.clear();  // limpa o campo de descrição
                }
            } else if (radioPatrimonioAdd.isSelected()) {
                // adicionar patrimônio
                int id = Integer.parseInt(txtIDAdd.getText().trim());  // obtém o ID
                String nome = txtNomeAdd.getText();  // obtém o nome
                String descricao = txtDefeitoAdd.getText();  // obtém a descrição do defeito
                TipoReserva tipo = TipoReserva.PATRIMONIO;  // define o tipo como patrimônio

                if (nome.trim().isEmpty() || descricao.trim().isEmpty()) {
                    model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);  // mostra um popup se informações não forem inseridas
                } else {
                    model.getRequisitanteModel().adicionarManutencao(id, nome, descricao, tipo);  // adiciona a manutenção

                    model.mostrarPopup("Sucesso", "Manutenção de Patrimonio requisitada com sucesso!", Alert.AlertType.INFORMATION);  // mostra um popup de sucesso

                    atualizarListaManutencoes();  // atualiza a lista de manutenções

                    txtIDAdd.clear();  // limpa o campo de ID
                    txtNomeAdd.clear();  // limpa o campo de nome
                    txtDefeitoAdd.clear();  // limpa o campo de descrição
                }
            } else {
                model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);  // mostra um popup se informações não forem inseridas
            }
        } catch (NumberFormatException e) {
            model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);  // mostra um popup se valores não forem válidos
        } catch (BemException | PatrimonioException | IOException e) {
            model.mostrarPopup("Erro", "Ocorreu um erro inesperado: " + e.getMessage(), Alert.AlertType.ERROR);  // mostra um popup se ocorrer um erro inesperado
            throw new RuntimeException(e);  // lança uma exceção em tempo de execução
        }
    }

    @FXML
    void editarManutencao(ActionEvent event) {
        try {
            if (radioBemEditar.isSelected()) {
                // editar bem
                int id = Integer.parseInt(txtIDEditar.getText().trim());  // obtém o ID
                String nome = txtNomeEditar.getText();  // obtém o nome
                String descricao = txtTipoEditar.getText();  // obtém a descrição do defeito
                TipoReserva tipo = TipoReserva.BEM;  // define o tipo como bem

                if (nome.trim().isEmpty() || descricao.trim().isEmpty()) {
                    model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);  // mostra um popup se informações não forem inseridas
                } else {
                    model.getRequisitanteModel().editarManutencao(id, nome, descricao, tipo);  // edita a manutenção

                    model.mostrarPopup("Sucesso", "Manutenção editada!", Alert.AlertType.INFORMATION);  // mostra um popup de sucesso

                    atualizarListaManutencoes();  // atualiza a lista de manutenções

                    txtIDEditar.clear();  // limpa o campo de ID
                    txtNomeEditar.clear();  // limpa o campo de nome
                    txtTipoEditar.clear();  // limpa o campo de descrição
                }
            } else if (radioPatrimonioEditar.isSelected()) {
                // editar patrimônio
                int id = Integer.parseInt(txtIDEditar.getText().trim());  // obtém o ID
                String nome = txtNomeEditar.getText();  // obtém o nome
                String descricao = txtTipoEditar.getText();  // obtém a descrição do defeito
                TipoReserva tipo = TipoReserva.PATRIMONIO;  // define o tipo como patrimônio

                if (nome.trim().isEmpty() || descricao.trim().isEmpty()) {
                    model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);  // mostra um popup se informações não forem inseridas
                } else {
                    model.getRequisitanteModel().editarManutencao(id, nome, descricao, tipo);  // edita a manutenção

                    model.mostrarPopup("Sucesso", "Manutenção editada!", Alert.AlertType.INFORMATION);  // mostra um popup de sucesso

                    atualizarListaManutencoes();  // atualiza a lista de manutenções

                    txtIDEditar.clear();  // limpa o campo de ID
                    txtNomeEditar.clear();  // limpa o campo de nome
                    txtTipoEditar.clear();  // limpa o campo de descrição
                }
            } else {
                model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);  // mostra um popup se informações não forem inseridas
            }
        } catch (NumberFormatException e) {
            model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);  // mostra um popup se valores não forem válidos
        } catch (IOException | ManutencaoException e) {
            model.mostrarPopup("Erro", "Ocorreu um erro inesperado: " + e.getMessage(), Alert.AlertType.ERROR);  // mostra um popup se ocorrer um erro inesperado
            throw new RuntimeException(e);  // lança uma exceção em tempo de execução
        }
    }

    @FXML
    void removerManutencao(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtRemover.getText().trim());  // obtém o ID
            if (radioPatrimonioRemover.isSelected()) {
                TipoReserva tipoReserva = TipoReserva.PATRIMONIO;  // define o tipo como patrimônio
                model.getRequisitanteModel().removerReqManutencao(id, tipoReserva);  // remove a manutenção
            } else if (radioBemRemover.isSelected()) {
                TipoReserva tipoReserva = TipoReserva.BEM;  // define o tipo como bem
                model.getRequisitanteModel().removerReqManutencao(id, tipoReserva);  // remove a manutenção
            } else {
                model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);  // mostra um popup se valores não forem válidos
            }

            atualizarListaManutencoes();  // atualiza a lista de manutenções

            txtRemover.clear();  // limpa o campo de ID
        } catch (NumberFormatException e) {
            model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);  // mostra um popup se valores não forem válidos
            throw new RuntimeException(e);  // lança uma exceção em tempo de execução
        } catch (IOException | ManutencaoException e) {
            model.mostrarPopup("Erro", "Ocorreu um erro inesperado: " + e.getMessage(), Alert.AlertType.ERROR);  // mostra um popup se ocorrer um erro inesperado
            throw new RuntimeException(e);  // lança uma exceção em tempo de execução
        }
    }

    @FXML
    void irParaRequisicoes(ActionEvent event) throws IOException {
        ScreenController.activate("telaRequisitanteRequisicoes");  // ativa a tela de requisições do requisitante
    }

    @FXML
    void sair(ActionEvent event) throws IOException {
        // implementar a lógica para sair da tela
        ScreenController.activate("telaLogin");  // ativa a tela de login
        Model.deslogarUsuario();  // desloga o usuário
    }

}
