package br.ufc.sistemapatrimonio.controller;

import br.ufc.sistemapatrimonio.exceptions.PatrimonioException;
import br.ufc.sistemapatrimonio.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminPatrimoniosViewController {

    private final Model model = new Model();  // Instancia o modelo que gerencia as operações de patrimônio

    @FXML
    private TextField txtRemover;  // Campo para inserir o ID do patrimônio a ser removido

    @FXML
    private Button btnAdicionar;  // Botão para adicionar um novo patrimônio

    @FXML
    private Button btnEditar;  // Botão para editar um patrimônio existente

    @FXML
    private Button btnIrParaBens;  // Botão para navegar para a tela de Bens

    @FXML
    private Button btnIrParaRequisicoes;  // Botão para navegar para a tela de Requisições

    @FXML
    private Button btnRemover;  // Botão para remover um patrimônio

    @FXML
    private Button btnSair;  // Botão para sair da tela atual

    @FXML
    private TextField txtDepressiacaoAdd;  // Campo para inserir a depreciação ao adicionar um patrimônio

    @FXML
    private TextField txtDepressiacaoEditar;  // Campo para inserir a depreciação ao editar um patrimônio

    @FXML
    private TextField txtDescricaoAdd;  // Campo para inserir a descrição ao adicionar um patrimônio

    @FXML
    private TextField txtDescricaoEditar;  // Campo para inserir a descrição ao editar um patrimônio

    @FXML
    private TextField txtIDAdd;  // Campo para inserir o ID ao adicionar um patrimônio

    @FXML
    private TextField txtIDEditar;  // Campo para inserir o ID ao editar um patrimônio

    @FXML
    private TextArea txtListaPatrimonios;  // Área de texto para listar os patrimônios

    @FXML
    private TextField txtNomeAdd;  // Campo para inserir o nome ao adicionar um patrimônio

    @FXML
    private TextField txtNomeEditar;  // Campo para inserir o nome ao editar um patrimônio

    @FXML
    private TextField txtNumeroDeTombamentoAdd;  // Campo para inserir o número de tombamento ao adicionar um patrimônio

    @FXML
    private TextField txtNumeroDeTombamentoEditar;  // Campo para inserir o número de tombamento ao editar um patrimônio

    @FXML
    private TextField txtTipoAdd;  // Campo para inserir o tipo ao adicionar um patrimônio

    @FXML
    private TextField txtTipoEditar;  // Campo para inserir o tipo ao editar um patrimônio

    public void initialize() {
        atualizarPatrimonios();  // Atualiza a lista de patrimônios ao inicializar o controlador
    }

    public void atualizarPatrimonios() {
        String patrimoniosListados = model.getAdminModel().listarPatrimonios();  // Obtém a lista de patrimônios
        txtListaPatrimonios.setText(patrimoniosListados);  // Atualiza a TextArea com a lista de patrimônios
    }

    @FXML
    void adicionarPatrimonio(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtIDAdd.getText());  // Obtém e converte o ID do campo de texto
            String nome = txtNomeAdd.getText();  // Obtém o nome do campo de texto
            String descricao = txtDescricaoAdd.getText();  // Obtém a descrição do campo de texto
            double depreciacao = Double.parseDouble(txtDepressiacaoAdd.getText());  // Obtém e converte a depreciação do campo de texto
            String tipo = txtTipoAdd.getText();  // Obtém o tipo do campo de texto
            int numeroTombamento = Integer.parseInt(txtNumeroDeTombamentoAdd.getText());  // Obtém e converte o número de tombamento do campo de texto

            model.getAdminModel().adicionarPatrimonio(id, nome, descricao, depreciacao, tipo, numeroTombamento);  // Adiciona o patrimônio ao modelo

            // Limpa os campos de texto após a adição
            txtIDAdd.clear();
            txtNomeAdd.clear();
            txtDescricaoAdd.clear();
            txtDepressiacaoAdd.clear();
            txtTipoAdd.clear();
            txtNumeroDeTombamentoAdd.clear();

            model.mostrarPopup("Sucesso", "Patrimônio adicionado com sucesso!", Alert.AlertType.INFORMATION);  // Mostra uma mensagem de sucesso
            atualizarListaPatrimonios();  // Atualiza a lista de patrimônios
        } catch (NumberFormatException e) {
            model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);  // Mostra uma mensagem de erro se os valores forem inválidos
        } catch (PatrimonioException e) {
            model.mostrarPopup("Erro", "Ocorreu um erro inesperado: " + e.getMessage(), Alert.AlertType.ERROR);  // Mostra uma mensagem de erro se ocorrer uma exceção específica
        }
    }

    @FXML
    void editarPatrimonio(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtIDEditar.getText());  // Obtém e converte o ID do campo de texto
            String nome = txtNomeEditar.getText();  // Obtém o nome do campo de texto
            String descricao = txtDescricaoEditar.getText();  // Obtém a descrição do campo de texto
            Double depreciacao = Double.parseDouble(txtDepressiacaoEditar.getText());  // Obtém e converte a depreciação do campo de texto
            String tipo = txtTipoEditar.getText();  // Obtém o tipo do campo de texto
            int numeroTombamento = Integer.parseInt(txtNumeroDeTombamentoEditar.getText());  // Obtém e converte o número de tombamento do campo de texto

            model.getAdminModel().editarPatrimonio(id, nome, descricao, depreciacao, tipo, numeroTombamento);  // Edita o patrimônio no modelo

            // Limpa os campos de texto após a edição
            txtIDEditar.clear();
            txtNomeEditar.clear();
            txtDescricaoEditar.clear();
            txtDepressiacaoEditar.clear();
            txtTipoEditar.clear();
            txtNumeroDeTombamentoEditar.clear();

            model.mostrarPopup("Sucesso", "Patrimônio editado com sucesso!", Alert.AlertType.INFORMATION);  // Mostra uma mensagem de sucesso
            atualizarListaPatrimonios();  // Atualiza a lista de patrimônios
        } catch (NumberFormatException e) {
            model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);  // Mostra uma mensagem de erro se os valores forem inválidos
        } catch (PatrimonioException e) {
            switch (e.getErroCode()) {
                case PatrimonioException.NAO_ENCONTRADO, PatrimonioException.ERRO ->
                        model.mostrarPopup("Erro", e.getMessage(), Alert.AlertType.ERROR);  // Mostra uma mensagem de erro para códigos de erro específicos
            }
        } catch (Exception e) {
            model.mostrarPopup("Erro", "Ocorreu um erro inesperado: " + e.getMessage(), Alert.AlertType.ERROR);  // Mostra uma mensagem de erro para outras exceções
        }
    }

    @FXML
    void removerPatrimonio(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtRemover.getText());  // Obtém e converte o ID do campo de texto

            model.getAdminModel().removerPatrimonio(id);  // Remove o patrimônio do modelo

            txtRemover.clear();  // Limpa o campo de texto após a remoção

            model.mostrarPopup("Sucesso", "Patrimônio removido com sucesso!", Alert.AlertType.INFORMATION);  // Mostra uma mensagem de sucesso

            atualizarListaPatrimonios();  // Atualiza a lista de patrimônios
        } catch (NumberFormatException e) {
            model.mostrarPopup("Erro", "Por favor, insira um ID válido.", Alert.AlertType.ERROR);  // Mostra uma mensagem de erro se o ID for inválido
        } catch (PatrimonioException e) {
            switch (e.getErroCode()) {
                case PatrimonioException.NAO_ENCONTRADO, PatrimonioException.ERRO ->
                        model.mostrarPopup("Erro", e.getMessage(), Alert.AlertType.ERROR);  // Mostra uma mensagem de erro para códigos de erro específicos
            }
        } catch (Exception e) {
            model.mostrarPopup("Erro", "Ocorreu um erro inesperado: " + e.getMessage(), Alert.AlertType.ERROR);  // Mostra uma mensagem de erro para outras exceções
        }
    }

    public void atualizarListaPatrimonios() {
        txtListaPatrimonios.setText(model.getAdminModel().listarPatrimonios());  // Atualiza a TextArea com a lista de patrimônios
    }

    @FXML
    void irParaBens(ActionEvent event) throws IOException {
        // Implementar navegação para a tela de Bens
        ScreenController.activate("telaAdminBens");  // Ativa a tela de administração de Bens
    }

    @FXML
    void irParaRequisicoes(ActionEvent event) throws IOException {
        ScreenController.activate("telaAdminRequisicoes");  // Ativa a tela de administração de Requisições
    }

    @FXML
    void sair(ActionEvent event) throws IOException {
        // Implementar a lógica para sair da tela
        ScreenController.activate("telaLogin");  // Ativa a tela de login
        Model.deslogarUsuario();  // Desloga o usuário
    }

}
