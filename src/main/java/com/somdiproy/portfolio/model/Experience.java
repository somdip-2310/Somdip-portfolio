package com.somdiproy.portfolio.model;

import java.time.LocalDate;
import java.util.List;

public class Experience {
    private String company;
    private String position;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private List<String> achievements;
    private List<String> technologies;
    
    // Constructors
    public Experience() {}
    
    public Experience(String company, String position, String location, 
                     LocalDate startDate, LocalDate endDate, String description, 
                     List<String> achievements, List<String> technologies) {
        this.company = company;
        this.position = position;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.achievements = achievements;
        this.technologies = technologies;
    }
    
    // Getters and Setters
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public List<String> getAchievements() { return achievements; }
    public void setAchievements(List<String> achievements) { this.achievements = achievements; }
    
    public List<String> getTechnologies() { return technologies; }
    public void setTechnologies(List<String> technologies) { this.technologies = technologies; }
    
    public boolean isCurrent() {
        return endDate == null;
    }
    
    public String getDuration() {
        if (endDate == null) {
            return startDate.getMonthValue() + "/" + startDate.getYear() + " - Present";
        }
        return startDate.getMonthValue() + "/" + startDate.getYear() + " - " + 
               endDate.getMonthValue() + "/" + endDate.getYear();
    }
}