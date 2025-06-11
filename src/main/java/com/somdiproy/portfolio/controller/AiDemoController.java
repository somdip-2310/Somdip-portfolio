// ===============================================
// FIXED: AiDemoController.java
// ===============================================

package com.somdiproy.portfolio.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/ai-demos")
public class AiDemoController {

    // FIXED: Updated default URLs to use working endpoints
    @Value("${hr.demo.embed.url:https://demos.somdip.dev}")
    private String hrDemoEmbedUrl;
    
    @Value("${hr.demo.external.url:https://demos.somdip.dev}")
    private String hrDemoExternalUrl;
    
    @Value("${hr.demo.enabled:true}")
    private boolean hrDemoEnabled;
    
    @Value("${hr.demo.health.check.enabled:true}")
    private boolean healthCheckEnabled;
    
    @Value("${hr.demo.health.check.timeout:5000}")
    private int healthCheckTimeout;
    
    @Value("${hr.demo.health.strategy:actuator-health}")
    private String healthStrategy;

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
                "description", "AI-powered hiring automation using AWS S3, Amazon Textract, and Amazon Bedrock for intelligent candidate matching",
                "status", "live",
                "icon", "fas fa-users",
                "color", "blue",
                "tags", List.of("AWS S3", "Amazon Textract", "Amazon Bedrock", "Spring Boot", "GenAI"),
                "metrics", Map.of(
                    "processed", "150+ Resumes",
                    "accuracy", "85% Match Rate",
                    "time_saved", "90% Time Reduction"
                ),
                "features", List.of(
                    "Secure document storage with AWS S3",
                    "OCR processing with Amazon Textract", 
                    "GenAI analysis with Amazon Bedrock",
                    "Intelligent job matching algorithm",
                    "Real-time candidate ranking",
                    "Batch processing capability",
                    "Export and reporting features"
                )
            ),
            Map.of(
                "id", "document-intelligence",
                "title", "Document Intelligence Suite",
                "description", "Enterprise document processing with text extraction, sentiment analysis, and entity recognition using AWS AI services",
                "status", "development",
                "icon", "fas fa-file-alt",
                "color", "green",
                "tags", List.of("AWS S3", "Amazon Textract", "Amazon Bedrock", "Document AI"),
                "plannedFeatures", List.of(
                    "Multi-format document support with S3 storage",
                    "Real-time sentiment analysis with Bedrock",
                    "Named entity recognition using Amazon Comprehend",
                    "Document classification with machine learning",
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
        model.addAttribute("title", "HR Resume Screening - AI Demo | Somdip Roy | AWS S3 Textract Bedrock");
        model.addAttribute("description", "Experience AI-powered HR resume screening demo by Somdip Roy. Built with AWS S3, Amazon Textract, and Amazon Bedrock for intelligent document processing and candidate matching.");
        model.addAttribute("currentPage", "ai-demos");
        model.addAttribute("projectName", "AI-Powered HR Resume Screening");
        
        // Check if HR demo is accessible
        boolean isDemoAvailable = checkHrDemoHealth();
        
        // Technical implementation details
        model.addAttribute("architecture", Map.of(
            "frontend", List.of("Spring Boot", "Thymeleaf", "Tailwind CSS", "JavaScript"),
            "backend", List.of("Java 17", "Spring Boot", "REST APIs", "AWS SDK"),
            "ai_services", List.of("AWS S3", "Amazon Textract", "Amazon Bedrock"),
            "security", List.of("Spring Security", "JWT", "HTTPS", "Input Validation"),
            "deployment", List.of("Docker", "AWS ECS Fargate", "Application Load Balancer", "Route 53")
        ));
        
        // FIXED: Demo configuration with corrected URLs
        model.addAttribute("demoConfig", Map.of(
            "embedUrl", hrDemoEmbedUrl,
            "externalUrl", hrDemoExternalUrl,
            "fullAppUrl", hrDemoExternalUrl, // FIXED: Use external URL for full app
            "isAvailable", isDemoAvailable,
            "status", isDemoAvailable ? "live" : "unavailable",
            "lastUpdated", "June 2025",
            "healthStatus", isDemoAvailable ? "healthy" : "unhealthy"
        ));
        
        // Additional demo metrics
        model.addAttribute("demoMetrics", Map.of(
            "totalResumes", "150+",
            "accuracy", "85%",
            "timeReduction", "90%",
            "avgProcessingTime", "< 30 seconds",
            "supportedFormats", "PDF, DOC, DOCX, TXT",
            "languages", "English, Hindi",
            "uptime", isDemoAvailable ? "99.9%" : "Offline"
        ));
        
        // Key features for the demo
        model.addAttribute("keyFeatures", List.of(
            "Secure document storage with AWS S3",
            "Advanced OCR and text extraction with Amazon Textract",
            "GenAI-powered skills and experience identification using Amazon Bedrock",
            "Intelligent job requirement matching algorithm",
            "Candidate ranking and scoring system with AI analysis",
            "Real-time processing with immediate results",
            "Enterprise-grade security and data privacy compliance"
        ));
        
        // Business value metrics
        model.addAttribute("businessValue", Map.of(
            "speedImprovement", "90% faster",
            "manualReduction", "75% less effort",
            "qualityScore", "85% accuracy",
            "costSavings", "$2,500 per hire",
            "processingTime", "< 30 seconds",
            "scalability", "1000+ resumes/hour"
        ));
        
        return "ai-demos/hr-screening";
    }
    
    /**
     * FIXED: Health Check with Correct URL Strategy
     */
    private boolean checkHrDemoHealth() {
        if (!hrDemoEnabled || !healthCheckEnabled) {
            return hrDemoEnabled;
        }
        
        switch (healthStrategy.toLowerCase()) {
            case "actuator-health":
                return checkActuatorHealth();
            case "root-path":
                return checkRootPathHealth();
            case "external-url":
                return checkExternalUrlHealth();
            case "comprehensive":
                return checkComprehensiveHealth();
            default:
                return checkExternalUrlHealth(); // Default to external URL check
        }
    }
    
    /**
     * FIXED: Actuator Health Check with Correct URL
     */
    private boolean checkActuatorHealth() {
        try {
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setConnectTimeout(healthCheckTimeout);
            requestFactory.setReadTimeout(healthCheckTimeout);
            
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            
            // FIXED: Use the working external URL for health check
            String healthUrl = hrDemoExternalUrl + "/actuator/health";
            
            System.out.println("🔍 Checking HR Demo actuator health: " + healthUrl);
            
            ResponseEntity<String> response = restTemplate.getForEntity(healthUrl, String.class);
            
            boolean isHealthy = response.getStatusCode().is2xxSuccessful() && 
                               response.getBody() != null && 
                               response.getBody().contains("\"status\":\"UP\"");
            
            if (isHealthy) {
                System.out.println("✅ HR Demo actuator health check passed: " + response.getStatusCode());
                System.out.println("   Response: " + (response.getBody().length() > 100 ? 
                                 response.getBody().substring(0, 100) + "..." : response.getBody()));
            } else {
                System.out.println("⚠️ HR Demo actuator health check failed: " + response.getStatusCode());
            }
            
            return isHealthy;
            
        } catch (Exception e) {
            System.err.println("❌ HR Demo actuator health check failed: " + e.getMessage());
            System.err.println("   URL attempted: " + hrDemoExternalUrl + "/actuator/health");
            return false;
        }
    }
    
    /**
     * FIXED: Root Path Health Check with External URL
     */
    private boolean checkRootPathHealth() {
        try {
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setConnectTimeout(healthCheckTimeout);
            requestFactory.setReadTimeout(healthCheckTimeout);
            
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            
            // FIXED: Use external URL for root path check
            String healthUrl = hrDemoExternalUrl + "/";
            
            System.out.println("🔍 Checking HR Demo root path: " + healthUrl);
            
            ResponseEntity<String> response = restTemplate.getForEntity(healthUrl, String.class);
            
            boolean isHealthy = response.getStatusCode().is2xxSuccessful();
            
            if (isHealthy) {
                System.out.println("✅ HR Demo root path check passed: " + response.getStatusCode());
            } else {
                System.out.println("⚠️ HR Demo root path check failed: " + response.getStatusCode());
            }
            
            return isHealthy;
            
        } catch (Exception e) {
            System.err.println("❌ HR Demo root path check failed: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * NEW: External URL Health Check (Recommended)
     */
    private boolean checkExternalUrlHealth() {
        try {
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setConnectTimeout(healthCheckTimeout);
            requestFactory.setReadTimeout(healthCheckTimeout);
            
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            
            // Use external URL which we know works
            String healthUrl = hrDemoExternalUrl;
            
            System.out.println("🔍 Checking HR Demo external URL: " + healthUrl);
            
            ResponseEntity<String> response = restTemplate.getForEntity(healthUrl, String.class);
            
            boolean isHealthy = response.getStatusCode().is2xxSuccessful();
            
            if (isHealthy) {
                System.out.println("✅ HR Demo external URL check passed: " + response.getStatusCode());
            } else {
                System.out.println("⚠️ HR Demo external URL check failed: " + response.getStatusCode());
            }
            
            return isHealthy;
            
        } catch (Exception e) {
            System.err.println("❌ HR Demo external URL check failed: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * FIXED: Comprehensive Health Check with Multiple Strategies
     */
    private boolean checkComprehensiveHealth() {
        System.out.println("🔍 Starting comprehensive health check for HR Demo");
        
        // Method 1: External URL check (most reliable)
        try {
            if (checkExternalUrlHealth()) {
                System.out.println("✅ Comprehensive check: External URL passed");
                return true;
            }
        } catch (Exception e) {
            System.err.println("⚠️ Comprehensive check: External URL failed - " + e.getMessage());
        }
        
        // Method 2: Actuator health check
        try {
            if (checkActuatorHealth()) {
                System.out.println("✅ Comprehensive check: Actuator health passed");
                return true;
            }
        } catch (Exception e) {
            System.err.println("⚠️ Comprehensive check: Actuator health failed - " + e.getMessage());
        }
        
        // Method 3: Root path check
        try {
            if (checkRootPathHealth()) {
                System.out.println("✅ Comprehensive check: Root path passed");
                return true;
            }
        } catch (Exception e) {
            System.err.println("⚠️ Comprehensive check: Root path failed - " + e.getMessage());
        }
        
        System.err.println("❌ All comprehensive health check methods failed for HR Demo");
        return false;
    }
    
    /**
     * FIXED: REST endpoint for AJAX health check from frontend
     */
    @GetMapping("/hr-screening/status")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getHrDemoStatus() {
        boolean isHealthy = checkHrDemoHealth();
        
        Map<String, Object> status = Map.of(
            "healthy", isHealthy,
            "status", isHealthy ? "online" : "offline",
            "service", "hr-demo",
            "timestamp", System.currentTimeMillis(),
            "embedUrl", hrDemoEmbedUrl,
            "externalUrl", hrDemoExternalUrl,
            "healthStrategy", healthStrategy,
            "healthCheckEnabled", healthCheckEnabled,
            "message", isHealthy ? "HR Demo service is operational" : "HR Demo service is currently unavailable"
        );
        
        System.out.println("📊 HR Demo status requested via AJAX: " + (isHealthy ? "HEALTHY" : "UNHEALTHY"));
        
        return ResponseEntity.ok(status);
    }
    
    /**
     * FIXED: Debug endpoint with updated URLs
     */
    @GetMapping("/hr-screening/debug")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> debugHrDemoHealth() {
        Map<String, Object> debugInfo = Map.of(
            "configuration", Map.of(
                "embedUrl", hrDemoEmbedUrl,
                "externalUrl", hrDemoExternalUrl,
                "enabled", hrDemoEnabled,
                "healthCheckEnabled", healthCheckEnabled,
                "healthStrategy", healthStrategy,
                "timeout", healthCheckTimeout
            ),
            "healthChecks", Map.of(
                "externalUrl", checkExternalUrlHealth(),
                "actuatorHealth", checkActuatorHealth(),
                "rootPath", checkRootPathHealth(),
                "comprehensive", checkComprehensiveHealth()
            ),
            "testUrls", Map.of(
                "externalUrl", hrDemoExternalUrl,
                "actuatorHealthUrl", hrDemoExternalUrl + "/actuator/health",
                "rootPathUrl", hrDemoExternalUrl + "/"
            ),
            "timestamp", System.currentTimeMillis(),
            "recommendations", List.of(
                "Use external URL for iframe embedding: " + hrDemoExternalUrl,
                "Health check URL: " + hrDemoExternalUrl + "/actuator/health",
                "Recommended health strategy: external-url or actuator-health"
            )
        );
        
        return ResponseEntity.ok(debugInfo);
    }
    
    /**
     * Placeholder for future AI demo pages
     */
    @GetMapping("/{demoId}")
    public String genericDemo(Model model) {
        model.addAttribute("title", "AI Demo - Coming Soon | Somdip Roy");
        model.addAttribute("currentPage", "ai-demos");
        return "redirect:/ai-demos";
    }
}