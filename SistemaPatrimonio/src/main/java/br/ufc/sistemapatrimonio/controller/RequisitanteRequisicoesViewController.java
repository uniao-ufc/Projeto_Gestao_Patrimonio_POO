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
    private RadioButton radioBemRemover;  // botão de opção para remover bem

    @FXML
    private RadioButton radioPatrimonioRemover;  // botão de opção para remover patrimônio

    @FXML
    private TextField txtRemover;  // campo para ID na remoção

    @FXML
    private Button btnRemover;  // botão para remover requisição

    @FXML
    private TextField txtIDAdd;  // campo para ID na adição

    @FXML
    private TextField txtNomeAdd;  // campo para nome na adição

    @FXML
    private TextField txtLocalAdd;  // campo para local na adição

    @FXML
    private Button btnAdicionar;  // botão para adicionar requisição

    @FXML
    private Button btnIrParaManutencoes;  // botão para ir para a tela de manutenções

    @FXML
    private RadioButton radioBemAdd;  // botão de opção para adicionar bem

    @FXML
    private RadioButton radioPatrimonioAdd;  // botão de opção para adicionar patrimônio

    @FXML
    private TextField txtDescricaoAdd;  // campo para descrição na adição

    @FXML
    private Button btnSair;  // botão para sair

    @FXML
    private TextArea txtListaRequisicoes;  // área de texto para listar requisições

    public void initialize() {
        atualizarReservas();  // atualiza a lista de reservas quando a tela é inicializada
    }

    public void atualizarReservas() {
        String reservasListadas = model.getRequisitanteModel().listarReservasUsuario();  // chama a função que lista as reservas
        txtListaRequisicoes.setText(reservasListadas);  // atualiza o conteúdo da área de texto
    }

    public void atualizarListaRequisicoes() {
        txtListaRequisicoes.setText(model.getRequisitanteModel().listarReservasUsuario());  // atualiza a lista de requisições
    }

    public void adicionarRequisicao(ActionEvent actionEvent) {
        try {
            if (radioBemAdd.isSelected()) {
                // adicionar bem
                int id = Integer.parseInt(txtIDAdd.getText().trim());  // obtém o ID
                String nome = txtNomeAdd.getText();  // obtém o nome
                String local = txtLocalAdd.getText();  // obtém o local
                String descricao = txtDescricaoAdd.getText();  // obtém a descrição
                TipoReserva tipo = TipoReserva.BEM;  // define o tipo como bem

                if (nome.trim().isEmpty() || local.trim().isEmpty() || descricao.trim().isEmpty()) {
                    model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);  // mostra um popup se informações não forem inseridas
                } else {
                    model.getRequisitanteModel().adicionarRequisicao(id, nome, local, descricao, tipo);  // adiciona a requisição

                    model.mostrarPopup("Sucesso", "Bem requisitado com sucesso!", Alert.AlertType.INFORMATION);  // mostra um popup de sucesso

                    txtIDAdd.clear();  // limpa o campo de ID
                    txtNomeAdd.clear();  // limpa o campo de nome
                    txtLocalAdd.clear();  // limpa o campo de local
                    txtDescricaoAdd.clear();  // limpa o campo de descrição
                    atualizarListaRequisicoes();  // atualiza a lista de requisições
                }
            } else if (radioPatrimonioAdd.isSelected()) {
                // adicionar patrimônio
                int id = Integer.parseInt(txtIDAdd.getText().trim());  // obtém o ID
                String nome = txtNomeAdd.getText();  // obtém o nome
                String local = txtLocalAdd.getText();  // obtém o local
                String descricao = txtDescricaoAdd.getText();  // obtém a descrição
                TipoReserva tipo = TipoReserva.PATRIMONIO;  // define o tipo como patrimônio

                if (nome.trim().isEmpty() || local.trim().isEmpty() || descricao.trim().isEmpty()) {
                    model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);  // mostra um popup se informações não forem inseridas
                } else {
                    model.getRequisitanteModel().adicionarRequisicao(id, nome, local, descricao, tipo);  // adiciona a requisição

                    model.mostrarPopup("Sucesso", "Patrimonio requisitado com sucesso!", Alert.AlertType.INFORMATION);  // mostra um popup de sucesso

                    txtIDAdd.clear();  // limpa o campo de ID
                    txtNomeAdd.clear();  // limpa o campo de nome
                    txtLocalAdd.clear();  // limpa o campo de local
                    txtDescricaoAdd.clear();  // limpa o campo de descrição
                    atualizarListaRequisicoes();  // atualiza a lista de requisições
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

    public void removerRequisicao(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(txtRemover.getText().trim());  // obtém o ID
            if (radioPatrimonioRemover.isSelected()) {
                TipoReserva tipoReserva = TipoReserva.PATRIMONIO;  // define o tipo como patrimônio
                model.getRequisitanteModel().removerRequisicao(id, tipoReserva);  // remove a requisição
            } else if (radioBemRemover.isSelected()) {
                TipoReserva tipoReserva = TipoReserva.BEM;  // define o tipo como bem
                model.getRequisitanteModel().removerRequisicao(id, tipoReserva);  // remove a requisição
            } else {
                model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);  // mostra um popup se valores não forem válidos
            }

            atualizarListaRequisicoes();  // atualiza a lista de requisições

            txtRemover.clear();  // limpa o campo de ID
        } catch (NumberFormatException e) {
            model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);  // mostra um popup se valores não forem válidos
            throw new RuntimeException(e);  // lança uma exceção em tempo de execução
        } catch (IOException | PatrimonioException | BemException e) {
            model.mostrarPopup("Erro", "Ocorreu um erro inesperado: " + e.getMessage(), Alert.AlertType.ERROR);  // mostra um popup se ocorrer um erro inesperado
            throw new RuntimeException(e);  // lança uma exceção em tempo de execução
        }
    }

    public void irParaManutencoes(ActionEvent actionEvent) throws IOException {
        ScreenController.activate("telaRequisitanteManutencoes");  // ativa a tela de manutenções do requisitante
    }

    @FXML
    void sair(ActionEvent event) throws IOException {
        // implementar a lógica para sair da tela
        ScreenController.activate("telaLogin");  // ativa a tela de login
        Model.deslogarUsuario();  // desloga o usuário
    }

}
