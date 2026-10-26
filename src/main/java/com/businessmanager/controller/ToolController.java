package com.businessmanager.controller;

import com.businessmanager.model.Tool;
import com.businessmanager.model.User;
import com.businessmanager.service.ToolService;
import com.businessmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tools")
public class ToolController {
    
    @Autowired
    private ToolService toolService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public String list(Model model, @RequestParam(required = false) String search) {
        if (search != null && !search.isEmpty()) {
            model.addAttribute("tools", toolService.search(search));
            model.addAttribute("search", search);
        } else {
            model.addAttribute("tools", toolService.findAll());
        }
        return "tools/list";
    }
    
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("tool", new Tool());
        model.addAttribute("statuses", Tool.Status.values());
        return "tools/form";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Tool tool = toolService.findById(id)
                .orElseThrow(() -> new RuntimeException("Tool not found"));
        model.addAttribute("tool", tool);
        model.addAttribute("statuses", Tool.Status.values());
        return "tools/form";
    }
    
    @PostMapping("/save")
    public String save(@ModelAttribute Tool tool, Authentication authentication, RedirectAttributes redirectAttributes) {
        if (tool.getId() == null) {
            User user = userService.findByUsername(authentication.getName()).orElse(null);
            tool.setOfferedBy(user);
        } else {
            toolService.findById(tool.getId()).ifPresent(existing -> {
                if (tool.getOfferedBy() == null) {
                    tool.setOfferedBy(existing.getOfferedBy());
                }
            });
        }
        toolService.save(tool);
        redirectAttributes.addFlashAttribute("success", "Tool saved successfully!");
        return "redirect:/tools";
    }
    
    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        toolService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Tool deleted successfully!");
        return "redirect:/tools";
    }
    
    @GetMapping({"/view/{id}", "/{id}"})
    public String view(@PathVariable Long id, Model model) {
        Tool tool = toolService.findById(id)
                .orElseThrow(() -> new RuntimeException("Tool not found"));
        model.addAttribute("tool", tool);
        return "tools/view";
    }
}
