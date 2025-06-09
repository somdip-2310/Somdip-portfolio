package com.somdiproy.portfolio.model;

public class Skill {
    private String name;
    private String category;
    private int proficiency; // 1-100
    private String description;
    
    public Skill() {}
    
    public Skill(String name, String category, int proficiency, String description) {
        this.name = name;
        this.category = category;
        this.proficiency = proficiency;
        this.description = description;
    }
    
    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public int getProficiency() { return proficiency; }
    public void setProficiency(int proficiency) { this.proficiency = proficiency; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getProficiencyLevel() {
        if (proficiency >= 90) return "Expert";
        if (proficiency >= 75) return "Advanced";
        if (proficiency >= 60) return "Intermediate";
        return "Beginner";
    }
}