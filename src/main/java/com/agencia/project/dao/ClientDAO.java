package com.agencia.project.dao;

import com.agencia.project.model.client.ClientModel;
import com.agencia.project.model.client.Nationality;

import java.sql.*;
import java.util.Optional;

import static com.agencia.project.util.conn.ConnectionFactory.getConnection;

// TODO: LOG4J + Lombok

public final class ClientDAO {

    // Criação de um cliente, retorna o ID.
    public static void createClient(ClientModel client) {
        String sql = "INSERT INTO tb_clients (name, age, email, document, nationality) VALUES (?,?,?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            setClient(client, stmt);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("A criação do cliente falhou " + e.getMessage());
        }
    }

    // Recuperação (SELECT) de um cliente pelo ID.
    public static Optional<ClientModel> readClientById(long id) {
        String sql = "SELECT * FROM tb_clients WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                String nationality = rs.getString("nationality");
                String document = rs.getString("document");

                rs.close();
                return Optional.of(ClientModel.builder()
                        .id(id)
                        .name(name)
                        .age(age)
                        .email(email)
                        .document(document)
                        .nationality(nationality.equals("Nacional") ? Nationality.NACIONAL : Nationality.ESTRANGEIRO)
                        .build());
            }
        } catch (SQLException e) {
            System.out.println("A consulta do cliente falhou id " + id + " | " + e.getMessage());
            return Optional.empty();
        }
        return Optional.empty();
    }

    // Atualização (UPDATE) dos dados de um cliente.
    public static void updateClient(ClientModel client) {
        String sql = "UPDATE tb_clients SET name = ?, age = ?, email = ?, document = ?, nationality = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            setClient(client, stmt);
            stmt.setLong(6, client.getId());

            stmt.executeUpdate();
        } catch (SQLException e){
            System.out.println("Erro ao atualizar cliente " + e.getMessage());
        }
    }

    // Exclusão (DELETE) de um cliente pelo ID.
    public static void deleteClient(long id) {
        String sql = "DELETE FROM tb_clients WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente " + e.getMessage());
        }
    }

    // Auxiliar os metodos update e crate
    private static void setClient(ClientModel client, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, client.getName());
        stmt.setInt(2, client.getAge());
        stmt.setString(3, client.getEmail());
        stmt.setString(4, client.getDocument());
        stmt.setString(5, client.getNationality() == Nationality.NACIONAL ? "Nacional" : "Estrangeiro");
    }

}
