package com.agencia.project.service;

import com.agencia.project.dao.ClientDAO;
import com.agencia.project.model.client.ClientModel;

import java.util.Optional;

public final class ClientService {

    public static void save(ClientModel client) {
        ClientDAO.createClient(client);
    }
    public static ClientModel findById(int id) {
        Optional<ClientModel> clientModel = ClientDAO.readClientById(id);
        return clientModel.orElseThrow(() -> new NullPointerException("Cliente nao encontrado"));
    }
    public static void update(ClientModel client) {
        ClientDAO.updateClient(client);
    }
    public static void delete(int id) {
        ClientDAO.deleteClient(id);
    }
}
