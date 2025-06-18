package com.somdiproy.portfolio.service;

import com.somdiproy.portfolio.model.ContactForm;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {
    
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    
    @Value("${sendgrid.api.key:}")
    private String sendGridApiKey;
    
    @Value("${sendgrid.enabled:false}")
    private boolean sendGridEnabled;
    
    @Value("${sendgrid.from.email:somdiproy.roy@gmail.com}")
    private String fromEmail;
    
    @Value("${sendgrid.from.name:Somdip Roy Portfolio}")
    private String fromName;
    
    @Value("${sendgrid.to.email:somdiproy.roy@gmail.com}")
    private String toEmail;
    
    @Value("${sendgrid.to.name:Somdip Roy}")
    private String toName;
    
    public void sendContactEmail(ContactForm contactForm) {
        if (!sendGridEnabled || sendGridApiKey.isEmpty()) {
            logger.warn("SendGrid is disabled or API key not configured. Contact form submission logged only.");
            logContactFormSubmission(contactForm);
            return;
        }
        
        try {
            // Create email object
            Mail mail = buildEmail(contactForm);
            
            // Send email via SendGrid API
            SendGrid sg = new SendGrid(sendGridApiKey);
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            
            Response response = sg.api(request);
            
            if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
                logger.info("Contact email sent successfully via SendGrid. Status: {}", response.getStatusCode());
                logger.info("Message-ID: {}", response.getHeaders().get("X-Message-Id"));
                logContactFormSubmission(contactForm);
            } else {
                logger.error("SendGrid API error. Status: {}, Body: {}", 
                    response.getStatusCode(), response.getBody());
                throw new RuntimeException("Failed to send email via SendGrid");
            }
            
        } catch (IOException e) {
            logger.error("Error sending email via SendGrid: {}", e.getMessage());
            logContactFormSubmission(contactForm);
            throw new RuntimeException("Failed to send email", e);
        }
    }
    
    private Mail buildEmail(ContactForm contactForm) {
        // From email (your verified sender)
        Email from = new Email(fromEmail, fromName);
        
        // To email (your Gmail)
        Email to = new Email(toEmail, toName);
        
        // Subject with sender's name
        String subject = String.format("[Portfolio Contact] %s - %s", 
            contactForm.getName(), contactForm.getSubject());
        
        // Create content
        Content content = new Content("text/plain", buildEmailContent(contactForm));
        
        // Build mail
        Mail mail = new Mail(from, subject, to, content);
        
        // Set reply-to as the sender's email
        Email replyTo = new Email(contactForm.getEmail(), contactForm.getName());
        mail.setReplyTo(replyTo);
        
        // Add custom headers
        mail.addHeader("X-Portfolio-Contact", "true");
        mail.addHeader("X-Sender-Company", contactForm.getCompany() != null ? contactForm.getCompany() : "N/A");
        
        // Add personalization
        Personalization personalization = new Personalization();
        personalization.addTo(to);
        
        // You can add custom substitutions if using templates
        personalization.addSubstitution("-name-", contactForm.getName());
        personalization.addSubstitution("-email-", contactForm.getEmail());
        
        return mail;
    }
    
    private String buildEmailContent(ContactForm contactForm) {
        StringBuilder content = new StringBuilder();
        content.append("New contact form submission from somdip.dev\n\n");
        content.append("==============================================\n\n");
        
        content.append("CONTACT DETAILS:\n");
        content.append("----------------\n");
        content.append("Name: ").append(contactForm.getName()).append("\n");
        content.append("Email: ").append(contactForm.getEmail()).append("\n");
        
        if (contactForm.getCompany() != null && !contactForm.getCompany().trim().isEmpty()) {
            content.append("Company: ").append(contactForm.getCompany()).append("\n");
        }
        
        content.append("\nSUBJECT:\n");
        content.append("----------------\n");
        content.append(contactForm.getSubject()).append("\n");
        
        content.append("\nMESSAGE:\n");
        content.append("----------------\n");
        content.append(contactForm.getMessage()).append("\n");
        
        content.append("\n==============================================\n\n");
        
        content.append("Timestamp: ").append(LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append(" UTC\n");
        content.append("Source: Portfolio Website (https://somdip.dev)\n");
        content.append("IP Address: Available in SendGrid Activity Feed\n");
        content.append("\n");
        content.append("Note: You can reply directly to this email to respond to ")
               .append(contactForm.getName()).append(" at ")
               .append(contactForm.getEmail());
        
        return content.toString();
    }
    
    private void logContactFormSubmission(ContactForm contactForm) {
        logger.info("=== CONTACT FORM SUBMISSION ===");
        logger.info("Name: {}", contactForm.getName());
        logger.info("Email: {}", contactForm.getEmail());
        logger.info("Company: {}", contactForm.getCompany());
        logger.info("Subject: {}", contactForm.getSubject());
        logger.info("Message: {}", contactForm.getMessage());
        logger.info("Timestamp: {}", LocalDateTime.now());
        logger.info("==============================");
    }
    
    /**
     * Test SendGrid configuration
     */
    public boolean testEmailConfiguration() {
        if (!sendGridEnabled || sendGridApiKey.isEmpty()) {
            logger.warn("SendGrid is disabled or not configured");
            return false;
        }
        
        try {
            Email from = new Email(fromEmail, fromName);
            Email to = new Email(toEmail, toName);
            String subject = "Test Email - Portfolio SendGrid Configuration";
            Content content = new Content("text/plain", 
                "This is a test email to verify SendGrid API configuration for somdip.dev portfolio.\n\n" +
                "If you receive this email, your SendGrid configuration is working correctly!\n\n" +
                "Sent at: " + LocalDateTime.now() + " UTC\n\n" +
                "SendGrid Features:\n" +
                "- Delivered via SendGrid Web API v3\n" +
                "- Activity tracking available in SendGrid dashboard\n" +
                "- Email analytics and open tracking available");
            
            Mail mail = new Mail(from, subject, to, content);
            
            SendGrid sg = new SendGrid(sendGridApiKey);
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            
            Response response = sg.api(request);
            
            if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
                logger.info("Test email sent successfully via SendGrid. Status: {}", response.getStatusCode());
                return true;
            } else {
                logger.error("Test email failed. Status: {}, Body: {}", 
                    response.getStatusCode(), response.getBody());
                return false;
            }
            
        } catch (Exception e) {
            logger.error("Test email failed: {}", e.getMessage());
            return false;
        }
    }
}