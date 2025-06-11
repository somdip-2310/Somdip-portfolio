package com.somdiproy.portfolio.service;

import com.somdiproy.portfolio.model.ContactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * Email service for handling contact form submissions
 * Updated to work with simplified ContactForm and conditional email configuration
 */
@Service
public class EmailService {
    
    private static final Logger logger = Logger.getLogger(EmailService.class.getName());
    
    // Make JavaMailSender optional to prevent startup failures
    @Autowired(required = false)
    private JavaMailSender mailSender;
    
    @Value("${spring.mail.username:}")
    private String fromEmail;
    
    @Value("${spring.mail.enabled:false}")
    private boolean emailEnabled;
    
    @Value("${email.service.from.address:noreply@somdip.dev}")
    private String defaultFromAddress;
    
    @Value("${email.service.from.name:Somdip Roy Portfolio}")
    private String fromName;
    
    /**
     * Send contact email notification and confirmation
     * Updated to handle simplified contact form fields with email service validation
     */
    public void sendContactEmail(ContactForm contactForm) {
        // Check if email service is properly configured
        if (!isEmailConfigured()) {
            logger.warning("Email service is not configured. Contact form submission logged but not emailed.");
            logContactFormSubmission(contactForm);
            return;
        }
        
        try {
            // Send notification email to yourself
            sendNotificationEmail(contactForm);
            
            // Send confirmation email to the user
            sendConfirmationEmail(contactForm);
            
            logger.info("Contact emails sent successfully for: " + contactForm.getEmail());
            
        } catch (Exception e) {
            // Log the error but don't break the application
            logger.severe("Error sending email for contact form: " + e.getMessage());
            logContactFormSubmission(contactForm);
            
            // Still throw exception for controller to handle gracefully
            throw new RuntimeException("Failed to send email", e);
        }
    }
    
    /**
     * Send notification email to yourself with contact form details
     */
    private void sendNotificationEmail(ContactForm contactForm) {
        if (!isEmailConfigured()) return;
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(getFromAddress());
        message.setTo(getFromAddress());
        message.setSubject("Portfolio Contact: " + contactForm.getSubject());
        
        StringBuilder emailBody = new StringBuilder();
        emailBody.append("New contact form submission:\n\n");
        emailBody.append("Name: ").append(contactForm.getName()).append("\n");
        emailBody.append("Email: ").append(contactForm.getEmail()).append("\n");
        
        // Company is optional
        if (contactForm.getCompany() != null && !contactForm.getCompany().trim().isEmpty()) {
            emailBody.append("Company: ").append(contactForm.getCompany()).append("\n");
        }
        
        emailBody.append("Subject: ").append(contactForm.getSubject()).append("\n");
        emailBody.append("\nMessage:\n").append(contactForm.getMessage());
        
        // Add timestamp and source info
        emailBody.append("\n\n---\n");
        emailBody.append("Submitted: ").append(new java.util.Date().toString()).append("\n");
        emailBody.append("Source: Portfolio Website Contact Form\n");
        emailBody.append("IP: [To be logged by web server]\n");
        
        message.setText(emailBody.toString());
        mailSender.send(message);
    }
    
