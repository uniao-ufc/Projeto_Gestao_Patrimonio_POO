package br.ufc.sistemapatrimonio.controller;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;

public class ScreenController {
    private static final HashMap<String, FXMLLoader> screenMap = new HashMap<>();
    private static Stage stage;

    // Define o Stage principal
    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }
    // Adiciona uma tela ao mapa de telas
    public static void addScreen(String name, FXMLLoader fxmlLoader) {
        screenMap.put(name, fxmlLoader);
    }
    // Ativa uma tela pelo nome
    public static void activate(String name) throws IOException {
        FXMLLoader loader = screenMap.get(name);
        if (loader == null) {
            throw new IOException("Tela não encontrada: " + name);
        }
        Parent root = loader.load();

        // Atualiza a cena com o novo root
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sistema de Patrimonio");
        stage.show();

        // Centraliza a tela
        screenCenter(stage);
    }
    // Centralizar a tela
    public static void screenCenter(Stage stage){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
}
