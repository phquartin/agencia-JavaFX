package com.agencia.project.util.initdb;

import com.agencia.project.util.conn.ConnectionFactory;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Log4j2
public final class DataInicialization {

    private static final String[] SQL_COMMANDS = {
            "CREATE TABLE tb_continents (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(18) NOT NULL UNIQUE)",
            "CREATE TABLE tb_countries (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100) NOT NULL UNIQUE, language VARCHAR(50) NULL, id_continent BIGINT, FOREIGN KEY (id_continent) REFERENCES tb_continents(id) ON DELETE CASCADE)",
            "CREATE TABLE tb_destinations (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100) NOT NULL UNIQUE, description TEXT NULL, id_country BIGINT NOT NULL, FOREIGN KEY (id_country) REFERENCES tb_countries(id) ON DELETE CASCADE)",
            "CREATE TABLE tb_vacation_packages (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100) NOT NULL, days INT NOT NULL, price DECIMAL(10,2) NOT NULL, package_type VARCHAR(30) NOT NULL)",
            "CREATE TABLE tb_packages_destinations (id_vacation_package BIGINT, id_destination BIGINT, PRIMARY KEY (id_vacation_package, id_destination), FOREIGN KEY (id_vacation_package) REFERENCES tb_vacation_packages(id) ON DELETE CASCADE, FOREIGN KEY (id_destination) REFERENCES tb_destinations(id) ON DELETE CASCADE)",
            "CREATE TABLE tb_clients (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100) NOT NULL, age INT NOT NULL, email VARCHAR(50) NOT NULL UNIQUE, document VARCHAR(15) NOT NULL UNIQUE, nationality VARCHAR(12) NOT NULL)",
            "CREATE TABLE tb_clients_packages (id BIGINT AUTO_INCREMENT PRIMARY KEY, id_client BIGINT NOT NULL, id_vacation_package BIGINT NOT NULL, start_date DATE NOT NULL, FOREIGN KEY (id_client) REFERENCES tb_clients(id) ON DELETE CASCADE, FOREIGN KEY (id_vacation_package) REFERENCES tb_vacation_packages(id) ON DELETE CASCADE)",
            "CREATE TABLE tb_services (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100) NOT NULL, price DECIMAL(10,2) NOT NULL)",
            "CREATE TABLE tb_purchase (id_client_package BIGINT PRIMARY KEY, id_service BIGINT, FOREIGN KEY (id_client_package) REFERENCES tb_clients_packages(id))"
    };

    public static void init() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            for (String sql : SQL_COMMANDS) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.execute();
                }
            }
        } catch (SQLException e) {
            log.error("Erro ao inicializar banco de dados {}", e.getMessage());
        }
    }

}
