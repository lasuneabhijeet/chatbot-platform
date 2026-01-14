package com.example.demo.service;

import com.example.demo.model.Project;
import com.example.demo.model.Prompt;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.PromptRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final PromptRepository promptRepository;

    public ProjectService(ProjectRepository projectRepository, PromptRepository promptRepository) {
        this.projectRepository = projectRepository;
        this.promptRepository = promptRepository;
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Prompt addPromptToProject(Prompt prompt) {
        return promptRepository.save(prompt);
    }
   // import java.util.Optional; // Ensure this import is at the top
 // ... other imports

 public Optional<Project> getProjectById(Long id) {
     return projectRepository.findById(id);
 }
    
}