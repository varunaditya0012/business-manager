package com.businessmanager.repository;

import com.businessmanager.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByStatus(Client.Status status);
    List<Client> findByIndustry(String industry);
    List<Client> findByCompanyNameContainingIgnoreCase(String name);
    long countByStatus(Client.Status status);
}
