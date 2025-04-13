package com.agencia.project.controller.client;

import com.agencia.project.model.client.ClientModel;
import com.agencia.project.model.client.Nationality;
import com.agencia.project.service.ClientService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UpdateClientApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(20);
        root.setStyle("-fx-padding: 20; -fx-background-color: #f7f9fc;");

        Label label = new Label("Digite o ID do Cliente:");
        TextField idField = new TextField();
        idField.setPromptText("ID do Cliente");

        Button buscarButton = new Button("Buscar");
        buscarButton.setStyle("-fx-font-size: 16; -fx-padding: 10;");

        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome");

        TextField idadeField = new TextField();
        idadeField.setPromptText("Idade");

        TextField emailField = new TextField();
        emailField.setPromptText("E-mail");

        ComboBox<String> nacionalidadeCombo = new ComboBox<>();
        nacionalidadeCombo.getItems().addAll("Nacional", "Estrangeiro");
        nacionalidadeCombo.setPromptText("Nacionalidade");

        TextField documentoField = new TextField();
        documentoField.setPromptText("Documento");

        Button atualizarButton = new Button("Atualizar");
        atualizarButton.setStyle("-fx-font-size: 16; -fx-padding: 10;");

        buscarButton.setOnAction(event -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                ClientModel client = ClientService.findById(id);

                nomeField.setText(client.getName());
                idadeField.setText(String.valueOf(client.getAge()));
                emailField.setText(client.getEmail());
                nacionalidadeCombo.setValue(client.getNationality() == Nationality.NACIONAL ? "Nacional" : "Estrangeiro");
                documentoField.setText(client.getDocument());

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Erro: " + e.getMessage());
                alert.showAndWait();
            }
        });
            //TODO: VALIDATIONS NOS APPS
        atualizarButton.setOnAction(event -> {
            try {
                Long id = Long.parseLong(idField.getText().trim());
                String nome = nomeField.getText().trim();
                String email = emailField.getText().trim();
                int idade = Integer.parseInt(idadeField.getText().trim());
                String nacionalidadeStr = nacionalidadeCombo.getValue();
                String documento = documentoField.getText().trim();
                Nationality nationality = nacionalidadeStr.equals("Nacional") ? Nationality.NACIONAL : Nationality.ESTRANGEIRO;

                ClientModel client = ClientModel.builder()
                        .id(id)
                        .name(nome)
                        .age(idade)
                        .email(email)
                        .document(documento)
                        .nationality(nationality)
                        .build();
                ClientService.update(client);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText(null);
                alert.setContentText("Cliente atualizado com sucesso.");
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Erro: " + e.getMessage());
                alert.showAndWait();
            }
        });

        root.getChildren().addAll(label, idField, buscarButton, nomeField, idadeField, emailField, nacionalidadeCombo, documentoField, atualizarButton);

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Atualizar Cliente");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
