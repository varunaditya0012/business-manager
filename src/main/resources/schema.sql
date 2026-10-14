-- ===================================================
-- Business Tool Manager Database Schema & Initial Data
-- Target Database: MySQL 8.0+
-- ===================================================

CREATE DATABASE IF NOT EXISTS business_manager_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE business_manager_db;

-- ---------------------------------------------------
-- 1. Table: users
-- ---------------------------------------------------
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100),
    role ENUM('ADMIN', 'MANAGER', 'USER') DEFAULT 'USER',
    enabled BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------
-- 2. Table: tools
-- ---------------------------------------------------
CREATE TABLE IF NOT EXISTS tools (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    category VARCHAR(50),
    status ENUM('ACTIVE', 'INACTIVE', 'MAINTENANCE') DEFAULT 'ACTIVE',
    price DECIMAL(10, 2),
    offered_by BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_tools_offered_by FOREIGN KEY (offered_by) REFERENCES users (id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------
-- 3. Table: employees
-- ---------------------------------------------------
CREATE TABLE IF NOT EXISTS employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20),
    department VARCHAR(50),
    position VARCHAR(50),
    salary DECIMAL(10, 2),
    hire_date DATE,
    status ENUM('ACTIVE', 'ON_LEAVE', 'TERMINATED') DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------
-- 4. Table: clients
-- ---------------------------------------------------
CREATE TABLE IF NOT EXISTS clients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    company_name VARCHAR(100) NOT NULL,
    contact_person VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20),
    address TEXT,
    industry VARCHAR(50),
    status ENUM('ACTIVE', 'INACTIVE', 'PROSPECT') DEFAULT 'PROSPECT',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ---------------------------------------------------
-- 5. Table: projects
-- ---------------------------------------------------
CREATE TABLE IF NOT EXISTS projects (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    client_id BIGINT,
    status ENUM('PLANNING', 'IN_PROGRESS', 'ON_HOLD', 'COMPLETED', 'CANCELLED') DEFAULT 'PLANNING',
    priority ENUM('LOW', 'MEDIUM', 'HIGH', 'CRITICAL') DEFAULT 'MEDIUM',
    start_date DATE,
    end_date DATE,
    budget DECIMAL(12, 2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_projects_client FOREIGN KEY (client_id) REFERENCES clients (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===================================================
-- Sample Data Seeds
-- Default Password for sample users: 'admin123'
-- BCrypt: $2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG
-- ===================================================

-- Sample Users
INSERT INTO users (id, username, email, password, full_name, role, enabled, created_at, updated_at)
VALUES 
    (1, 'admin', 'admin@businessmanager.com', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'System Administrator', 'ADMIN', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'manager', 'manager@businessmanager.com', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'Operations Manager', 'MANAGER', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'johndoe', 'john.doe@businessmanager.com', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'John Doe', 'USER', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON DUPLICATE KEY UPDATE username=VALUES(username);

-- Sample Tools
INSERT INTO tools (id, name, description, category, status, price, offered_by, created_at, updated_at)
VALUES
    (1, 'Slack Enterprise Grid', 'Enterprise team collaboration, messaging, and integrations platform.', 'Communication', 'ACTIVE', 12.50, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'Jira Software Premium', 'Agile project management, sprint tracking, and software issue tracking.', 'Project Management', 'ACTIVE', 15.00, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'Figma Organization', 'Cloud-based vector graphics, UI/UX design, and interactive prototyping tool.', 'Design', 'ACTIVE', 45.00, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 'GitHub Enterprise Cloud', 'Enterprise source code hosting, automated CI/CD workflows, and security reviews.', 'Development', 'ACTIVE', 21.00, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, 'Datadog Cloud Monitoring', 'Application performance monitoring, log analytics, and infrastructure health.', 'DevOps', 'MAINTENANCE', 35.00, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (6, 'Zoom One Business', 'HD video meetings, conference rooms, team chat, and whiteboard solution.', 'Communication', 'INACTIVE', 19.99, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON DUPLICATE KEY UPDATE name=VALUES(name);

-- Sample Employees
INSERT INTO employees (id, first_name, last_name, email, phone, department, position, salary, hire_date, status, created_at, updated_at)
VALUES
    (1, 'Alice', 'Smith', 'alice.smith@businessmanager.com', '+1-555-0101', 'Engineering', 'Lead Software Engineer', 125000.00, '2022-03-15', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'Bob', 'Johnson', 'bob.johnson@businessmanager.com', '+1-555-0102', 'Marketing', 'Digital Marketing Director', 98000.00, '2021-08-01', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'Carol', 'Williams', 'carol.williams@businessmanager.com', '+1-555-0103', 'Human Resources', 'HR Specialist', 85000.00, '2020-01-10', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 'David', 'Brown', 'david.brown@businessmanager.com', '+1-555-0104', 'Finance', 'Senior Financial Analyst', 92000.00, '2023-06-20', 'ON_LEAVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, 'Eva', 'Martinez', 'eva.martinez@businessmanager.com', '+1-555-0105', 'Engineering', 'DevOps Specialist', 115000.00, '2023-11-01', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON DUPLICATE KEY UPDATE email=VALUES(email);

-- Sample Clients
INSERT INTO clients (id, company_name, contact_person, email, phone, address, industry, status, created_at, updated_at)
VALUES
    (1, 'Acme Corporation', 'Sarah Connor', 'sarah.connor@acmecorp.com', '+1-555-1001', '100 Cyberdyne Way, Silicon Valley, CA 94025', 'Technology', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'TechNova Solutions', 'Marcus Vance', 'm.vance@technova.io', '+1-555-1002', '450 Innovation Blvd, Austin, TX 78701', 'Software & AI', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'Global Logistics Inc', 'Helen Drake', 'hdrake@globallogistics.com', '+1-555-1003', '78 Harbor Drive, Chicago, IL 60601', 'Logistics & Supply Chain', 'PROSPECT', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 'Apex Financial Group', 'Robert Sterling', 'r.sterling@apexfinance.com', '+1-555-1004', '12 Wall St, New York, NY 10005', 'Banking & Finance', 'INACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON DUPLICATE KEY UPDATE company_name=VALUES(company_name);

-- Sample Projects
INSERT INTO projects (id, name, description, client_id, status, priority, start_date, end_date, budget, created_at, updated_at)
VALUES
    (1, 'Cloud Migration & Modernization', 'Migrate on-premise infrastructure to AWS cloud microservices architecture with high availability.', 1, 'IN_PROGRESS', 'HIGH', '2024-01-15', '2024-09-30', 250000.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'AI Analytics Dashboard', 'Custom enterprise business intelligence platform with predictive analytics and real-time reporting.', 2, 'IN_PROGRESS', 'CRITICAL', '2024-02-01', '2024-11-15', 180000.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'Fleet Logistics Mobile App', 'Real-time GPS tracking and supply-chain automated inventory mobile app for iOS and Android.', 3, 'PLANNING', 'MEDIUM', '2024-06-01', '2024-12-31', 95000.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 'FinTech Security & Audit Compliance', 'SOC2 Type II and ISO 27001 readiness review, vulnerability patching, and compliance verification.', 4, 'COMPLETED', 'HIGH', '2023-09-01', '2024-02-28', 65000.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON DUPLICATE KEY UPDATE name=VALUES(name);
