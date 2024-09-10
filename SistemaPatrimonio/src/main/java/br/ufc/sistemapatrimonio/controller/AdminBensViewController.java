package br.ufc.sistemapatrimonio.controller;

import br.ufc.sistemapatrimonio.exceptions.BemException;
import br.ufc.sistemapatrimonio.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminBensViewController {

    private final Model model = new Model();

    @FXML
    private TextField txtRemover;

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnIrParaPatrimonios;

    @FXML
    private Button btnIrParaRequisicoes;

    @FXML
    private Button btnRemover;

    @FXML
    private Button btnSair;

    @FXML
    private TextField txtDepressiacaoAdd;

    @FXML
    private TextField txtDepressiacaoEditar;

    @FXML
    private TextField txtDescricaoAdd;

    @FXML
    private TextField txtDescricaoEditar;

    @FXML
    private TextField txtIDAdd;

    @FXML
    private TextField txtIDEditar;

    @FXML
    private TextArea txtListaBens;

    @FXML
    private TextField txtNomeAdd;

    @FXML
    private TextField txtNomeEditar;

    @FXML
    private TextField txtTipoAdd;

    @FXML
    private TextField txtTipoEditar;

    @FXML
    // Método acionado ao adicionar um bem
    void adicionarBem(ActionEvent event) {
        try {
            // converte o texto do campo txtIDAdd para um número inteiro
            int id = Integer.parseInt(txtIDAdd.getText());
            
            // obtém o texto inserido no campo de nome
            String nome = txtNomeAdd.getText();
            
            // obtém a descrição do bem
            String descricao = txtDescricaoAdd.getText();
            
            // converte o texto do campo de depreciação para um número inteiro
            int depreciacao = Integer.parseInt(txtDepressiacaoAdd.getText());
            
            // obtém o tipo do bem a partir do campo txtTipoAdd
            String tipo = txtTipoAdd.getText();
    
            // chama o método para adicionar o bem com os dados informados
            model.getAdminModel().adicionarBem(id, nome, descricao, depreciacao, tipo);
    
            // limpa os campos de entrada após adicionar o bem
            txtIDAdd.clear();
            txtNomeAdd.clear();
            txtDescricaoAdd.clear();
            txtDepressiacaoAdd.clear();
            txtTipoAdd.clear();
    
            // exibe uma mensagem de sucesso após adicionar o bem
            model.mostrarPopup("Sucesso", "Bem adicionado com sucesso!", Alert.AlertType.INFORMATION);
    
            // atualiza a lista de bens exibida na interface
            atualizarListaBens();
        } catch (NumberFormatException e) {
            // exibe um erro se o valor inserido não for um número válido
            model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);
        } catch (BemException e) {
            // captura exceções específicas do sistema de bens e exibe um erro
            model.mostrarPopup("Erro", "Ocorreu um erro inesperado: " + e.getMessage(), Alert.AlertType.ERROR);
            throw new RuntimeException(e);
        }
    }
    

    @FXML
    // Método acionado para editar um bem
    void editarBem(ActionEvent event) {
        try {
            // converte o texto do campo txtIDEditar para um número inteiro
            int id = Integer.parseInt(txtIDEditar.getText());
            
            // obtém o nome do bem a partir do campo txtNomeEditar
            String nome = txtNomeEditar.getText();
            
            // obtém a descrição do bem a partir do campo txtDescricaoEditar
            String descricao = txtDescricaoEditar.getText();
            
            // converte o texto do campo txtDepressiacaoEditar para um número inteiro
            int depreciacao = Integer.parseInt(txtDepressiacaoEditar.getText());
            
            // obtém o tipo do bem a partir do campo txtTipoEditar
            String tipo = txtTipoEditar.getText();
    
            // chama o método para editar o bem com os novos valores
            model.getAdminModel().editarBem(id, nome, descricao, depreciacao, tipo);
    
            // limpa os campos de edição após a operação
            txtIDEditar.clear();
            txtNomeEditar.clear();
            txtDescricaoEditar.clear();
            txtDepressiacaoEditar.clear();
            txtTipoEditar.clear();
    
            // exibe uma mensagem de sucesso após a edição do bem
            model.mostrarPopup("Sucesso", "Bem editado com sucesso!", Alert.AlertType.INFORMATION);
    
            // atualiza a lista de bens exibida na interface
            atualizarListaBens();
        } catch (NumberFormatException e) {
            // exibe um erro se os valores inseridos não forem válidos
            model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);
        } catch (BemException e) {
            // captura exceções relacionadas ao bem, como bem não encontrado ou erro genérico
            switch (e.getErroCode()) {
                case BemException.NAO_ENCONTRADO, BemException.ERRO ->
                        model.mostrarPopup("Erro", e.getMessage(), Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            // captura qualquer outro tipo de exceção e exibe uma mensagem de erro
            model.mostrarPopup("Erro", "Ocorreu um erro inesperado: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    

    @FXML
    // Método acionado para remover um bem
    void removerBem(ActionEvent event) {
        try {
            // converte o texto do campo txtRemover para um número inteiro, que é o ID do bem a ser removido
            int id = Integer.parseInt(txtRemover.getText());
    
            // chama o método para remover o bem com o ID fornecido
            model.getAdminModel().removerBem(id);
    
            // limpa o campo de texto após a remoção
            txtRemover.clear();
    
            // exibe uma mensagem de sucesso após remover o bem
            model.mostrarPopup("Sucesso", "Bem removido com sucesso!", Alert.AlertType.INFORMATION);
    
            // atualiza a lista de bens exibida na interface
            atualizarListaBens();
        } catch (NumberFormatException e) {
            // exibe um erro caso o ID inserido não seja um número válido
            model.mostrarPopup("Erro", "Por favor, insira um ID válido.", Alert.AlertType.ERROR);
        } catch (BemException e) {
            // trata exceções específicas do bem, como bem não encontrado ou erro genérico
            switch (e.getErroCode()) {
                case BemException.NAO_ENCONTRADO, BemException.ERRO ->
                        model.mostrarPopup("Erro", e.getMessage(), Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            // captura qualquer outro tipo de exceção e exibe uma mensagem de erro genérico
            model.mostrarPopup("Erro", "Ocorreu um erro inesperado: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    

    @FXML
    void irParaPatrimonios(ActionEvent event) throws IOException {
        // Implementar navegação para a tela de Patrimonios
        ScreenController.activate("telaAdminPatrimonios");
    }

    @FXML
    void irParaRequisicoes(ActionEvent event) throws IOException {
        // Implementar navegação para a tela de Requisições
        ScreenController.activate("telaAdminRequisicoes");
    }

    @FXML
    void sair(ActionEvent event) throws IOException {
        // Implementar a lógica para sair da tela
        ScreenController.activate("telaLogin");
        Model.deslogarUsuario();
    }
    // Atualiza a lista de bens, geralmente após uma modificação ou cadastro
    public void atualizarListaBens() {
        txtListaBens.setText(model.getAdminModel().listarBens());
    }
}