    /**
     * Send confirmation email to the user
     */
    private void sendConfirmationEmail(ContactForm contactForm) {
        if (!isEmailConfigured()) return;
        
        SimpleMailMessage confirmationMessage = new SimpleMailMessage();
        confirmationMessage.setFrom(getFromAddress());
        confirmationMessage.setTo(contactForm.getEmail());
        confirmationMessage.setSubject("Thank you for contacting Somdip Roy - Senior Technical Architect");
        
        String confirmationBody = String.format(
            "Dear %s,\n\n" +
            "Thank you for reaching out! I have received your message regarding: \"%s\"\n\n" +
            "I appreciate your interest and will review your inquiry carefully. " +
            "I typically respond to all messages within 24 hours during business days.\n\n" +
            "About Me:\n" +
            "• Senior Technical Architect with 13+ years of experience\n" +
            "• Specializing in Java, Spring Boot, AWS, and AI/ML solutions\n" +
            "• Expert in enterprise architecture and cloud-native applications\n" +
            "• Led teams of 15+ engineers and delivered 30+ enterprise projects\n\n" +
            "Connect with me:\n" +
            "• LinkedIn: https://www.linkedin.com/in/somdip-roy-b8004b111/\n" +
            "• Portfolio: https://somdip.dev\n" +
            "• Email: somdiproy.roy@gmail.com\n" +
            "• Location: Mumbai, Maharashtra, India\n\n" +
            "If you have any urgent questions or would like to discuss AI/ML solutions, " +
            "enterprise architecture, or cloud migration strategies, feel free to connect with me on LinkedIn.\n\n" +
            "Best regards,\n" +
            "Somdip Roy\n" +
            "Senior Technical Architect\n" +
            "13+ Years Experience | Java | Spring Boot | AWS | AI/ML\n\n" +
            "---\n" +
            "This is an automated confirmation. Please do not reply to this email.\n" +
            "For direct communication, use: somdiproy.roy@gmail.com",
            contactForm.getName(),
            contactForm.getSubject()
        );
        
        confirmationMessage.setText(confirmationBody);
        mailSender.send(confirmationMessage);
    }
    
    /**
     * Log contact form submission when email is not available
     */
    private void logContactFormSubmission(ContactForm contactForm) {
        logger.info("=== CONTACT FORM SUBMISSION ===");
        logger.info("Name: " + contactForm.getName());
        logger.info("Email: " + contactForm.getEmail());
        logger.info("Company: " + (contactForm.getCompany() != null ? contactForm.getCompany() : "Not provided"));
        logger.info("Subject: " + contactForm.getSubject());
        logger.info("Message: " + contactForm.getMessage());
        logger.info("Timestamp: " + new java.util.Date().toString());
        logger.info("==============================");
    }
    
    /**
     * Utility method to validate email configuration
     */
    public boolean isEmailConfigured() {
        boolean configured = emailEnabled && 
                           mailSender != null && 
                           (fromEmail != null && !fromEmail.trim().isEmpty() || 
                            defaultFromAddress != null && !defaultFromAddress.trim().isEmpty());
        
        if (!configured) {
            logger.info("Email service status - Enabled: " + emailEnabled + 
                       ", MailSender: " + (mailSender != null) + 
                       ", FromEmail: " + (fromEmail != null && !fromEmail.trim().isEmpty()));
        }
        
        return configured;
    }
    
    /**
     * Get the appropriate from address
     */
    private String getFromAddress() {
        if (fromEmail != null && !fromEmail.trim().isEmpty()) {
            return fromEmail;
        }
        return defaultFromAddress;
    }
    
    /**
     * Test method to check email service status
     */
    public String getEmailServiceStatus() {
        if (!emailEnabled) {
            return "Email service is disabled in configuration";
        }
        if (mailSender == null) {
            return "JavaMailSender bean is not available";
        }
        if (!isEmailConfigured()) {
            return "Email configuration is incomplete";
        }
        return "Email service is properly configured and ready";
    }
    
    /**
     * Send a test email to verify configuration
     */
    public boolean sendTestEmail() {
        if (!isEmailConfigured()) {
            logger.warning("Cannot send test email - service not configured");
            return false;
        }
        
        try {
            SimpleMailMessage testMessage = new SimpleMailMessage();
            testMessage.setFrom(getFromAddress());
            testMessage.setTo(getFromAddress());
            testMessage.setSubject("Portfolio Email Service Test");
            testMessage.setText("This is a test email from the portfolio email service.\n\n" +
                               "If you receive this, the email configuration is working correctly.\n\n" +
                               "Timestamp: " + new java.util.Date().toString());
            
            mailSender.send(testMessage);
            logger.info("Test email sent successfully");
            return true;
            
        } catch (Exception e) {
            logger.severe("Test email failed: " + e.getMessage());
            return false;
        }
    }
}