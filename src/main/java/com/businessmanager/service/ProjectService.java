package com.businessmanager.service;

import com.businessmanager.model.Project;
import com.businessmanager.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;
    
    public List<Project> findAll() {
        return projectRepository.findAll();
    }
    
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }
    
    public Project save(Project project) {
        return projectRepository.save(project);
    }
    
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }
    
    public List<Project> findByStatus(Project.Status status) {
        return projectRepository.findByStatus(status);
    }
    
    public List<Project> findByClientId(Long clientId) {
        return projectRepository.findByClientId(clientId);
    }
    
    public List<Project> search(String query) {
        return projectRepository.findByNameContainingIgnoreCase(query);
    }
    
    public long countByStatus(Project.Status status) {
        return projectRepository.countByStatus(status);
    }
    
    public long count() {
        return projectRepository.count();
    }
}
