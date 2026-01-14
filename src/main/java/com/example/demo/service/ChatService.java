package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatService {

    @Value("${openai.api.key}")
    private String apiKey; // This must be your OpenRouter API Key

    private final String API_URL = "https://openrouter.ai/api/v1/chat/completions";
    private final RestTemplate restTemplate = new RestTemplate();

    public String getChatResponse(String systemPrompt, String userMessage) {
        // 1. Set up Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        
        // OpenRouter requires these for identification
        headers.set("HTTP-Referer", "http://localhost:9090"); 
        headers.set("X-Title", "AI_Chatbot_Project");

        // 2. Build Request Body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "google/gemini-2.0-flash-exp:free");

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "system", "content", systemPrompt));
        messages.add(Map.of("role", "user", "content", userMessage));

        requestBody.put("messages", messages);

        // 3. Execute Request
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        
        try {
            Map<String, Object> response = restTemplate.postForObject(API_URL, entity, Map.class);
            
            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
            Map<String, Object> firstChoice = choices.get(0);
            Map<String, String> message = (Map<String, String>) firstChoice.get("message");
            
            return message.get("content");
        } catch (Exception e) {
            return "Error calling AI: " + e.getMessage();
        }
    }
}