package com.businessmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Company name is required")
    @Column(name = "company_name", nullable = false, length = 100)
    private String companyName;
    
    @Column(name = "contact_person", length = 100)
    private String contactPerson;
    
    @Email
    @Column(length = 100)
    private String email;
    
    @Column(length = 20)
    private String phone;
    
    @Column(columnDefinition = "TEXT")
    private String address;
    
    @Column(length = 50)
    private String industry;
    
    @Enumerated(EnumType.STRING)
    private Status status = Status.PROSPECT;
    
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Project> projects;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Client() {
    }

    public Client(Long id, String companyName, String contactPerson, String email, String phone, String address, String industry, Status status, List<Project> projects, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.companyName = companyName;
        this.contactPerson = contactPerson;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.industry = industry;
        this.status = status != null ? status : Status.PROSPECT;
        this.projects = projects;
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
        ACTIVE, INACTIVE, PROSPECT
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public List<Project> getProjects() { return projects; }
    public void setProjects(List<Project> projects) { this.projects = projects; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public static ClientBuilder builder() {
        return new ClientBuilder();
    }

    public static class ClientBuilder {
        private Long id;
        private String companyName;
        private String contactPerson;
        private String email;
        private String phone;
        private String address;
        private String industry;
        private Status status = Status.PROSPECT;
        private List<Project> projects;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public ClientBuilder id(Long id) { this.id = id; return this; }
        public ClientBuilder companyName(String companyName) { this.companyName = companyName; return this; }
        public ClientBuilder contactPerson(String contactPerson) { this.contactPerson = contactPerson; return this; }
        public ClientBuilder email(String email) { this.email = email; return this; }
        public ClientBuilder phone(String phone) { this.phone = phone; return this; }
        public ClientBuilder address(String address) { this.address = address; return this; }
        public ClientBuilder industry(String industry) { this.industry = industry; return this; }
        public ClientBuilder status(Status status) { this.status = status; return this; }
        public ClientBuilder projects(List<Project> projects) { this.projects = projects; return this; }
        public ClientBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public ClientBuilder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }

        public Client build() {
            return new Client(id, companyName, contactPerson, email, phone, address, industry, status, projects, createdAt, updatedAt);
        }
    }
}
