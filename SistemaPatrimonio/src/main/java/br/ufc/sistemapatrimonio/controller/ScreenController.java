package br.ufc.sistemapatrimonio.controller;

import br.ufc.sistemapatrimonio.entities.Usuario;
import br.ufc.sistemapatrimonio.model.Model;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;

public class ScreenController {
    private static final HashMap<String, Scene> sceneMap = new HashMap<>();
    private static Stage stage;

    // Define o Stage principal
    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    // Adiciona uma tela ao mapa de cenas
    public static void addScreen(String name, FXMLLoader fxmlLoader) throws IOException {
        if (!sceneMap.containsKey(name)) {
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            sceneMap.put(name, scene);
        }
    }

    // Ativa uma tela pelo nome
    public static void activate(String name) throws IOException {
        Scene scene = sceneMap.get(name);
        if (scene == null) {
            throw new IOException("Tela não encontrada: " + name);
        }
        // Obtém o usuário autenticado
        Usuario usuarioAutenticado = Model.getUsuarioAutenticado();
        // Ajeitar para apagar quando sair
        // Verifica se o usuário autenticado é null e atribui valores padrão se necessário
        //String tipoUsuario = (usuarioAutenticado != null) ? String.valueOf(usuarioAutenticado.getTipoUsuario()) : "";
       // String username = (usuarioAutenticado != null) ? usuarioAutenticado.getUsername() : "";
        //stage.setTitle("Sistema de Patrimonio " + tipoUsuario + " " + username);
        // Atualiza a cena com a nova Scene
        stage.setScene(scene);
        stage.setTitle("Sistema de Patrimonio");
        stage.show();

        // Centraliza a tela
        screenCenter(stage);
    }

    // Centralizar a tela
    public static void screenCenter(Stage stage) {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
}