package com.agencia.project;

import com.agencia.project.model.client.ClientModel;
import com.agencia.project.model.client.Nationality;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class FormularioApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(15);
        root.setStyle("-fx-padding: 20; -fx-background-color: #f0f4f8;");

        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome");
        nomeField.setStyle("-fx-font-size: 14;");

        TextField emailField = new TextField();
        emailField.setPromptText("E-mail");
        emailField.setStyle("-fx-font-size: 14;");

        TextField idadeField = new TextField();
        idadeField.setPromptText("Idade");
        idadeField.setStyle("-fx-font-size: 14;");

        ComboBox<String> nacionalidadeCombo = new ComboBox<>();
        nacionalidadeCombo.getItems().addAll("Nacional", "Estrangeiro");
        nacionalidadeCombo.setPromptText("Nacionalidade");
        nacionalidadeCombo.setStyle("-fx-font-size: 14;");

        TextField documentoField = new TextField();
        documentoField.setPromptText("Documento");
        documentoField.setDisable(true);
        documentoField.setStyle("-fx-font-size: 14;");

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

        Button enviarButton = new Button("Enviar");
        enviarButton.setStyle("-fx-font-size: 16; -fx-background-color: #4caf50; -fx-text-fill: white; -fx-padding: 10;");
        enviarButton.setOnAction(event -> {
            try {
                // Validação do nome
                String nome = nomeField.getText().trim();
                if (!nome.matches("^[a-zA-ZÀ-ÿ\\s]+$")) {
                    throw new Exception("O nome deve conter apenas letras.");
                }

                // Validação da idade
                String idadeStr = idadeField.getText().trim();
                if (!idadeStr.matches("^\\d+$")) {
                    throw new Exception("A idade deve conter apenas números.");
                }
                int idade = Integer.parseInt(idadeStr);
                if (idade < 18 || idade > 130) {
                    throw new Exception("A idade deve estar entre 18 e 130.");
                }

                // Validação do e-mail
                String email = emailField.getText().trim();
                if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                    throw new Exception("O e-mail fornecido é inválido.");
                }

                // Validação do documento
                String nacionalidadeStr = nacionalidadeCombo.getValue();
                String documento = documentoField.getText().trim();
                switch (nacionalidadeStr){
                    case "Nacional":
                        if (!documento.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")) {
                            throw new Exception("O CPF fornecido é inválido.");
                        }
                        break;
                    case "Estrangeiro":
                        if (!documento.matches("^[A-Z]{2}\\d{6,9}$")) {
                            throw new Exception("O Passaporte fornecido é inválido.");
                        }
                        break;
                    default:
                        throw new Exception("Nacionalidade não reconhecida.");
                }

                // Dados válidos! Criar instância do cliente
                Nationality nacionalidade = "Nacional".equals(nacionalidadeStr) ? Nationality.NACIONAL : Nationality.ESTRANGEIRO;
                ClientModel client = new ClientModel(nome, idade, email, documento, nacionalidade);

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

        Scene scene = new Scene(root, 450, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        primaryStage.setTitle("Formulário Moderno");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static int getIdade(String email, TextField idadeField) throws Exception {
        if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new Exception("O e-mail fornecido é inválido.");
        }

        String idadeStr = idadeField.getText().trim();
        if (!idadeStr.matches("^\\d+$")) {
            throw new Exception("A idade deve conter apenas números.");
        }
        int idade = Integer.parseInt(idadeStr);
        if (idade < 18 || idade > 130) {
            throw new Exception("A idade deve estar entre 18 e 130.");
        }
        return idade;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
