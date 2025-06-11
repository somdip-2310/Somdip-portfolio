package com.somdiproy.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @GetMapping
    public String projects(Model model) {
        model.addAttribute("title", "Projects & Portfolio - Somdip Roy | 13+ Years Experience");
        model.addAttribute("description", "Explore Somdip Roy's professional projects, open source contributions, and technical implementations across enterprise development, cloud architecture, and AI/ML solutions spanning 13+ years of experience.");
        model.addAttribute("currentPage", "projects");
        
        // Professional Projects - Updated to reflect 13+ years experience and removed cost savings
        List<Map<String, Object>> projects = Arrays.asList(
            createProject(
                "enterprise-migration",
                "Enterprise Cloud Migration",
                "Led migration of 22+ legacy applications to AWS cloud infrastructure, achieving 15% cost reduction and improved scalability",
                "Cloud Architecture",
                "completed",
                "6 months",
                "15+ developers",
                Arrays.asList("AWS", "Spring Boot", "Docker", "Kubernetes", "Jenkins"),
                Arrays.asList(
                    "Migrated 6 Oracle servers to AWS within one month",
                    "Implemented CI/CD pipelines with GitHub Actions", 
                    "Achieved 99.9% uptime post-migration",
                    "Enhanced system scalability and performance"
                ),
                "fas fa-cloud-upload-alt",
                "blue"
            ),
            createProject(
                "sso-implementation",
                "Single Sign-On Implementation",
                "Implemented enterprise SSO solution migrating from Okta to Microsoft Entra, serving 18,000+ users across multiple applications",
                "Security & Identity",
                "completed", 
                "4 months",
                "8 developers",
                Arrays.asList("Microsoft Entra", "SAML", "OAuth 2.0", "Spring Security", "JWT"),
                Arrays.asList(
                    "Seamless migration with zero downtime",
                    "Improved security with MFA integration",
                    "Enhanced user experience across applications",
                    "Achieved enterprise-grade identity management"
                ),
                "fas fa-shield-alt",
                "green"
            ),
            createProject(
                "5g-infrastructure",
                "5G Network Infrastructure",
                "Developed 5G-ready infrastructure solutions including material reconciliation and quality audit workflows",
                "Telecommunications",
                "completed",
                "8 months", 
                "12 developers",
                Arrays.asList("5G Technology", "Angular", "Spring Boot", "SAP Integration", "Camunda BPM"),
                Arrays.asList(
                    "Implemented 5G Pre-RFC workflow automation",
                    "Built material reconciliation for site decommissioning",
                    "Integrated with SAP Inventory systems",
                    "Automated quality audit processes"
                ),
                "fas fa-broadcast-tower",
                "purple"
            ),
            createProject(
                "ai-hr-screening",
                "AI-Powered HR Resume Screening",
                "Built enterprise AI solution using AWS S3, Amazon Textract, and Amazon Bedrock for intelligent resume processing and candidate matching",
                "AI/ML Solutions",
                "live",
                "3 months",
                "5 developers",
                Arrays.asList("AWS S3", "Amazon Textract", "Amazon Bedrock", "Spring Boot", "GenAI"),
                Arrays.asList(
                    "90% faster resume processing with AI",
                    "85% accuracy in candidate matching",
                    "Secure document handling with AWS S3",
                    "Real-time GenAI analysis with Bedrock"
                ),
                "fas fa-brain",
                "indigo"
            ),
            createProject(
                "security-automation",
                "DevSecOps Pipeline Automation",
                "Integrated SAST/DAST security tools into CI/CD pipelines with automated vulnerability scanning and reporting",
                "DevSecOps",
                "ongoing",
                "3 months",
                "6 developers",
                Arrays.asList("CodeQL", "OWASP ZAP", "SonarQube", "GitHub Actions", "Docker"),
                Arrays.asList(
                    "Automated security scanning in CI/CD",
                    "Integrated penetration testing workflows", 
                    "Real-time vulnerability reporting",
                    "Compliance dashboard implementation"
                ),
                "fas fa-bug",
                "red"
            ),
            createProject(
                "microservices-architecture",
                "Microservices Architecture Design",
                "Architected and implemented microservices-based system replacing monolithic applications for better scalability",
                "System Architecture", 
                "completed",
                "10 months",
                "20+ developers",
                Arrays.asList("Spring Boot", "Docker", "Kubernetes", "API Gateway", "Service Mesh"),
                Arrays.asList(
                    "Decomposed monolith into 15+ microservices",
                    "Implemented API gateway and service discovery",
                    "Enhanced system resilience and scalability", 
                    "Reduced deployment time by 70%"
                ),
                "fas fa-project-diagram",
                "orange"
            )
        );
        
        model.addAttribute("projects", projects);
        
        // Project categories for filtering - Updated
        model.addAttribute("categories", Arrays.asList(
            "All Projects",
            "Cloud Architecture", 
            "Security & Identity",
            "Telecommunications",
            "AI/ML Solutions",
            "DevSecOps",
            "System Architecture"
        ));
        
        // Technology stats - Updated to reflect 13+ years
        Map<String, Object> techStats = new HashMap<>();
        techStats.put("totalProjects", projects.size());
        techStats.put("completedProjects", (int) projects.stream().mapToLong(p -> "completed".equals(p.get("status")) ? 1 : 0).sum());
        techStats.put("technologiesUsed", 30);
        techStats.put("teamMembers", "60+");
        techStats.put("yearsExperience", "13+");
        model.addAttribute("techStats", techStats);
        
        return "projects/index";
    }
    
    private Map<String, Object> createProject(String id, String title, String description, 
            String category, String status, String duration, String team, 
            List<String> technologies, List<String> highlights, String icon, String color) {
        
        Map<String, Object> project = new HashMap<>();
        project.put("id", id);
        project.put("title", title);
        project.put("description", description);
        project.put("category", category);
        project.put("status", status);
        project.put("duration", duration);
        project.put("team", team);
        project.put("technologies", technologies);
        project.put("highlights", highlights);
        project.put("icon", icon);
        project.put("color", color);
        
        return project;
    }
}