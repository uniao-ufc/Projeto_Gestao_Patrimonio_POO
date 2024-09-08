package br.ufc.sistemapatrimonio.controller;

import br.ufc.sistemapatrimonio.entities.Usuario;
import br.ufc.sistemapatrimonio.enums.TipoUsuario;
import br.ufc.sistemapatrimonio.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;
import java.util.HashMap;

public class LoginCadastroViewController {
    private final Model model = new Model();

    @FXML
    private TextField logLogin;

    @FXML
    private PasswordField logSenha;

    @FXML
    private TextField cadLogin;

    @FXML
    private PasswordField cadSenha;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnCadastro;

    @FXML
    private Button btnLogin;

    @FXML
    private RadioButton radioAdmin;

    @FXML
    private RadioButton radioRequi;

    private String login;

    private String senha;

    private String cadastroLogin;

    private String cadastroSenha;

    private TipoUsuario tipo;

    @FXML // Cadastra o usuario
    void cadastrar(ActionEvent event) {
        if(cadLogin.getText().trim().isEmpty() || cadSenha.getText().trim().isEmpty() || !radioAdmin.isSelected() && !radioRequi.isSelected()){
            model.mostrarPopup("Erro", "Informações não inseridas", AlertType.WARNING);
        }else{
            // Tratamento de dados e verificacao de cadastro
            cadastroLogin = cadLogin.getText().trim();
            cadastroSenha = cadSenha.getText().trim();

            if(radioAdmin.isSelected()){
                tipo = TipoUsuario.ADMINISTRADOR;
            }else if (radioRequi.isSelected()){
                tipo = TipoUsuario.REQUISITANTE;
            }
            // Verificando o resultado do metodo
            int resultado = model.getCadastroModel().cadastrarUsuario(cadastroLogin, cadastroSenha, tipo);

            switch (resultado) {
                case 1 -> model.mostrarPopup("Erro", "A conta já existe!", AlertType.WARNING);
                case 2 -> model.mostrarPopup("Erro", "Usuário ou senha incorretos!", AlertType.ERROR);
                case 3 -> model.mostrarPopup("Sucesso", "Cadastro realizado com sucesso!", AlertType.INFORMATION);
                default -> model.mostrarPopup("Erro", "Erro desconhecido!", AlertType.ERROR);
            }
           /*for (Usuario usuario : Model.users) {
                System.out.println(usuario.getUsername()+ " " + usuario.getId());
           }*/
        }
    }

    @FXML // Loga o usuario
    //BUG NO CADASTRO, SE VC TENTAR MUDAR O CARGO DE UM USUARIO EXISTENTE E TENTAR LGOO ELE PARA DE EXISTIR E VC PRECISA CRIAR ELE DNV
    void logarUsuario(ActionEvent event) throws IOException {
        if(logLogin.getText().trim().isEmpty() || logLogin.getText().trim().isEmpty()){
            model.mostrarPopup("Erro", "Informações não inseridas", AlertType.WARNING);
            // Erro se informacaoes nao forem inseridas
        }else {
            // Tratamento de dados e verificacao de login
            login = logLogin.getText().trim();
            senha = logSenha.getText().trim();

            HashMap<Integer, Usuario> resultado = model.getCadastroModel().autenticarUsuario(login, senha, tipo);

            if (resultado.containsKey(1)) {
                // Usuário autenticado com sucesso
                model.mostrarPopup("Sucesso", "Logado", AlertType.INFORMATION);
                model.setUsuarioAutenticado(resultado.get(1));
                // Ativa a tela de requisitante
                if(Model.getUsuarioAutenticado().getTipoUsuario() == TipoUsuario.REQUISITANTE){
                    ScreenController.activate("telaRequisitanteRequisicoes");
                }else{
                    ScreenController.activate("telaAdminRequisicoes");
                }
            } else {
                model.mostrarPopup("Erro", "Usuário ou senha incorretos!", AlertType.ERROR);
            }
        }
    }

    public void sair(ActionEvent actionEvent) {

    }
}