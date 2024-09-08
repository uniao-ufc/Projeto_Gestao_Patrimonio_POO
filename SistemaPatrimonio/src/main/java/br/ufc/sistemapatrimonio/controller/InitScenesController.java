package br.ufc.sistemapatrimonio.controller;

import br.ufc.sistemapatrimonio.HelloApplication;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class InitScenesController {
    public static void initScenes() throws IOException {
        // Carregar telas
        // Login
        FXMLLoader telaLoginLoader = new FXMLLoader(HelloApplication.class.getResource("telaLoginCadastro.fxml"));
        ScreenController.addScreen("telaLogin", telaLoginLoader);
        // Tela do requisitante
        FXMLLoader telaRequisitanteRequisicoesLoader = new FXMLLoader(HelloApplication.class.getResource("telaRequisitanteRequisicoes.fxml"));
        ScreenController.addScreen("telaRequisitanteRequisicoes", telaRequisitanteRequisicoesLoader);
        //
        FXMLLoader telaRequisitanteManutencoesLoader = new FXMLLoader(HelloApplication.class.getResource("telaRequisitanteManutencoes.fxml"));
        ScreenController.addScreen("telaRequisitanteManutencoes", telaRequisitanteManutencoesLoader);
        //
        FXMLLoader telaAdminRequisicoesLoader = new FXMLLoader(HelloApplication.class.getResource("telaAdminRequisicoes.fxml"));
        ScreenController.addScreen("telaAdminRequisicoes", telaAdminRequisicoesLoader);
        //
        FXMLLoader telaAdminManutencoesLoader = new FXMLLoader(HelloApplication.class.getResource("telaAdminManutencoes.fxml"));
        ScreenController.addScreen("telaAdminManutencoes", telaAdminManutencoesLoader);
        //
        FXMLLoader telaAdminBensLoader = new FXMLLoader(HelloApplication.class.getResource("telaAdminBens.fxml"));
        ScreenController.addScreen("telaAdminBens", telaAdminBensLoader);
        //
        FXMLLoader telaAdminPatrimoniosLoader = new FXMLLoader(HelloApplication.class.getResource("telaAdminPatrimonios.fxml"));
        ScreenController.addScreen("telaAdminPatrimonios", telaAdminPatrimoniosLoader);
        //
        FXMLLoader telaAdminHistoricoLoader = new FXMLLoader(HelloApplication.class.getResource("telaAdminHistorico.fxml"));
        ScreenController.addScreen("telaAdminHistorico", telaAdminHistoricoLoader);
    }
}
