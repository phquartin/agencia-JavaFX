package com.agencia.project.util.initdb;

import com.agencia.project.util.conn.ConnectionFactory;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Log4j2
public final class DataInicialization {

    private static final String SQL = """
            CREATE TABLE tb_continent (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(100) NOT NULL UNIQUE
            );
            CREATE TABLE tb_country (
                id INT AUTO_INCREMENT PRIMARY KEY, 
                name VARCHAR(100) NOT NULL UNIQUE, 
                language VARCHAR(255) NULL, 
                id_continent INT, 
                FOREIGN KEY (id_continent) REFERENCES tb_continentes(id) ON DELETE CASCADE
            );
            CREATE TABLE tb_destinations (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(100) NOT NULL UNIQUE,
                description TEXT NULL,
                id_country INT, 
                FOREIGN KEY (id_country) REFERENCES tb_country(id) ON DELETE CASCADE
            );
            CREATE TABLE tb_vacation_packages (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(100) NOT NULL, 
                days INT NOT NULL, 
                price DECIMAL(10,2) NOT NULL,
                package_type VARCHAR(30) NOT NULL
            );
            CREATE TABLE tb_packages_destinations (
                id_vacation_package INT, 
                id_destination INT, 
                PRIMARY KEY (id_vacation_package, id_destination),
                FOREIGN KEY (id_vacation_package) REFERENCES tb_vacation_packages(id) ON DELETE CASCADE,
                FOREIGN KEY (id_destination) REFERENCES tb_destinations(id) ON DELETE CASCADE
            );
            CREATE TABLE tb_clients (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                age INT NOT NULL, 
                email VARCHAR(50) NOT NULL, 
                document VARCHAR(15) NOT NULL,
                nationality VARCHAR(12) NOT NULL
            );
            CREATE TABLE tb_clients_packages (
                id INT AUTO_INCREMENT PRIMARY KEY,
                id_client INT NOT NULL,
                id_vacation_package INT NOT NULL, 
                start_date DATE NOT NULL,
                FOREIGN KEY (id_client) REFERENCES tb_clients(id) ON DELETE CASCADE,
                FOREIGN KEY (id_vacation_package) REFERENCES tb_vacation_packages(id) ON DELETE CASCADE
            );
            CREATE TABLE tb_services (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(100) NOT NULL, 
                price DECIMAL(10,2) NOT NULL 
            );
            CREATE TABLE tb_purchase (
                id_client_package INT PRIMARY KEY ,
                id_service INT, 
                FOREIGN KEY (id_client_package) REFERENCES tb_clients_packages(id)
            );
            """;

    public static void init() {
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL)
        ){

            stmt.execute();

        } catch (SQLException e) {
            log.error("Erro ao inicializar banco de dados {}", e.getMessage());
        }
    }

}
