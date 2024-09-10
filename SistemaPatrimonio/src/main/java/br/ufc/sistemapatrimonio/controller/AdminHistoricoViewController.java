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
        // atualiza o campo de texto com a lista de manutenções obtida do modelo
        txtHistoricoManutencoes.setText(model.getAdminModel().listarManutencoes());
    }
    
    public void atualizarListaReservas() {
        // atualiza o campo de texto com a lista de reservas obtida do modelo
        txtHistoricoRequisicoes.setText(model.getAdminModel().listarReservas());
    }
    

    @FXML
    void removerDoHistorico(ActionEvent event) {
        try {
            // converte o texto do campo txtRemover para um número inteiro, que representa o ID
            int id = Integer.parseInt(txtRemover.getText().trim());
            
            // verifica se a opção "requisicao" está selecionada
            if (radioRequisicao.isSelected()) {
                
                // verifica se o radioButton "patrimonio" está selecionado
                if (radioPatrimonio.isSelected()) {
                    // define o tipo de reserva e patrimônio, e remove do histórico
                    String tipoReserva = "reserva";
                    TipoReserva tipopatrimonio = TipoReserva.PATRIMONIO;
                    model.getAdminModel().removerHistorico(id, tipoReserva, tipopatrimonio);
                    atualizarListaManutencoes();
                    atualizarListaReservas();
                    
                // verifica se o radioButton "bem" está selecionado
                } else if (radioBem.isSelected()) {
                    // define o tipo de reserva e bem, e remove do histórico
                    String tipoReserva = "reserva";
                    TipoReserva tipobem = TipoReserva.BEM;
                    model.getAdminModel().removerHistorico(id, tipoReserva, tipobem);
                    atualizarListaManutencoes();
                    atualizarListaReservas();
                    
                // se nenhuma das opções foi selecionada, exibe um erro
                } else {
                    model.mostrarPopup("Erro", "Por favor, selecione valores válidos.", Alert.AlertType.ERROR);
                }
    
            // verifica se a opção "manutencao" está selecionada
            } else if (radioManutencao.isSelected()) {
                
                // verifica se o radioButton "patrimonio" está selecionado
                if (radioPatrimonio.isSelected()) {
                    // define o tipo de manutenção e patrimônio, e remove do histórico
                    String tipoReserva = "manutencao";
                    TipoReserva tipopatrimonio = TipoReserva.PATRIMONIO;
                    model.getAdminModel().removerHistorico(id, tipoReserva, tipopatrimonio);
                    atualizarListaManutencoes();
                    atualizarListaReservas();
                    
                // verifica se o radioButton "bem" está selecionado
                } else if (radioBem.isSelected()) {
                    // define o tipo de manutenção e bem, e remove do histórico
                    String tipoReserva = "manutencao";
                    TipoReserva tipobem = TipoReserva.BEM;
                    model.getAdminModel().removerHistorico(id, tipoReserva, tipobem);
                    atualizarListaManutencoes();
                    atualizarListaReservas();
                    
                // se nenhuma das opções foi selecionada, exibe um erro
                } else {
                    model.mostrarPopup("Erro", "Por favor, selecione valores válidos.", Alert.AlertType.ERROR);
                }
    
            // se nenhuma das opções principais foi selecionada, exibe um erro
            } else {
                model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);
            }
    
            // atualiza as listas de manutenção e reservas após a remoção
            atualizarListaManutencoes();
            atualizarListaReservas();
    
            // limpa o campo de texto após a operação
            txtRemover.clear();
            
        } catch (NumberFormatException e) {
            // exibe um erro se o ID inserido não for um número válido
            model.mostrarPopup("Erro", "Por favor, insira valores válidos.", Alert.AlertType.ERROR);
            throw new RuntimeException(e);
            
        } catch (IOException | BemException e) {
            // captura e trata exceções de IO ou relacionadas ao bem
            model.mostrarPopup("Erro", "Ocorreu um erro inesperado: " + e.getMessage(), Alert.AlertType.ERROR);
            throw new RuntimeException(e);
        }
    }
    

    @FXML
    void irParaBens(ActionEvent event) throws IOException {
        // ativa a tela de administração de bens
        ScreenController.activate("telaAdminBens");
    }
    
    @FXML
    void irParaManutencoes(ActionEvent event) throws IOException {
        // ativa a tela de administração de manutenções
        ScreenController.activate("telaAdminManutencoes");
    }
    
    @FXML
    void irParaPatrimonios(ActionEvent event) throws IOException {
        // ativa a tela de administração de patrimônios
        ScreenController.activate("telaAdminPatrimonios");
    }

    @FXML
    void sair(ActionEvent event) throws IOException {
        // Implementar a lógica para sair da tela
        ScreenController.activate("telaLogin");
        Model.deslogarUsuario();
    }
}
