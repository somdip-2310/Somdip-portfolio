package com.somdiproy.portfolio.service;

import com.somdiproy.portfolio.model.ContactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendContactEmail(ContactForm contactForm) {
        // Send email to yourself
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(fromEmail);
        message.setSubject("Portfolio Contact: " + contactForm.getSubject());
        
        StringBuilder emailBody = new StringBuilder();
        emailBody.append("New contact form submission:\n\n");
        emailBody.append("Name: ").append(contactForm.getName()).append("\n");
        emailBody.append("Email: ").append(contactForm.getEmail()).append("\n");
        
        if (contactForm.getCompany() != null && !contactForm.getCompany().trim().isEmpty()) {
            emailBody.append("Company: ").append(contactForm.getCompany()).append("\n");
        }
        
        if (contactForm.getPhone() != null && !contactForm.getPhone().trim().isEmpty()) {
            emailBody.append("Phone: ").append(contactForm.getPhone()).append("\n");
        }
        
        if (contactForm.getProjectType() != null && !contactForm.getProjectType().trim().isEmpty()) {
            emailBody.append("Project Type: ").append(contactForm.getProjectType()).append("\n");
        }
        
        if (contactForm.getTimeline() != null && !contactForm.getTimeline().trim().isEmpty()) {
            emailBody.append("Timeline: ").append(contactForm.getTimeline()).append("\n");
        }
        
        if (contactForm.getBudget() != null && !contactForm.getBudget().trim().isEmpty()) {
            emailBody.append("Budget: ").append(contactForm.getBudget()).append("\n");
        }
        
        emailBody.append("\nMessage:\n").append(contactForm.getMessage());
        
        message.setText(emailBody.toString());
        mailSender.send(message);

        // Send confirmation email to the user
        SimpleMailMessage confirmationMessage = new SimpleMailMessage();
        confirmationMessage.setFrom(fromEmail);
        confirmationMessage.setTo(contactForm.getEmail());
        confirmationMessage.setSubject("Thank you for contacting Somdip Roy");
        
        String confirmationBody = String.format(
            "Dear %s,\n\n" +
            "Thank you for reaching out! I have received your message regarding: %s\n\n" +
            "I will review your inquiry and get back to you within 24 hours.\n\n" +
            "Best regards,\n" +
            "Somdip Roy\n" +
            "Technical Architect\n" +
            "somdiproy.roy@gmail.com\n" +
            "+91 9004233112",
            contactForm.getName(),
            contactForm.getSubject()
        );
        
        confirmationMessage.setText(confirmationBody);
        mailSender.send(confirmationMessage);
    }
}