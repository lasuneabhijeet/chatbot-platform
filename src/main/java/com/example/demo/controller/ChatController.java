package com.example.demo.controller;

import com.example.demo.service.ChatService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/{projectId}")
    public String askChatbot(@PathVariable Long projectId, @RequestBody Map<String, String> request) {
        String userMsg = request.get("message");
        
        // For now, let's use a hardcoded prompt. 
        // Later, you can fetch this from your PromptRepository using projectId
        String systemPrompt = "You are a helpful assistant."; 
        
        return chatService.getChatResponse(systemPrompt, userMsg);
    }
}