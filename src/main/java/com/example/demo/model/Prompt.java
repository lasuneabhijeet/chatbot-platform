package com.example.demo.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Prompt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String content;

    // This creates the Foreign Key 'project_id' in your MySQL 'prompt' table
    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonBackReference // Prevents infinite JSON loops during testing
    private Project project;

    // --- Standard Getters and Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    // THIS IS THE METHOD THE CONTROLLER IS LOOKING FOR
    public Project getProject() { return project; }
    public void setProject(Project project) {
        this.project = project;
    }
}