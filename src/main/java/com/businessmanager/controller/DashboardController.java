package com.businessmanager.controller;

import com.businessmanager.model.*;
import com.businessmanager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    
    @Autowired
    private ToolService toolService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ProjectService projectService;
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Counts
        model.addAttribute("totalTools", toolService.count());
        model.addAttribute("activeTools", toolService.countByStatus(Tool.Status.ACTIVE));
        model.addAttribute("totalEmployees", employeeService.count());
        model.addAttribute("activeEmployees", employeeService.countByStatus(Employee.Status.ACTIVE));
        model.addAttribute("totalClients", clientService.count());
        model.addAttribute("activeClients", clientService.countByStatus(Client.Status.ACTIVE));
        model.addAttribute("totalProjects", projectService.count());
        model.addAttribute("activeProjects", projectService.countByStatus(Project.Status.IN_PROGRESS));
        
        // Recent items
        model.addAttribute("recentTools", toolService.findAll().stream().limit(5).toList());
        model.addAttribute("recentProjects", projectService.findAll().stream().limit(5).toList());
        
        return "dashboard";
    }
}
