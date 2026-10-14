package com.businessmanager.service;

import com.businessmanager.model.Client;
import com.businessmanager.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;
    
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
    
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }
    
    public Client save(Client client) {
        return clientRepository.save(client);
    }
    
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
    
    public List<Client> findByStatus(Client.Status status) {
        return clientRepository.findByStatus(status);
    }
    
    public List<Client> search(String query) {
        return clientRepository.findByCompanyNameContainingIgnoreCase(query);
    }
    
    public long countByStatus(Client.Status status) {
        return clientRepository.countByStatus(status);
    }
    
    public long count() {
        return clientRepository.count();
    }
}
