package com.somdiproy.portfolio.controller;

import com.somdiproy.portfolio.model.ContactForm;
import com.somdiproy.portfolio.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/contact")
public class ContactController {

    @Autowired(required = false) // Optional for local testing
    private EmailService emailService;

    @GetMapping
    public String contact(Model model) {
        model.addAttribute("title", "Contact Somdip Roy - Senior Technical Architect | Get in Touch");
        model.addAttribute("description", "Contact Somdip Roy, Senior Technical Architect with 13+ years experience. Discuss your Java, Spring Boot, AWS cloud, and AI/ML project requirements. Based in Mumbai, India.");
        model.addAttribute("currentPage", "contact");
        model.addAttribute("contactForm", new ContactForm());
        
        // Updated contact information - removed phone, updated LinkedIn
        model.addAttribute("email", "somdiproy.roy@gmail.com");
        // Removed phone number
        model.addAttribute("location", "Mumbai, India");
        model.addAttribute("linkedin", "https://www.linkedin.com/in/somdip-roy-b8004b111/");
        
        return "contact/index";
    }

    @PostMapping
    public String submitContact(@Valid ContactForm contactForm, 
                              BindingResult bindingResult, 
                              Model model, 
                              RedirectAttributes redirectAttributes) {
        
        // If validation errors, return to form
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Contact Somdip Roy - Senior Technical Architect | Get in Touch");
            model.addAttribute("description", "Contact Somdip Roy, Senior Technical Architect with 13+ years experience. Discuss your Java, Spring Boot, AWS cloud, and AI/ML project requirements. Based in Mumbai, India.");
            model.addAttribute("currentPage", "contact");
            
            // Re-add contact information for the form - updated
            model.addAttribute("email", "somdiproy.roy@gmail.com");
            model.addAttribute("location", "Mumbai, India");
            model.addAttribute("linkedin", "https://www.linkedin.com/in/somdip-roy-b8004b111/");
            
            return "contact/index";
        }

        try {
            // For local testing - log the contact form submission
            logContactSubmission(contactForm);
            
            // Send email if service is available
            if (emailService != null) {
                emailService.sendContactEmail(contactForm);
                redirectAttributes.addFlashAttribute("successMessage", 
                    "Thank you for your message, " + contactForm.getName() + "! I'll get back to you within 24 hours.");
            } else {
                // Local testing mode
                redirectAttributes.addFlashAttribute("successMessage", 
                    "Thank you for your message, " + contactForm.getName() + "! (Local testing mode - email service disabled)");
            }
            
            return "redirect:/contact";
            
        } catch (Exception e) {
            // Log the error
            System.err.println("Error processing contact form: " + e.getMessage());
            e.printStackTrace();
            
            model.addAttribute("errorMessage", 
                "Sorry, there was an error sending your message. Please try again or email me directly at somdiproy.roy@gmail.com");
            model.addAttribute("title", "Contact Somdip Roy - Senior Technical Architect | Get in Touch");
            model.addAttribute("description", "Contact Somdip Roy, Senior Technical Architect with 13+ years experience. Discuss your Java, Spring Boot, AWS cloud, and AI/ML project requirements. Based in Mumbai, India.");
            model.addAttribute("currentPage", "contact");
            
            // Re-add contact information - updated
            model.addAttribute("email", "somdiproy.roy@gmail.com");
            model.addAttribute("location", "Mumbai, India");
            model.addAttribute("linkedin", "https://www.linkedin.com/in/somdip-roy-b8004b111/");
            
            return "contact/index";
        }
    }
    
    /**
     * Log contact form submission for debugging and local testing
     * Updated to remove fields that are no longer in the form
     */
    private void logContactSubmission(ContactForm contactForm) {
        System.out.println("=== CONTACT FORM SUBMISSION ===");
        System.out.println("Name: " + contactForm.getName());
        System.out.println("Email: " + contactForm.getEmail());
        System.out.println("Company: " + contactForm.getCompany());
        // Removed phone, projectType, timeline, budget logging
        System.out.println("Subject: " + contactForm.getSubject());
        System.out.println("Message: " + contactForm.getMessage());
        System.out.println("===============================");
    }
}