package com.somdiproy.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ai-demos")
public class AiDemoController {

    @GetMapping
    public String aiDemos(Model model) {
        model.addAttribute("title", "AI Demos & Applications - Somdip Roy");
        model.addAttribute("description", "Live demonstrations of enterprise AI solutions using AWS machine learning services, document processing, and intelligent automation.");
        model.addAttribute("currentPage", "ai-demos");
        
        // Current and planned AI projects
        List<Map<String, Object>> demos = List.of(
            Map.of(
                "id", "hr-screening",
                "title", "HR Resume Screening",
                "description", "AI-powered hiring automation using AWS Textract and Comprehend for intelligent candidate matching",
                "status", "live",
                "icon", "fas fa-users",
                "color", "blue",
                "tags", List.of("AWS Textract", "Comprehend", "Spring Boot", "Machine Learning"),
                "metrics", Map.of(
                    "processed", "150+ Resumes",
                    "accuracy", "85% Match Rate",
                    "time_saved", "90% Time Reduction"
                ),
                "features", List.of(
                    "Resume parsing with Amazon Textract",
                    "Skills extraction with Amazon Comprehend", 
                    "Intelligent job matching algorithm",
                    "Real-time candidate ranking",
                    "Batch processing capability",
                    "Export and reporting features"
                )
            ),
            Map.of(
                "id", "document-intelligence",
                "title", "Document Intelligence Suite",
                "description", "Enterprise document processing with text extraction, sentiment analysis, and entity recognition",
                "status", "development",
                "icon", "fas fa-file-alt",
                "color", "green",
                "tags", List.of("AWS Textract", "Comprehend", "Entity Recognition"),
                "plannedFeatures", List.of(
                    "Multi-format document support",
                    "Real-time sentiment analysis",
                    "Named entity recognition",
                    "Document classification",
                    "Batch processing workflows"
                )
            ),
            Map.of(
                "id", "security-analyzer",
                "title", "AI Security Analyzer",
                "description", "Automated security vulnerability detection using machine learning for code and infrastructure",
                "status", "planning",
                "icon", "fas fa-shield-alt",
                "color", "red",
                "tags", List.of("Security", "SAST", "DAST", "ML", "DevSecOps"),
                "plannedFeatures", List.of(
                    "Automated SAST/DAST integration",
                    "ML-powered vulnerability detection",
                    "Risk assessment and prioritization",
                    "Compliance reporting",
                    "CI/CD pipeline integration"
                )
            ),
            Map.of(
                "id", "cloud-optimizer",
                "title", "AWS Cloud Cost Optimizer",
                "description", "AI-driven cloud resource optimization and cost management solution",
                "status", "planning",
                "icon", "fas fa-cloud-dollar",
                "color", "purple",
                "tags", List.of("AWS", "Cost Optimization", "Machine Learning", "Analytics"),
                "plannedFeatures", List.of(
                    "Automated resource right-sizing",
                    "Usage pattern analysis",
                    "Cost prediction and alerts",
                    "Optimization recommendations",
                    "Multi-account management"
                )
            )
        );
        
        model.addAttribute("demos", demos);
        return "ai-demos/index";
    }

    @GetMapping("/hr-screening")
    public String hrScreening(Model model) {
        model.addAttribute("title", "HR Resume Screening - AI Demo | Somdip Roy");
        model.addAttribute("description", "Interactive demonstration of AI-powered hiring automation using Amazon Textract and Comprehend for intelligent resume processing and candidate matching.");
        model.addAttribute("currentPage", "ai-demos");
        model.addAttribute("projectName", "HR Resume Screening");
        
        // Technical implementation details
        model.addAttribute("architecture", Map.of(
            "frontend", List.of("Spring Boot", "Thymeleaf", "Tailwind CSS", "JavaScript"),
            "backend", List.of("Java 17", "Spring Boot", "REST APIs", "AWS SDK"),
            "ai_services", List.of("Amazon Textract", "Amazon Comprehend", "Amazon S3"),
            "security", List.of("Spring Security", "JWT", "HTTPS", "Input Validation"),
            "deployment", List.of("Docker", "AWS ECS", "Application Load Balancer", "CloudFront")
        ));
        
        // Demo configuration
        model.addAttribute("demoConfig", Map.of(
            "embedUrl", "http://localhost:8081",
            "fullAppUrl", "http://localhost:8081",
            "githubUrl", "https://github.com/somdiproy/hr-resume-screening",
            "status", "live",
            "lastUpdated", "February 2025"
        ));
        
        // Additional demo metrics for the detailed page
        model.addAttribute("demoMetrics", Map.of(
            "totalResumes", "150+",
            "accuracy", "85%",
            "timeReduction", "90%",
            "avgProcessingTime", "< 30 seconds",
            "supportedFormats", "PDF, DOC, DOCX, TXT",
            "languages", "English, Hindi"
        ));
        
        // Key features for the demo
        model.addAttribute("keyFeatures", List.of(
            "Automatic OCR and text extraction from resumes",
            "Skills and experience identification using NLP",
            "Job requirement matching algorithm",
            "Candidate ranking and scoring system",
            "Real-time processing with immediate results",
            "Secure document handling and data privacy"
        ));
        
        return "ai-demos/hr-screening";
    }
    
    /**
     * Placeholder for future AI demo pages
     * This method can be extended to handle other demo pages
     */
    @GetMapping("/{demoId}")
    public String genericDemo(Model model) {
        model.addAttribute("title", "AI Demo - Coming Soon | Somdip Roy");
        model.addAttribute("currentPage", "ai-demos");
        
        // For now, redirect to the main AI demos page
        return "redirect:/ai-demos";
    }
}