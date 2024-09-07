package br.ufc.sistemapatrimonio;

import br.ufc.sistemapatrimonio.entities.Administrador;
import br.ufc.sistemapatrimonio.entities.Usuario;
import br.ufc.sistemapatrimonio.enums.TipoUsuario;
import br.ufc.sistemapatrimonio.model.Model;
import br.ufc.sistemapatrimonio.controller.LoginViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelloApplication extends Application {
    public static ArrayList<Usuario> user = new ArrayList<>();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("telaLogin.fxml"));
        Parent root = fxmlLoader.load();
        LoginViewController controller = (LoginViewController)fxmlLoader.getController();
        Scene scene = new Scene(root);
        stage.setTitle("Sistema de Patrimonio");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        Administrador mauro = new Administrador("Mauro", "mauro", TipoUsuario.ADMINISTRADOR);
        Usuario maure = new Usuario("Maure", "maure", TipoUsuario.ADMINISTRADOR);
        Model.users.add(mauro);
        Model.users.add(maure);
        System.out.println(Arrays.toString(Model.users.toArray()));
        launch();
    }
}