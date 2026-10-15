package com.businessmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Project name is required")
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;
    
    @Enumerated(EnumType.STRING)
    private Status status = Status.PLANNING;
    
    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.MEDIUM;
    
    @Column(name = "start_date")
    private LocalDate startDate;
    
    @Column(name = "end_date")
    private LocalDate endDate;
    
    @Column(precision = 12, scale = 2)
    private BigDecimal budget;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Project() {
    }

    public Project(Long id, String name, String description, Client client, Status status, Priority priority, LocalDate startDate, LocalDate endDate, BigDecimal budget, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.client = client;
        this.status = status != null ? status : Status.PLANNING;
        this.priority = priority != null ? priority : Priority.MEDIUM;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    public enum Status {
        PLANNING, IN_PROGRESS, ON_HOLD, COMPLETED, CANCELLED
    }
    
    public enum Priority {
        LOW, MEDIUM, HIGH, CRITICAL
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public BigDecimal getBudget() { return budget; }
    public void setBudget(BigDecimal budget) { this.budget = budget; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public static ProjectBuilder builder() {
        return new ProjectBuilder();
    }

    public static class ProjectBuilder {
        private Long id;
        private String name;
        private String description;
        private Client client;
        private Status status = Status.PLANNING;
        private Priority priority = Priority.MEDIUM;
        private LocalDate startDate;
        private LocalDate endDate;
        private BigDecimal budget;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public ProjectBuilder id(Long id) { this.id = id; return this; }
        public ProjectBuilder name(String name) { this.name = name; return this; }
        public ProjectBuilder description(String description) { this.description = description; return this; }
        public ProjectBuilder client(Client client) { this.client = client; return this; }
        public ProjectBuilder status(Status status) { this.status = status; return this; }
        public ProjectBuilder priority(Priority priority) { this.priority = priority; return this; }
        public ProjectBuilder startDate(LocalDate startDate) { this.startDate = startDate; return this; }
        public ProjectBuilder endDate(LocalDate endDate) { this.endDate = endDate; return this; }
        public ProjectBuilder budget(BigDecimal budget) { this.budget = budget; return this; }
        public ProjectBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public ProjectBuilder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }

        public Project build() {
            return new Project(id, name, description, client, status, priority, startDate, endDate, budget, createdAt, updatedAt);
        }
    }
}
