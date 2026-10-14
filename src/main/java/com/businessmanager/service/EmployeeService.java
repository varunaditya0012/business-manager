package com.businessmanager.service;

import com.businessmanager.model.Employee;
import com.businessmanager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
    
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }
    
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }
    
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
    
    public List<Employee> findByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }
    
    public List<Employee> search(String query) {
        return employeeRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(query, query);
    }
    
    public long countByStatus(Employee.Status status) {
        return employeeRepository.countByStatus(status);
    }
    
    public long count() {
        return employeeRepository.count();
    }
}
