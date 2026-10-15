package com.businessmanager.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tools")
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(length = 50)
    private String category;
    
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offered_by")
    private User offeredBy;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Tool() {
    }

    public Tool(Long id, String name, String description, String category, Status status, BigDecimal price, User offeredBy, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.status = status != null ? status : Status.ACTIVE;
        this.price = price;
        this.offeredBy = offeredBy;
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
        ACTIVE, INACTIVE, MAINTENANCE
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public User getOfferedBy() { return offeredBy; }
    public void setOfferedBy(User offeredBy) { this.offeredBy = offeredBy; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public static ToolBuilder builder() {
        return new ToolBuilder();
    }

    public static class ToolBuilder {
        private Long id;
        private String name;
        private String description;
        private String category;
        private Status status = Status.ACTIVE;
        private BigDecimal price;
        private User offeredBy;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public ToolBuilder id(Long id) { this.id = id; return this; }
        public ToolBuilder name(String name) { this.name = name; return this; }
        public ToolBuilder description(String description) { this.description = description; return this; }
        public ToolBuilder category(String category) { this.category = category; return this; }
        public ToolBuilder status(Status status) { this.status = status; return this; }
        public ToolBuilder price(BigDecimal price) { this.price = price; return this; }
        public ToolBuilder offeredBy(User offeredBy) { this.offeredBy = offeredBy; return this; }
        public ToolBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public ToolBuilder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }

        public Tool build() {
            return new Tool(id, name, description, category, status, price, offeredBy, createdAt, updatedAt);
        }
    }
}
