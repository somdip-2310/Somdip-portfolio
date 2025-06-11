package com.somdiproy.portfolio.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;
import java.util.logging.Logger;

/**
 * Mail configuration that creates JavaMailSender beans conditionally
 * This prevents startup failures when email is not configured
 */
@Configuration
public class MailConfiguration {

    private static final Logger logger = Logger.getLogger(MailConfiguration.class.getName());

    @Value("${spring.mail.host:smtp.gmail.com}")
    private String mailHost;

    @Value("${spring.mail.port:587}")
    private int mailPort;

    @Value("${spring.mail.username:}")
    private String mailUsername;

    @Value("${spring.mail.password:}")
    private String mailPassword;

    @Value("${spring.mail.properties.mail.smtp.auth:true}")
    private boolean smtpAuth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable:true}")
    private boolean starttlsEnable;

    @Value("${spring.mail.properties.mail.smtp.starttls.required:true}")
    private boolean starttlsRequired;

    @Value("${spring.mail.properties.mail.debug:false}")
    private boolean mailDebug;

    /**
     * Create JavaMailSender bean when email is properly enabled and configured
     */
    @Bean("javaMailSender")
    @ConditionalOnProperty(
        name = "spring.mail.enabled",
        havingValue = "true"
    )
    public JavaMailSender javaMailSender() {
        logger.info("Creating JavaMailSender bean - Email service is ENABLED");
        
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        
        // Basic SMTP configuration
        mailSender.setHost(mailHost);
        mailSender.setPort(mailPort);
        
        // Authentication
        if (mailUsername != null && !mailUsername.trim().isEmpty()) {
            mailSender.setUsername(mailUsername);
        }
        if (mailPassword != null && !mailPassword.trim().isEmpty()) {
            mailSender.setPassword(mailPassword);
        }
        
        // SMTP Properties
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", String.valueOf(smtpAuth));
        props.put("mail.smtp.starttls.enable", String.valueOf(starttlsEnable));
        props.put("mail.smtp.starttls.required", String.valueOf(starttlsRequired));
        props.put("mail.debug", String.valueOf(mailDebug));
        
        // Additional security properties
        props.put("mail.smtp.ssl.trust", mailHost);
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
        // Connection timeout properties
        props.put("mail.smtp.connectiontimeout", "10000");
        props.put("mail.smtp.timeout", "10000");
        props.put("mail.smtp.writetimeout", "10000");
        
        logger.info("JavaMailSender configured with host: " + mailHost + 
                   ", port: " + mailPort + 
                   ", username: " + (mailUsername != null && !mailUsername.isEmpty() ? "***configured***" : "not set"));
        
        return mailSender;
    }

    /**
     * Create a no-operation JavaMailSender when email is disabled
     * This prevents application startup failures
     */
    @Bean("javaMailSender")
    @ConditionalOnProperty(
        name = "spring.mail.enabled",
        havingValue = "false",
        matchIfMissing = true
    )
    public JavaMailSender noOpJavaMailSender() {
        logger.info("Creating NoOp JavaMailSender bean - Email service is DISABLED");
        return new NoOpJavaMailSender();
    }

    /**
     * No-operation JavaMailSender implementation
     * This allows the application to start without email configuration
     */
    private static class NoOpJavaMailSender extends JavaMailSenderImpl {
        
        private static final Logger noOpLogger = Logger.getLogger(NoOpJavaMailSender.class.getName());

        @Override
        public void send(org.springframework.mail.SimpleMailMessage simpleMessage) throws org.springframework.mail.MailException {
            noOpLogger.info("📧 Email service disabled - Would have sent email:");
            noOpLogger.info("   To: " + java.util.Arrays.toString(simpleMessage.getTo()));
            noOpLogger.info("   Subject: " + simpleMessage.getSubject());
            noOpLogger.info("   From: " + simpleMessage.getFrom());
            // Don't log the full message content for privacy
        }

        @Override
        public void send(org.springframework.mail.SimpleMailMessage... simpleMessages) throws org.springframework.mail.MailException {
            noOpLogger.info("📧 Email service disabled - Would have sent " + simpleMessages.length + " messages");
            for (org.springframework.mail.SimpleMailMessage message : simpleMessages) {
                send(message);
            }
        }

        @Override
        public void send(org.springframework.mail.javamail.MimeMessagePreparator mimeMessagePreparator) throws org.springframework.mail.MailException {
            noOpLogger.info("📧 Email service disabled - Would have sent MIME message");
        }

        @Override
        public void send(org.springframework.mail.javamail.MimeMessagePreparator... mimeMessagePreparators) throws org.springframework.mail.MailException {
            noOpLogger.info("📧 Email service disabled - Would have sent " + mimeMessagePreparators.length + " MIME messages");
        }
    }

    /**
     * Email configuration properties for validation
     */
    @Bean
    @ConfigurationProperties(prefix = "email.service")
    public EmailConfigProperties emailConfigProperties() {
        return new EmailConfigProperties();
    }

    /**
     * Configuration properties class for email service
     */
    public static class EmailConfigProperties {
        private String fromAddress = "noreply@somdip.dev";
        private String fromName = "Somdip Roy Portfolio";
        private boolean testModeEnabled = false;
        private String testRecipient = "";

        // Getters and Setters
        public String getFromAddress() { return fromAddress; }
        public void setFromAddress(String fromAddress) { this.fromAddress = fromAddress; }

        public String getFromName() { return fromName; }
        public void setFromName(String fromName) { this.fromName = fromName; }

        public boolean isTestModeEnabled() { return testModeEnabled; }
        public void setTestModeEnabled(boolean testModeEnabled) { this.testModeEnabled = testModeEnabled; }

        public String getTestRecipient() { return testRecipient; }
        public void setTestRecipient(String testRecipient) { this.testRecipient = testRecipient; }
    }
}