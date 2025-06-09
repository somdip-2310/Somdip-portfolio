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
        model.addAttribute("title", "Somdip Roy - Technical Architect | AWS Certified");
        model.addAttribute("description", "Technical Architect with 12+ years experience in Java, Spring, and AWS");
        model.addAttribute("currentPage", "home");
        
        // Add key metrics for homepage
        model.addAttribute("yearsExperience", 12);
        model.addAttribute("applicationsManaged", 22);
        model.addAttribute("teamSize", 15);
        model.addAttribute("costSavings", "$300K");
        
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
        model.addAttribute("title", "About Somdip Roy - Technical Architect");
        model.addAttribute("description", "Learn more about my background, skills, and professional journey");
        model.addAttribute("currentPage", "about");
        model.addAttribute("skills", experienceService.getTechnicalSkills());
        model.addAttribute("certifications", experienceService.getCertifications());
        
        return "about";
    }
}