package com.agencia.project.util.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // Configura os parâmetros de conexão ao banco.
    private static final String URL = "jdbc:mysql://localhost:3309/agencia_data";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // Método para obter uma conexão com o banco de dados.
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
