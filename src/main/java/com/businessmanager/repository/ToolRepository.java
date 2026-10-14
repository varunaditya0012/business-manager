package com.businessmanager.repository;

import com.businessmanager.model.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {
    List<Tool> findByCategory(String category);
    List<Tool> findByStatus(Tool.Status status);
    List<Tool> findByOfferedById(Long userId);
    List<Tool> findByNameContainingIgnoreCase(String name);
    long countByStatus(Tool.Status status);
}
