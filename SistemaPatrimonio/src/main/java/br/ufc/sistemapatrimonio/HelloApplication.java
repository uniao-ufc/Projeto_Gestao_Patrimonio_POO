package br.ufc.sistemapatrimonio;

import br.ufc.sistemapatrimonio.model.Model;
import br.ufc.sistemapatrimonio.controller.LoginViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
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
        launch();
    }
}