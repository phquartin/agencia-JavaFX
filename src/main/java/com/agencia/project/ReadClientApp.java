package com.agencia.project;

import com.agencia.project.model.client.ClientModel;
import com.agencia.project.service.ClientService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReadClientApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(20);
        root.setStyle("-fx-padding: 20; -fx-background-color: #f7f9fc;");

        Label label = new Label("Digite o ID do Cliente:");
        TextField idField = new TextField();
        idField.setPromptText("ID do Cliente");

        Button buscarButton = new Button("Buscar");
        buscarButton.setStyle("-fx-font-size: 16; -fx-padding: 10;");
        TextArea resultadoArea = new TextArea();
        resultadoArea.setEditable(false);
        resultadoArea.setPromptText("Detalhes do Cliente");

        buscarButton.setOnAction(event -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                ClientModel client = ClientService.findById(id);

                resultadoArea.setText("ID: " + client.getId() + "\n" +
                        "Nome: " + client.getName() + "\n" +
                        "Idade: " + client.getAge() + "\n" +
                        "Email: " + client.getEmail() + "\n" +
                        "Documento: " + client.getDocument() + "\n" +
                        "Nacionalidade: " + (client.getNationality() == com.agencia.project.model.client.Nationality.NACIONAL ? "Nacional" : "Estrangeiro"));
            } catch (Exception e) {
                resultadoArea.setText("Erro: " + e.getMessage());
            }
        });

        root.getChildren().

                addAll(label, idField, buscarButton, resultadoArea);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Ler Cliente");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
