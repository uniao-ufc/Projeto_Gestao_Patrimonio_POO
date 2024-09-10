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

public class AdminManutencoesViewController {

    Model model = new Model();
    @FXML
    private RadioButton radioBemRemover;
    @FXML
    private RadioButton radioPatrimonioRemover;
    @FXML
    private Button btnAdicionar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnIrParaBens;
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
    private TextArea txtListaManutencao;
    @FXML
    private TextField txtNomeAdd;
    @FXML
    private TextField txtNomeEditar;
    @FXML
    private TextField txtRemover;
    @FXML
    private TextField txtTipoEditar;

    public void atualizarListaManutencoes() {
        // atualiza o campo de texto com a lista de manutenções do usuário
        txtListaManutencao.setText(model.getAdminModel().listarManutencoesUsuario());
    }
    
    @FXML
    void adicionarManutencao(ActionEvent event) {
        try {
            // verifica se o RadioButton para adicionar bem está selecionado
            if (radioBemAdd.isSelected()) {
                // adiciona um bem
                int id = Integer.parseInt(txtIDAdd.getText().trim());
                String nome = txtNomeAdd.getText();
                String descricao = txtDefeitoAdd.getText();
                TipoReserva tipo = TipoReserva.BEM;
    
                // verifica se os campos nome e descrição estão preenchidos
                if (nome.trim().isEmpty() || descricao.trim().isEmpty()) {
                    model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);
                } else {
                    // chama o método para adicionar uma manutenção de bem
                    model.getAdminModel().adicionarManutencao(id, nome, descricao, tipo);
    
                    // exibe uma mensagem de sucesso
                    model.mostrarPopup("Sucesso", "Manutenção de Bem requisitada com sucesso!", Alert.AlertType.INFORMATION);
    
                    // atualiza a lista de manutenções e limpa os campos de texto
                    atualizarListaManutencoes();
                    txtIDAdd.clear();
                    txtNomeAdd.clear();
                    txtDefeitoAdd.clear();
                }
    
            // verifica se o RadioButton para adicionar patrimônio está selecionado
            } else if (radioPatrimonioAdd.isSelected()) {
                // adiciona um patrimônio
                int id = Integer.parseInt(txtIDAdd.getText().trim());
                String nome = txtNomeAdd.getText();
                String descricao = txtDefeitoAdd.getText();
                TipoReserva tipo = TipoReserva.PATRIMONIO;
    
                // verifica se os campos nome e descrição estão preenchidos
                if (nome.trim().isEmpty() || descricao.trim().isEmpty()) {
                    model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);
                } else {
                    // chama o método para adicionar uma manutenção de patrimônio
                    model.getAdminModel().adicionarManutencao(id, nome, descricao, tipo);
    
                    // exibe uma mensagem de sucesso
                    model.mostrarPopup("Sucesso", "Manutenção de Patrimonio requisitada com sucesso!", Alert.AlertType.INFORMATION);
    
                    // atualiza a lista de manutenções e limpa os campos de texto
                    atualizarListaManutencoes();
                    txtIDAdd.clear();
                    txtNomeAdd.clear();
                    txtDefeitoAdd.clear();
                }
    
            // exibe uma mensagem de erro se nenhum RadioButton estiver selecionado
            } else {
                model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);
            }
    
        // captura exceções de formato inválido e exibe um erro
        } catch (NumberFormatException e) {
            model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);
    
        // captura exceções relacionadas a bem, patrimônio ou I/O e exibe um erro
        } catch (BemException | PatrimonioException | IOException e) {
            model.mostrarPopup("Erro", "Ocorreu um erro inesperado: " + e.getMessage(), Alert.AlertType.ERROR);
            throw new RuntimeException(e);
        }
    }
    

    @FXML
    void editarManutencao(ActionEvent event) {
        try {
            // verifica se o RadioButton para editar um bem está selecionado
            if (radioBemEditar.isSelected()) {
                // edita uma manutenção de bem
                int id = Integer.parseInt(txtIDEditar.getText().trim());
                String nome = txtNomeEditar.getText();
                String descricao = txtTipoEditar.getText();
                TipoReserva tipo = TipoReserva.BEM;
    
                // verifica se os campos de nome e descrição estão preenchidos
                if (nome.trim().isEmpty() || descricao.trim().isEmpty()) {
                    model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);
                } else {
                    // edita a manutenção de bem
                    model.getAdminModel().editarManutencao(id, nome, descricao, tipo);
    
                    // exibe uma mensagem de sucesso
                    model.mostrarPopup("Sucesso", "Manutenção editada!", Alert.AlertType.INFORMATION);
    
                    // atualiza a lista de manutenções e limpa os campos
                    atualizarListaManutencoes();
                    txtIDEditar.clear();
                    txtNomeEditar.clear();
                    txtTipoEditar.clear();
                }
    
            // verifica se o RadioButton para editar um patrimônio está selecionado
            } else if (radioPatrimonioAdd.isSelected()) {
                // edita uma manutenção de patrimônio
                int id = Integer.parseInt(txtIDEditar.getText().trim());
                String nome = txtNomeEditar.getText();
                String descricao = txtTipoEditar.getText();
                TipoReserva tipo = TipoReserva.PATRIMONIO;
    
                // verifica se os campos de nome e descrição estão preenchidos
                if (nome.trim().isEmpty() || descricao.trim().isEmpty()) {
                    model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);
                } else {
                    // edita a manutenção de patrimônio
                    model.getAdminModel().editarManutencao(id, nome, descricao, tipo);
    
                    // exibe uma mensagem de sucesso
                    model.mostrarPopup("Sucesso", "Manutenção editada!", Alert.AlertType.INFORMATION);
    
                    // atualiza a lista de manutenções e limpa os campos
                    atualizarListaManutencoes();
                    txtIDEditar.clear();
                    txtNomeEditar.clear();
                    txtTipoEditar.clear();
                }
    
            // exibe uma mensagem de erro se nenhuma opção válida estiver selecionada
            } else {
                model.mostrarPopup("Erro", "Informações não inseridas!", Alert.AlertType.WARNING);
            }
    
        // captura exceções de formato inválido
        } catch (NumberFormatException e) {
            model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);
    
        // captura exceções relacionadas à manutenção ou I/O
        } catch (IOException | ManutencaoException e) {
            model.mostrarPopup("Erro", "Ocorreu um erro inesperado: " + e.getMessage(), Alert.AlertType.ERROR);
            throw new RuntimeException(e);
        }
    }
    
    @FXML
    void removerManutencao(ActionEvent event) {
        try {
            // converte o texto do campo txtRemover para um número inteiro
            int id = Integer.parseInt(txtRemover.getText().trim());
    
            // verifica se o RadioButton de patrimônio está selecionado
            if (radioPatrimonioRemover.isSelected()) {
                TipoReserva tipoReserva = TipoReserva.PATRIMONIO;
                model.getAdminModel().removerReqManutencao(id, tipoReserva);
    
            // verifica se o RadioButton de bem está selecionado
            } else if (radioBemRemover.isSelected()) {
                TipoReserva tipoReserva = TipoReserva.BEM;
                model.getAdminModel().removerReqManutencao(id, tipoReserva);
    
            // exibe uma mensagem de erro se nenhuma opção válida estiver selecionada
            } else {
                model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);
            }
    
            // atualiza a lista de manutenções e limpa o campo de remoção
            atualizarListaManutencoes();
            txtRemover.clear();
    
        // captura exceções de formato inválido
        } catch (NumberFormatException e) {
            model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);
            throw new RuntimeException(e);
    
        // captura exceções relacionadas à I/O ou manutenção
        } catch (IOException e) {
            model.mostrarPopup("Erro", "Ocorreu um erro inesperado: " + e.getMessage(), Alert.AlertType.ERROR);
            throw new RuntimeException(e);
        } catch (ManutencaoException e) {
            throw new RuntimeException(e);
        }
    }
    
    @FXML
    void irParaBens(ActionEvent event) throws IOException {
        // ativa a tela de administração de bens
        ScreenController.activate("telaAdminBens");
    }
    
    @FXML
    void irParaRequisicoes(ActionEvent event) throws IOException {
        // ativa a tela de administração de requisições
        ScreenController.activate("telaAdminRequisicoes");
    }
    
    @FXML
    void sair(ActionEvent event) throws IOException {
        // ativa a tela de login e desloga o usuário
        ScreenController.activate("telaLogin");
        Model.deslogarUsuario();
    }
}  
