package com.businessmanager.repository;

import com.businessmanager.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByStatus(Project.Status status);
    List<Project> findByPriority(Project.Priority priority);
    List<Project> findByClientId(Long clientId);
    List<Project> findByNameContainingIgnoreCase(String name);
    long countByStatus(Project.Status status);
}
