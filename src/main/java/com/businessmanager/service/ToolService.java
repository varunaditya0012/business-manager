package com.businessmanager.service;

import com.businessmanager.model.Tool;
import com.businessmanager.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ToolService {
    
    @Autowired
    private ToolRepository toolRepository;
    
    public List<Tool> findAll() {
        return toolRepository.findAll();
    }
    
    public Optional<Tool> findById(Long id) {
        return toolRepository.findById(id);
    }
    
    public Tool save(Tool tool) {
        return toolRepository.save(tool);
    }
    
    public void deleteById(Long id) {
        toolRepository.deleteById(id);
    }
    
    public List<Tool> findByCategory(String category) {
        return toolRepository.findByCategory(category);
    }
    
    public List<Tool> findByStatus(Tool.Status status) {
        return toolRepository.findByStatus(status);
    }
    
    public List<Tool> search(String query) {
        return toolRepository.findByNameContainingIgnoreCase(query);
    }
    
    public long countByStatus(Tool.Status status) {
        return toolRepository.countByStatus(status);
    }
    
    public long count() {
        return toolRepository.count();
    }
}
