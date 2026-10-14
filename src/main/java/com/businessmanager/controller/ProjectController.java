package com.businessmanager.controller;

import com.businessmanager.model.Project;
import com.businessmanager.service.ClientService;
import com.businessmanager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private ClientService clientService;
    
    @GetMapping
    public String list(Model model, @RequestParam(required = false) String search) {
        if (search != null && !search.isEmpty()) {
            model.addAttribute("projects", projectService.search(search));
            model.addAttribute("search", search);
        } else {
            model.addAttribute("projects", projectService.findAll());
        }
        return "projects/list";
    }
    
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("statuses", Project.Status.values());
        model.addAttribute("priorities", Project.Priority.values());
        model.addAttribute("clients", clientService.findAll());
        return "projects/form";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Project project = projectService.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        model.addAttribute("project", project);
        model.addAttribute("statuses", Project.Status.values());
        model.addAttribute("priorities", Project.Priority.values());
        model.addAttribute("clients", clientService.findAll());
        return "projects/form";
    }
    
    @PostMapping("/save")
    public String save(@ModelAttribute Project project, RedirectAttributes redirectAttributes) {
        projectService.save(project);
        redirectAttributes.addFlashAttribute("success", "Project saved successfully!");
        return "redirect:/projects";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        projectService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Project deleted successfully!");
        return "redirect:/projects";
    }
    
    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        Project project = projectService.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        model.addAttribute("project", project);
        return "projects/view";
    }
}
