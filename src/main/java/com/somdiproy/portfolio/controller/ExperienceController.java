package com.somdiproy.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/experience")
public class ExperienceController {

    @GetMapping
    public String experience(Model model) {
        model.addAttribute("title", "Professional Experience - Somdip Roy | 13+ Years Technical Architect");
        model.addAttribute("description", "Explore Somdip Roy's 13+ years of professional experience as a Technical Architect, including roles at UPL, Jio, and other leading organizations. Expertise in Java, Spring Boot, AWS, and enterprise solutions.");
        model.addAttribute("currentPage", "experience");
        
        // Professional Experience - Updated with 13+ years
        List<Map<String, Object>> experiences = Arrays.asList(
            createExperience(
                "upl-technical-architect",
                "Senior Technical Architect", // Updated title
                "United Phosphorous Limited (UPL)",
                "Oct 2022",
                "Present",
                "2+ years",
                "Mumbai, India",
                "Chemical Manufacturing & Agriculture",
                "Leading technical architecture and development across multiple domains including sales, HR, marketing, manufacturing, GRC, and security. Managing 22+ applications and a team of 15+ members while coordinating with 4 external vendors.",
                Arrays.asList(
                    "Managed development and maintenance of applications across sales, HR, marketing, manufacturing, GRC, and security domains",
                    "Led and mentored a team of 15+ members overseeing 22+ in-house applications",
                    "Executed strategic SSO migration from Okta to Entra, managing ~18,000 users, ~200 applications, and ~32,000 devices",
                    "Managed application modernization using 6R strategy (retain, retire, rehost, re-platform, refactor, repurchase, rebuild)",
                    "Enhanced security by integrating SAST with CodeQL and implementing DAST through VAPT and WAPT",
                    "Implemented CI/CD pipelines using GitHub Actions with cross-platform support and self-hosted runners",
                    "Led migration of 6 Oracle servers from Oracle Cloud to AWS within one month using Critical Path Method",
                    "Implemented Software Asset Management (SAM) and Hardware Asset Management (HAM) using ServiceNow Discovery"
                ),
                Arrays.asList("Java", "Spring Framework", "Angular", "AWS", "GitHub Actions", "CodeQL", "SAST", "DAST", "VAPT", "ServiceNow", "Oracle Cloud", "Microsoft Entra", "CI/CD"),
                Arrays.asList(
                    "Delivered enterprise solutions with 99% uptime", // Removed cost savings reference
                    "Achieved 15% reduction in cloud computing costs by transitioning to PAAS solutions",
                    "Successfully migrated 18,000+ users with zero downtime",
                    "Enhanced application performance by replacing JDBC with ORM",
                    "Significantly reduced CapEx through Oracle to AWS migration"
                ),
                "fas fa-building",
                "blue"
            ),
            createExperience(
                "ril-development-lead",
                "Development Lead",
                "Reliance Industries Limited",
                "Jul 2019",
                "Oct 2022",
                "3 years 4 months",
                "Mumbai, India",
                "Telecommunications & 5G Infrastructure",
                "Led development of 5G infrastructure solutions including Pre-RFC workflows, material reconciliation, and quality audit systems. Implemented comprehensive security testing and API integrations with SAP systems.",
                Arrays.asList(
                    "Implemented 5G Pre Ready for Construction (Pre RFC) flow with SAP integration for billing and invoicing",
                    "Developed 5G material reconciliation workflow for site decommissioning with inventory management",
                    "Successfully deployed 5G WIFI modules in collaboration with Inventory system (Granite), CPNR, and EDA",
                    "Implemented UBR Quality Audit flow with real-time equipment quality capture via Angular and Spring Boot",
                    "Conducted comprehensive SAST and DAST vulnerability assessments for web applications",
                    "Performed VAPT for server vulnerabilities and WAPT for web application security testing",
                    "Enhanced REST API security with signed/encrypted JWT tokens, TLS encryption, and rate limiting",
                    "Secured web services using SAML authentication and XML encryption for SOAP messages",
                    "Implemented Camunda BPM in SiteForge for smooth task transitions and process monitoring"
                ),
                Arrays.asList("5G Technology", "Angular", "Spring Boot", "SAP Integration", "Camunda BPM", "SAST", "DAST", "VAPT", "WAPT", "JWT", "SAML", "XML Encryption", "JMS", "TLS"),
                Arrays.asList(
                    "Successfully integrated 5G infrastructure with existing SAP systems",
                    "Implemented comprehensive security testing covering SAST, DAST, VAPT, and WAPT",
                    "Enhanced API security with advanced authentication and encryption",
                    "Streamlined quality audit processes with real-time web-based solutions",
                    "Secured messaging services with SSL/TLS encryption"
                ),
                "fas fa-broadcast-tower",
                "purple"
            ),
            createExperience(
                "accenture-senior-developer",
                "Senior Developer (Java)",
                "Accenture India",
                "Dec 2014",
                "Jun 2019",
                "4+ years (with gap)",
                "Pune, India",
                "IT Services & Consulting",
                "Developed UIM cartridges and telecom solutions for major service providers. Specialized in requirements gathering, system design, and performance optimization using Java technologies and agile methodologies.",
                Arrays.asList(
                    "Developed and deployed UIM cartridges within Eclipse SCE environment for reusable rule deployment",
                    "Drafted comprehensive use cases and Software Requirement Specifications (SRS) for telecom service providers",
                    "Employed IEEE 830 and Agile methodologies for requirements gathering and documentation",
                    "Created and normalized equipment, network, and service models for improved system performance",
                    "Designed and implemented web services for UIM entities within Telecom domain using Java",
                    "Optimized performance through Spring cache, DB indexing, Hikari connection pool, and @Async annotations",
                    "Implemented robust error handling with global exception handler using @ControllerAdvice",
                    "Ensured security with Spring Security and JWT tokens alongside input validations"
                ),
                Arrays.asList("Java", "Spring Framework", "Hibernate", "Maven", "Oracle SQL", "PostgreSQL", "UIM", "Eclipse SCE", "Spring Security", "JWT", "IEEE 830", "Agile"),
                Arrays.asList(
                    "Significant improvements in system performance and availability through model optimization",
                    "Enhanced operational efficiency through streamlined UIM cartridge deployment",
                    "Improved system throughput with performance optimization techniques",
                    "Strengthened application security with comprehensive validation and authentication",
                    "Successfully delivered requirements aligned with business needs and technical feasibility"
                ),
                "fas fa-users-cog",
                "green"
            ),
            createExperience(
                "ericsson-solution-integrator",
                "Solution Integrator",
                "Ericsson Global India Limited",
                "Jan 2016",
                "Dec 2016",
                "1 year",
                "Bangalore, India",
                "Telecommunications & Network Integration",
                "Worked on telecom network integration projects, focusing on solution design and implementation for global telecommunications infrastructure.",
                Arrays.asList(
                    "Designed and implemented network integration solutions for telecom operators",
                    "Collaborated with cross-functional teams on network optimization projects",
                    "Participated in solution architecture design for telecommunications systems",
                    "Supported network deployment and integration activities",
                    "Worked with telecom protocols and network management systems",
                    "Provided quick resolutions and fixes to achieve high customer satisfaction"
                ),
                Arrays.asList("Network Integration", "Telecommunications", "Solution Architecture", "System Design", "Network Protocols", "OSS/BSS", "Network Management"),
                Arrays.asList(
                    "Achieved high CSAT scores through quick resolutions and fixes",
                    "Received Rockstar Award for exceptional customer service",
                    "Successfully delivered network integration projects",
                    "Enhanced solution design and integration capabilities"
                ),
                "fas fa-network-wired",
                "orange"
            ),
            createExperience(
                "wipro-software-developer",
                "Software Developer",
                "Wipro Technologies",
                "Nov 2012",
                "Dec 2014",
                "2 years 2 months",
                "Bangalore, India",
                "Software Development & IT Services",
                "Started my professional journey as a software developer, working on enterprise applications and gaining foundational experience in software development lifecycle.",
                Arrays.asList(
                    "Developed and maintained enterprise software applications using Java technologies",
                    "Participated in full software development lifecycle from requirements to deployment",
                    "Worked on Network Inventory (M6 Application) stabilization and optimization",
                    "Collaborated with senior developers and learned industry best practices",
                    "Gained experience in client interaction and project delivery",
                    "Contributed to application performance improvements and bug fixes"
                ),
                Arrays.asList("Java", "J2EE", "Enterprise Applications", "Network Inventory", "Software Development", "SDLC", "Application Maintenance"),
                Arrays.asList(
                    "Received Feather In My Cap Award for efforts to stabilize Network Inventory (M6 Application)",
                    "Successfully completed comprehensive training programs",
                    "Gained strong foundation in enterprise software development",
                    "Developed excellent problem-solving and analytical skills",
                    "Contributed to critical application stabilization efforts"
                ),
                "fas fa-code",
                "indigo"
            )
        );
        
        model.addAttribute("experiences", experiences);
        
        // Experience Statistics - Updated to 13+ years
        Map<String, Object> experienceStats = new HashMap<>();
        experienceStats.put("totalExperience", "13+ years");
        experienceStats.put("companiesWorked", 5);
        experienceStats.put("projectsDelivered", "30+");
        experienceStats.put("applicationsManaged", "22+");
        model.addAttribute("experienceStats", experienceStats);
        
        // Core Competencies from resume
        model.addAttribute("coreCompetencies", Arrays.asList(
            "Enterprise Architecture",
            "Cloud Migration",
            "DevSecOps Implementation",
            "Team Leadership",
            "Java Spring Development",
            "AI/ML Integration",
            "Security Implementation",
            "Performance Optimization"
        ));
        
        // Technical Skills Categories
        Map<String, List<String>> technicalSkills = new HashMap<>();
        technicalSkills.put("Development", Arrays.asList("Java", "Spring Framework", "Hibernate", "Angular", "Maven"));
        technicalSkills.put("Security", Arrays.asList("SAST", "DAST", "VAPT", "WAPT", "JWT", "SAML", "Spring Security"));
        technicalSkills.put("Cloud & DevOps", Arrays.asList("AWS", "GitHub Actions", "CI/CD", "Docker", "Kubernetes"));
        technicalSkills.put("Integration", Arrays.asList("SAP Integration", "REST APIs", "SOAP", "JMS", "XML Encryption"));
        model.addAttribute("technicalSkills", technicalSkills);
        
        // Career Progression Timeline - Updated years and milestones
        model.addAttribute("careerMilestones", Arrays.asList(
            createMilestone("2012", "Career Beginning", "Started as Software Developer at Wipro Technologies"),
            createMilestone("2014", "Foundation Established", "Gained core development skills and received recognition"),
            createMilestone("2016", "Network Expertise", "Moved to Ericsson for telecom network integration"),
            createMilestone("2017", "Senior Development", "Advanced to Senior Developer role at Accenture"),
            createMilestone("2019", "Leadership Role", "Became Development Lead at Reliance Industries"),
            createMilestone("2022", "Technical Architect", "Promoted to Senior Technical Architect at UPL"),
            createMilestone("2025", "AI/GenAI Expert", "Specialized in AI/ML solutions and AWS Bedrock")
        ));
        
        // Certifications and Awards
        model.addAttribute("certifications", Arrays.asList(
            createCertification("AWS Certified Solutions Architect – Associate", "Amazon Web Services", "Jan 2025 - Jan 2028"),
            createCertification("DevSecOps Foundation (DSOF)", "DevOps Institute", "Jun 2023 - Jun 2025"),
            createCertification("Feather In My Cap Award", "Wipro Limited", "Sep 2013"),
            createCertification("Rockstar Award", "Ericsson Global India Limited", "Oct 2016")
        ));
        
        return "experience/index";
    }
    
    private Map<String, Object> createExperience(String id, String position, String company, 
            String startDate, String endDate, String duration, String location, String industry,
            String description, List<String> responsibilities, List<String> technologies, 
            List<String> achievements, String icon, String color) {
        
        Map<String, Object> experience = new HashMap<>();
        experience.put("id", id);
        experience.put("position", position);
        experience.put("company", company);
        experience.put("startDate", startDate);
        experience.put("endDate", endDate);
        experience.put("duration", duration);
        experience.put("location", location);
        experience.put("industry", industry);
        experience.put("description", description);
        experience.put("responsibilities", responsibilities);
        experience.put("technologies", technologies);
        experience.put("achievements", achievements);
        experience.put("icon", icon);
        experience.put("color", color);
        
        return experience;
    }
    
    private Map<String, Object> createMilestone(String year, String title, String description) {
        Map<String, Object> milestone = new HashMap<>();
        milestone.put("year", year);
        milestone.put("title", title);
        milestone.put("description", description);
        return milestone;
    }
    
    private Map<String, Object> createCertification(String name, String issuer, String period) {
        Map<String, Object> certification = new HashMap<>();
        certification.put("name", name);
        certification.put("issuer", issuer);
        certification.put("period", period);
        return certification;
    }
}