package com.agencia.project.test;

import com.agencia.project.model.client.ClientModel;
import com.agencia.project.model.client.Nationality;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormularioApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);

        // Campos de texto
        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome");

        TextField emailField = new TextField();
        emailField.setPromptText("E-mail");

        TextField idadeField = new TextField();
        idadeField.setPromptText("Idade");

        ComboBox<String> nacionalidadeCombo = new ComboBox<>();
        nacionalidadeCombo.getItems().addAll("Nacional", "Estrangeiro");
        nacionalidadeCombo.setPromptText("Nacionalidade");

        TextField documentoField = new TextField();
        documentoField.setPromptText("Documento");
        documentoField.setDisable(true); // Desabilitado inicialmente

        // Lógica para habilitar documento apropriado
        nacionalidadeCombo.setOnAction(event -> {
            String nacionalidade = nacionalidadeCombo.getValue();
            if ("Estrangeiro".equals(nacionalidade)) {
                documentoField.setDisable(false);
                documentoField.setPromptText("Passaporte");
            } else if ("Nacional".equals(nacionalidade)) {
                documentoField.setDisable(false);
                documentoField.setPromptText("CPF");
            }
        });

        // Botão de envio
        Button enviarButton = new Button("Enviar");
        enviarButton.setOnAction(event -> {
            try {
                // Validação e obtenção dos dados do formulário
                String nome = nomeField.getText().trim();
                String email = emailField.getText().trim();
                int idade = Integer.parseInt(idadeField.getText().trim());
                String nacionalidadeStr = nacionalidadeCombo.getValue();
                String documento = documentoField.getText().trim();

                if (nome.isEmpty() || email.isEmpty() || nacionalidadeStr == null || documento.isEmpty()) {
                    throw new Exception("Todos os campos devem ser preenchidos!");
                }
                if (idade < 18) {
                    throw new Exception("Idade deve ser maior ou igual a 18!");
                }

                // Convertendo a nacionalidade para o enum
                Nationality nacionalidade = "Nacional".equals(nacionalidadeStr) ? Nationality.NACIONAL : Nationality.ESTRANGEIRO;

                // Criando a instância de ClientModel
                ClientModel client = new ClientModel(nome, idade, email, documento, nacionalidade);

                // Exibindo os dados do cliente no console (ou use para outra funcionalidade)
                System.out.println(client);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Formulário Enviado");
                alert.setHeaderText(null);
                alert.setContentText("Cliente criado com sucesso:\n" + client);
                alert.showAndWait();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        });


        root.getChildren().addAll(nomeField, emailField, idadeField, nacionalidadeCombo, documentoField, enviarButton);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Formulário");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
