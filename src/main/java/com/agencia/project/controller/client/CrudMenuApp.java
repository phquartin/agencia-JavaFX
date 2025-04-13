package com.agencia.project.controller.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CrudMenuApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(20);
        root.setStyle("-fx-padding: 20; -fx-background-color: #f7f9fc;");

        // Botão para criar um cliente
        Button criarClienteButton = new Button("Criar Cliente");
        criarClienteButton.setStyle("-fx-font-size: 16; -fx-padding: 10;");
        criarClienteButton.setOnAction(event -> {
            FormularioApp formularioApp = new FormularioApp();
            formularioApp.start(new Stage());
        });

        // Botão para ler um cliente
        Button lerClienteButton = new Button("Ler Cliente");
        lerClienteButton.setStyle("-fx-font-size: 16; -fx-padding: 10;");
        lerClienteButton.setOnAction(event -> {
            ReadClientApp readClientApp = new ReadClientApp();
            readClientApp.start(new Stage());
        });

        // Botão para atualizar um cliente
        Button atualizarClienteButton = new Button("Atualizar Cliente");
        atualizarClienteButton.setStyle("-fx-font-size: 16; -fx-padding: 10;");
        atualizarClienteButton.setOnAction(event -> {
            UpdateClientApp updateClientApp = new UpdateClientApp();
            updateClientApp.start(new Stage());
        });

        // Botão para deletar um cliente
        Button deletarClienteButton = new Button("Deletar Cliente");
        deletarClienteButton.setStyle("-fx-font-size: 16; -fx-padding: 10;");
        deletarClienteButton.setOnAction(event -> {
            DeleteClientApp deleteClientApp = new DeleteClientApp();
            deleteClientApp.start(new Stage());
        });

        root.getChildren().addAll(criarClienteButton, lerClienteButton, atualizarClienteButton, deletarClienteButton);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Menu de CRUD");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
