package com.businessmanager.controller;

import com.businessmanager.model.Client;
import com.businessmanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clients")
public class ClientController {
    
    @Autowired
    private ClientService clientService;
    
    @GetMapping
    public String list(Model model, @RequestParam(required = false) String search) {
        if (search != null && !search.isEmpty()) {
            model.addAttribute("clients", clientService.search(search));
            model.addAttribute("search", search);
        } else {
            model.addAttribute("clients", clientService.findAll());
        }
        return "clients/list";
    }
    
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("statuses", Client.Status.values());
        return "clients/form";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Client client = clientService.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        model.addAttribute("client", client);
        model.addAttribute("statuses", Client.Status.values());
        return "clients/form";
    }
    
    @PostMapping("/save")
    public String save(@ModelAttribute Client client, RedirectAttributes redirectAttributes) {
        clientService.save(client);
        redirectAttributes.addFlashAttribute("success", "Client saved successfully!");
        return "redirect:/clients";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        clientService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Client deleted successfully!");
        return "redirect:/clients";
    }
    
    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        Client client = clientService.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        model.addAttribute("client", client);
        return "clients/view";
    }
}
