package com.somdiproy.portfolio.service;

import com.somdiproy.portfolio.model.Experience;
import com.somdiproy.portfolio.model.Skill;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExperienceService {

    public List<Experience> getAllExperiences() {
        return List.of(
            new Experience(
                "United Phosphorous Limited (UPL)",
                "Technical Architect",
                "Mumbai, India",
                LocalDate.of(2022, 10, 1),
                null, // Current position
                "Leading application development across multiple domains including sales, HR, marketing, manufacturing, GRC, and security.",
                List.of(
                    "Managed team of 15+ members overseeing 22+ in-house applications",
                    "Saved $300K annually by migrating SSO from Okta to Entra for ~18,000 users",
                    "Achieved 15% reduction in cloud computing costs through PAAS migration",
                    "Implemented CI/CD pipelines using GitHub Actions with SAST/DAST integration",
                    "Led Oracle Cloud to AWS migration for 6 servers within one month",
                    "Enhanced security with CodeQL integration and penetration testing"
                ),
                List.of("Java", "Spring Framework", "AWS", "GitHub Actions", "CodeQL", "SAST/DAST", "ServiceNow", "Oracle Cloud", "Microservices")
            ),
            new Experience(
                "Reliance Industries Limited",
                "Development Lead",
                "Mumbai, India",
                LocalDate.of(2019, 7, 1),
                LocalDate.of(2022, 10, 1),
                "Implemented 5G infrastructure solutions and security frameworks for telecom operations.",
                List.of(
                    "Implemented 5G Pre Ready for Construction (Pre RFC) flow with SAP integration",
                    "Developed 5G material reconciliation workflow for site decommissioning",
                    "Successfully deployed 5G WIFI modules with Inventory system integration",
                    "Built UBR Quality Audit flow using Angular and Spring Boot",
                    "Conducted comprehensive VAPT/WAPT security assessments",
                    "Enhanced REST API security with JWT tokens and TLS encryption",
                    "Implemented Camunda BPM for workflow automation"
                ),
                List.of("Java", "Spring Boot", "Angular", "5G Technology", "SAP Integration", "VAPT/WAPT", "JWT", "SAML", "Camunda BPM", "JMS")
            ),
            new Experience(
                "Accenture India",
                "Senior Developer (Java)",
                "Pune, India",
                LocalDate.of(2014, 12, 1),
                LocalDate.of(2019, 6, 1),
                "Developed UIM cartridges and web services for major telecom service providers.",
                List.of(
                    "Developed UIM cartridges within Eclipse SCE environment",
                    "Drafted comprehensive SRS using IEEE 830 and Agile methodologies",
                    "Created normalized equipment, network, and service models",
                    "Designed web services for UIM entities in telecom domain",
                    "Optimized performance using Spring cache and Hikari connection pool",
                    "Implemented robust security with Spring Security and JWT tokens"
                ),
                List.of("Java", "Spring Framework", "Hibernate", "Maven", "Oracle SQL", "PostgreSQL", "UIM", "IEEE 830", "Agile", "JWT")
            )
        );
    }

    public List<Skill> getTechnicalSkills() {
        return List.of(
            // Core Development
            new Skill("Java", "Programming Languages", 95, "12+ years experience with enterprise Java development"),
            new Skill("Spring Framework", "Frameworks", 90, "Expert in Spring Boot, Spring Security, Spring Cloud"),
            new Skill("Spring Boot", "Frameworks", 90, "Microservices architecture and REST API development"),
            new Skill("Angular", "Frontend", 75, "Modern web applications with TypeScript"),
            
            // Cloud & DevOps
            new Skill("AWS", "Cloud Platforms", 85, "Solutions Architect Associate certified"),
            new Skill("Kubernetes", "Container Orchestration", 80, "Amazon EKS and container management"),
            new Skill("CI/CD", "DevOps", 85, "GitHub Actions, automated deployment pipelines"),
            new Skill("Docker", "Containerization", 80, "Application containerization and deployment"),
            
            // Security
            new Skill("SAST/DAST", "Security", 85, "Static and dynamic application security testing"),
            new Skill("VAPT/WAPT", "Security", 80, "Vulnerability assessment and penetration testing"),
            new Skill("Spring Security", "Security", 85, "Authentication, authorization, and API security"),
            new Skill("JWT", "Security", 85, "Token-based authentication and authorization"),
            
            // Databases
            new Skill("Oracle SQL", "Databases", 80, "Complex queries, performance optimization"),
            new Skill("PostgreSQL", "Databases", 75, "Database design and optimization"),
            new Skill("Hibernate", "ORM", 85, "Object-relational mapping and JPA"),
            
            // Integration & Messaging
            new Skill("REST APIs", "Integration", 90, "RESTful web services design and development"),
            new Skill("SAML", "Integration", 75, "Single sign-on and identity federation"),
            new Skill("JMS", "Messaging", 75, "Java Message Service and enterprise messaging"),
            
            // Tools & Methodologies
            new Skill("Maven", "Build Tools", 85, "Project management and dependency resolution"),
            new Skill("Git", "Version Control", 90, "Advanced Git workflows and collaboration"),
            new Skill("Agile", "Methodologies", 85, "Scrum, IEEE 830, requirements gathering")
        );
    }

    public List<String> getCertifications() {
        return List.of(
            "AWS Certified Solutions Architect – Associate (Jan 2025 - Jan 2028)",
            "DevSecOps Foundation (DSOF), DevOps Institute (Jun 2023 - Jun 2025)",
            "Feather In My Cap Award, Wipro Limited (Sep 2013)",
            "Rockstar Award, Ericsson Global India Limited (Oct 2016)"
        );
    }
}