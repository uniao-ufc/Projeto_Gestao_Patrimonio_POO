package br.ufc.sistemapatrimonio.controller;

import br.ufc.sistemapatrimonio.HelloApplication;
import javafx.fxml.FXMLLoader;

public class InitScenesController {
    public static void initScenes(){
        // Carregar telas
        // Login
        FXMLLoader telaLoginLoader = new FXMLLoader(HelloApplication.class.getResource("telaLoginCadastro.fxml"));
        ScreenController.addScreen("telaLogin", telaLoginLoader);
        // Tela do requisitante
        FXMLLoader telaRequisitanteLoader = new FXMLLoader(HelloApplication.class.getResource("telaRequisitante.fxml"));
        ScreenController.addScreen("telaRequisitante", telaRequisitanteLoader);
        //
        FXMLLoader telaAdminBensLoader = new FXMLLoader(HelloApplication.class.getResource("telaAdminBens.fxml"));
        ScreenController.addScreen("telaAdminBens", telaAdminBensLoader);
        //
        FXMLLoader telaAdminPatrimoniosLoader = new FXMLLoader(HelloApplication.class.getResource("telaAdminPatrimonios.fxml"));
        ScreenController.addScreen("telaAdminPatrimonios", telaAdminPatrimoniosLoader);
        //
        FXMLLoader telaAdminManutencoesLoader = new FXMLLoader(HelloApplication.class.getResource("telaAdminManutencoes.fxml"));
        ScreenController.addScreen("telaAdminManutecoes", telaAdminManutencoesLoader);
    }
}
