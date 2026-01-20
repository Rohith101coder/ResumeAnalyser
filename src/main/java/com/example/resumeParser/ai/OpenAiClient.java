package com.example.resumeParser.ai;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;




@Component
public class OpenAiClient implements AiClient {

    private final WebClient webClient;
    private final String model;

    public OpenAiClient(
            @Value("${OPENAI_API_KEY}") String apiKey,
            @Value("${ai.model}") String model) {
                System.out.println("OPENAI KEY LOADED = " + apiKey);


        this.model = model;

        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public String generate(String prompt) {

        Map<String, Object> requestBody = Map.of(
                "model", model,
                "messages", new Object[]{
                        Map.of("role", "user", "content", prompt)
                }
        );

        return webClient.post()
                .uri("/chat/completions")
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        response -> response.bodyToMono(String.class)
                                .map(body -> new RuntimeException("OpenAI API error: " + body))
                )
                .bodyToMono(Map.class)
                .map(response -> {
                    var choices = (java.util.List<?>) response.get("choices");
                    if (choices == null || choices.isEmpty()) {
                        return "No choices returned by AI";
                    }

                    var firstChoice = (Map<?, ?>) choices.get(0);
                    var message = (Map<?, ?>) firstChoice.get("message");
                    return message != null ? message.get("content").toString()
                                           : "Message missing in AI response";
                })
                .block();
    }
}
