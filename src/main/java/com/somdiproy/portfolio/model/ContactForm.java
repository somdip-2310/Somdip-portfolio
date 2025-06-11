package com.somdiproy.portfolio.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Contact form model - Updated to remove projectType, timeline, budget, and phone fields
 * as per the simplified contact form requirements
 */
public class ContactForm {
    
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;
    
    @Size(max = 100, message = "Company name cannot exceed 100 characters")
    private String company;
    
    @NotBlank(message = "Subject is required")
    @Size(min = 5, max = 200, message = "Subject must be between 5 and 200 characters")
    private String subject;
    
    @NotBlank(message = "Message is required")
    @Size(min = 10, max = 2000, message = "Message must be between 10 and 2000 characters")
    private String message;
    
    // Removed fields: phone, projectType, timeline, budget
    
    // Default constructor
    public ContactForm() {}
    
    // Updated constructor with only remaining fields
    public ContactForm(String name, String email, String company, String subject, String message) {
        this.name = name;
        this.email = email;
        this.company = company;
        this.subject = subject;
        this.message = message;
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getCompany() {
        return company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    // Updated toString method with only remaining fields
    @Override
    public String toString() {
        return "ContactForm{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", company='" + company + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
    
    // Utility method for validation
    public boolean isValid() {
        return name != null && !name.trim().isEmpty() &&
               email != null && !email.trim().isEmpty() &&
               subject != null && !subject.trim().isEmpty() &&
               message != null && !message.trim().isEmpty();
    }
}