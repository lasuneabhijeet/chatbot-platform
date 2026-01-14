package com.example.demo.controller;

import com.example.demo.model.Project;
import com.example.demo.model.Prompt;
import com.example.demo.repository.PromptRepository;
import com.example.demo.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final PromptRepository promptRepository; // Add this

    // Update constructor to include PromptRepository
    public ProjectController(ProjectService projectService, PromptRepository promptRepository) {
        this.projectService = projectService;
        this.promptRepository = promptRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.createProject(project));
    }

    // THIS FIXES THE 404 ERROR
    @PostMapping("/{projectId}/prompts")
    public ResponseEntity<?> addPrompt(@PathVariable Long projectId, @RequestBody Prompt prompt) {
        return projectService.getProjectById(projectId).map(project -> {
            prompt.setProject(project); // Links the prompt to your project
            promptRepository.save(prompt);
            return ResponseEntity.ok("Prompt saved successfully to project " + projectId);
        }).orElse(ResponseEntity.notFound().build());
    }
}