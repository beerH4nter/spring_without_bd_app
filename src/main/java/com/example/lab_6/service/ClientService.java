package com.example.lab_6.service;


import com.example.lab_6.entity.Client;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final List<Client> clients = new ArrayList<>();
    private int nextId = 1;

    public List<Client> getAllClients() {
        return clients;
    }

    public Optional<Client> getClientById(int id) {
        return clients.stream().filter(client -> client.getId() == id).findFirst();
    }

    public Client createClient(Client Client) {
        Client.setId(nextId++);
        clients.add(Client);
        return Client;
    }

    public Optional<Client> updateClient(int id, Client updateClient) {
        Optional<Client> clientOptional = getClientById(id);
        clientOptional.ifPresent(client -> {
            client.setName(updateClient.getName());
            client.setEmail(updateClient.getEmail());
            client.setPhoneNumber(updateClient.getPhoneNumber());
        });
        return clientOptional;
    }

    public boolean deleteClient(int id) {
        return clients.removeIf(client -> client.getId() == id);
    }
}
