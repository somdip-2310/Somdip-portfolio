package com.somdiproy.portfolio.controller;

import com.somdiproy.portfolio.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @Autowired
    private ExperienceService experienceService;
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Somdip Roy - Senior Technical Architect | Java Spring AWS Expert | 13+ Years Experience");
        model.addAttribute("description", "Somdip Roy - Senior Technical Architect with 13+ years of enterprise Java, Spring Boot, AWS cloud solutions, and AI/ML expertise. Leading teams, delivering scalable applications with proven results.");
        model.addAttribute("currentPage", "home");
        
        // Updated key metrics for homepage - 13 years experience, removed cost savings
        model.addAttribute("yearsExperience", "13");
        model.addAttribute("applicationsManaged", "22");
        model.addAttribute("teamSize", "15");
        // Removed costSavings attribute
        
        // Featured skills for homepage
        model.addAttribute("featuredSkills", experienceService.getTechnicalSkills()
                .stream()
                .filter(skill -> skill.getProficiency() >= 85)
                .limit(6)
                .toList());
        
        return "index";
    }
    
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About Somdip Roy - Senior Technical Architect | 13+ Years Java Spring AWS Expert");
        model.addAttribute("description", "Learn about Somdip Roy, Senior Technical Architect with 13+ years of enterprise Java development, AWS cloud solutions, and AI/ML expertise. Currently leading development teams and delivering scalable applications.");
        model.addAttribute("currentPage", "about");
        
        // Updated experience reference
        model.addAttribute("yearsExperience", "13");
        model.addAttribute("applicationsManaged", "22");
        model.addAttribute("teamSize", "15");
        
        model.addAttribute("skills", experienceService.getTechnicalSkills());
        model.addAttribute("certifications", experienceService.getCertifications());
        
        return "about";
    }
}