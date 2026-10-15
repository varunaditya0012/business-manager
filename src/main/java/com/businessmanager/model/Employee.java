package com.businessmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "First name is required")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    
    @Email(message = "Valid email is required")
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    
    @Column(length = 20)
    private String phone;
    
    @Column(length = 50)
    private String department;
    
    @Column(length = 50)
    private String position;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal salary;
    
    @Column(name = "hire_date")
    private LocalDate hireDate;
    
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Employee() {
    }

    public Employee(Long id, String firstName, String lastName, String email, String phone, String department, String position, BigDecimal salary, LocalDate hireDate, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.position = position;
        this.salary = salary;
        this.hireDate = hireDate;
        this.status = status != null ? status : Status.ACTIVE;
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
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public enum Status {
        ACTIVE, ON_LEAVE, TERMINATED
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }

    public LocalDate getHireDate() { return hireDate; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public static EmployeeBuilder builder() {
        return new EmployeeBuilder();
    }

    public static class EmployeeBuilder {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String department;
        private String position;
        private BigDecimal salary;
        private LocalDate hireDate;
        private Status status = Status.ACTIVE;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public EmployeeBuilder id(Long id) { this.id = id; return this; }
        public EmployeeBuilder firstName(String firstName) { this.firstName = firstName; return this; }
        public EmployeeBuilder lastName(String lastName) { this.lastName = lastName; return this; }
        public EmployeeBuilder email(String email) { this.email = email; return this; }
        public EmployeeBuilder phone(String phone) { this.phone = phone; return this; }
        public EmployeeBuilder department(String department) { this.department = department; return this; }
        public EmployeeBuilder position(String position) { this.position = position; return this; }
        public EmployeeBuilder salary(BigDecimal salary) { this.salary = salary; return this; }
        public EmployeeBuilder hireDate(LocalDate hireDate) { this.hireDate = hireDate; return this; }
        public EmployeeBuilder status(Status status) { this.status = status; return this; }
        public EmployeeBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public EmployeeBuilder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }

        public Employee build() {
            return new Employee(id, firstName, lastName, email, phone, department, position, salary, hireDate, status, createdAt, updatedAt);
        }
    }
}
