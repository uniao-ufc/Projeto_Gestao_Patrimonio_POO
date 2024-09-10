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
    Model model = new Model();  // instancia o modelo que gerencia as operações de requisição

    @FXML
    private RadioButton radioPatrimonioRemover;  // botão de opção para remover um patrimônio

    @FXML
    private RadioButton radioBemRemover;  // botão de opção para remover um bem

    @FXML
    private Button btnAdicionar;  // botão para adicionar uma nova requisição

    @FXML
    private Button btnIrParaBens;  // botão para navegar para a tela de Bens

    @FXML
    private Button btnIrParaHistoricos;  // botão para navegar para a tela de Históricos

    @FXML
    private Button btnIrParaManutencoes;  // botão para navegar para a tela de Manutenções

    @FXML
    private Button btnIrParaPatrimonios;  // botão para navegar para a tela de Patrimônios

    @FXML
    private Button btnRemover;  // botão para remover uma requisição

    @FXML
    private Button btnSair;  // botão para sair da tela atual

    @FXML
    private RadioButton radioBem;  // botão de opção para adicionar um bem

    @FXML
    private ToggleGroup radioOpcoes;  // grupo de botões de opção para selecionar entre bem e patrimônio

    @FXML
    private RadioButton radioPatrimonio;  // botão de opção para adicionar um patrimônio

    @FXML
    private TextField txtDescricaoAdd;  // campo para inserir a descrição ao adicionar uma requisição

    @FXML
    private TextField txtIDAdd;  // campo para inserir o ID ao adicionar uma requisição

    @FXML
    private TextArea txtListaRequisicoes;  // área de texto para listar as requisições

    @FXML
    private TextField txtLocalAdd;  // campo para inserir o local ao adicionar uma requisição

    @FXML
    private TextField txtNomeAdd;  // campo para inserir o nome ao adicionar uma requisição

    @FXML
    private TextField txtRemover;  // campo para inserir o ID da requisição a ser removida

    @FXML
    void adicionarRequisicao(ActionEvent event) {
        try {
            if (radioBem.isSelected()) {
                // adicionar bem
                int id = Integer.parseInt(txtIDAdd.getText().trim());  // obtém e converte o ID do campo de texto
                String nome = txtNomeAdd.getText();  // obtém o nome do campo de texto
                String local = txtLocalAdd.getText();  // obtém o local do campo de texto
                String descricao = txtDescricaoAdd.getText();  // obtém a descrição do campo de texto
                TipoReserva tipo = TipoReserva.BEM;  // define o tipo como BEM

                if (nome.trim().isEmpty() || local.trim().isEmpty() || descricao.trim().isEmpty()) {
                    model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);  // mostra uma mensagem de aviso se informações estiverem faltando
                } else {
                    model.getAdminModel().adicionarRequisicao(id, nome, local, descricao, tipo);  // adiciona a requisição ao modelo

                    model.mostrarPopup("Sucesso", "Bem requisitado com sucesso!", Alert.AlertType.INFORMATION);  // mostra uma mensagem de sucesso

                    txtIDAdd.clear();
                    txtNomeAdd.clear();
                    txtLocalAdd.clear();
                    txtDescricaoAdd.clear();
                    atualizarListaRequisicoes();  // atualiza a lista de requisições
                }
            } else if (radioPatrimonio.isSelected()) {
                // adicionar patrimonio
                int id = Integer.parseInt(txtIDAdd.getText().trim());  // obtém e converte o ID do campo de texto
                String nome = txtNomeAdd.getText();  // obtém o nome do campo de texto
                String local = txtLocalAdd.getText();  // obtém o local do campo de texto
                String descricao = txtDescricaoAdd.getText();  // obtém a descrição do campo de texto
                TipoReserva tipo = TipoReserva.PATRIMONIO;  // define o tipo como PATRIMONIO

                if (nome.trim().isEmpty() || local.trim().isEmpty() || descricao.trim().isEmpty()) {
                    model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);  // mostra uma mensagem de aviso se informações estiverem faltando
                } else {
                    model.getAdminModel().adicionarRequisicao(id, nome, local, descricao, tipo);  // adiciona a requisição ao modelo

                    model.mostrarPopup("Sucesso", "Patrimonio requisitado com sucesso!", Alert.AlertType.INFORMATION);  // mostra uma mensagem de sucesso

                    // atualizarListaBens();  // não utilizado no momento
                    txtIDAdd.clear();
                    txtNomeAdd.clear();
                    txtLocalAdd.clear();
                    txtDescricaoAdd.clear();
                    atualizarListaRequisicoes();  // atualiza a lista de requisições
                }
            } else {
                model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);  // mostra uma mensagem de aviso se nenhuma opção estiver selecionada
            }
        } catch (NumberFormatException e) {
            model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);  // mostra uma mensagem de erro se os valores forem inválidos
        } catch (BemException | PatrimonioException | IOException e) {
            model.mostrarPopup("Erro", "Ocorreu um erro inesperado: " + e.getMessage(), Alert.AlertType.ERROR);  // mostra uma mensagem de erro para exceções específicas
            throw new RuntimeException(e);  // lança uma exceção de runtime
        }
    }

    public void atualizarListaRequisicoes() {
        txtListaRequisicoes.setText(model.getAdminModel().listarReservasUsuario());  // atualiza a área de texto com a lista de requisições
    }

    @FXML
    void removerRequisicao(ActionEvent event) {
        // quando apagar aqui apagar em tudo
        try {
            int id = Integer.parseInt(txtRemover.getText().trim());  // obtém e converte o ID do campo de texto
            if (radioPatrimonioRemover.isSelected()) {
                TipoReserva tipoReserva = TipoReserva.PATRIMONIO;  // define o tipo como PATRIMONIO
                model.getAdminModel().removerRequisicao(id, tipoReserva);  // remove a requisição do modelo
            } else if (radioBemRemover.isSelected()) {
                TipoReserva tipoReserva = TipoReserva.BEM;  // define o tipo como BEM
                model.getAdminModel().removerRequisicao(id, tipoReserva);  // remove a requisição do modelo
            } else {
                model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);  // mostra uma mensagem de erro se nenhuma opção estiver selecionada
            }

            atualizarListaRequisicoes();  // atualiza a lista de requisições

            txtRemover.clear();  // limpa o campo de texto após a remoção
        } catch (NumberFormatException e) {
            model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);  // mostra uma mensagem de erro se os valores forem inválidos
            throw new RuntimeException(e);  // lança uma exceção de runtime
        } catch (IOException | PatrimonioException | BemException e) {
            model.mostrarPopup("Erro", "Ocorreu um erro inesperado: " + e.getMessage(), Alert.AlertType.ERROR);  // mostra uma mensagem de erro para exceções específicas
            throw new RuntimeException(e);  // lança uma exceção de runtime
        }
    }

    @FXML
    void irParaBens(ActionEvent event) throws IOException {
        ScreenController.activate("telaAdminBens");  // ativa a tela de administração de Bens
    }

    @FXML
    void irParaHistoricos(ActionEvent event) throws IOException {
        ScreenController.activate("telaAdminHistorico");  // ativa a tela de administração de Históricos
    }

    @FXML
    void irParaManutencoes(ActionEvent event) throws IOException {
        ScreenController.activate("telaAdminManutencoes");  // ativa a tela de administração de Manutenções
    }

    @FXML
    void irParaPatrimonios(ActionEvent event) throws IOException {
        ScreenController.activate("telaAdminPatrimonios");  // ativa a tela de administração de Patrimônios
    }

    @FXML
    void sair(ActionEvent event) throws IOException {
        // implementar a lógica para sair da tela
        ScreenController.activate("telaLogin");  // ativa a tela de login
        Model.deslogarUsuario();  // desloga o usuário
    }

}
