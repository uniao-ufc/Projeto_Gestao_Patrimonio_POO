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

public class LoginViewController{
    private final Model model = new Model();

    @FXML
    private Button btnCadastro;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField cadastroLogin;

    @FXML
    private PasswordField cadastroSenha;

    @FXML
    private TextField loginLogin;

    @FXML
    private PasswordField loginSenha;

    @FXML
    private RadioButton radioAdmin;

    @FXML
    private RadioButton radioRequi;

    private String login;
    private String senha;
    private String cadLogin;
    private String cadSenha;
    private TipoUsuario tipo;

    @FXML // Cadastra o usuario
    void cadastrar(ActionEvent event) {
        if(cadastroLogin.getText().trim().isEmpty() || cadastroSenha.getText().trim().isEmpty() || !radioAdmin.isSelected() && !radioRequi.isSelected()){
            model.mostrarPopup("Erro", "Informações não inseridas", AlertType.WARNING);
        }else{
            // Tratamento de dados e verificacao de cadastro
            cadLogin = cadastroLogin.getText().trim();
            cadSenha = cadastroSenha.getText().trim();

            if(radioAdmin.isSelected()){
                tipo = TipoUsuario.ADMINISTRADOR;
            }else if (radioRequi.isSelected()){
                tipo = TipoUsuario.REQUISITANTE;
            }
            // Verificando o resultado do metodo
            int resultado = model.cadastroModel.cadastrarUsuario(cadLogin, cadSenha, tipo);

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
    void logarUsuario(ActionEvent event) throws IOException {
        if(loginLogin.getText().trim().isEmpty() || loginSenha.getText().trim().isEmpty()){
            model.mostrarPopup("Erro", "Informações não inseridas", AlertType.WARNING);
            // Erro se informacaoes nao forem inseridas
        }else {
            // Tratamento de dados e verificacao de login
            login = loginLogin.getText().trim();
            senha = loginSenha.getText().trim();

            HashMap<Integer, Usuario> resultado = model.cadastroModel.autenticarUsuario(login, senha, tipo);

            if (resultado.containsKey(1)) {
                // Usuário autenticado com sucesso
                model.mostrarPopup("Sucesso", "Logado", AlertType.INFORMATION);
                model.setUsuarioAutenticado(resultado.get(1));
                // Ativa a tela de requisitante
                ScreenController.activate("telaRequisitante");
            } else {
                model.mostrarPopup("Erro", "Usuário ou senha incorretos!", AlertType.ERROR);
            }
        }
    }
}