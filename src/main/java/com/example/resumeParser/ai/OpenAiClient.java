package com.example.resumeParser.ai;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.resumeParser.config.AiProperties;


@Component
public class OpenAiClient implements AiClient{
    private final WebClient webClient;
    private final AiProperties aiProperties;

    public OpenAiClient(AiProperties aiProperties) {
        this.aiProperties = aiProperties;
        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1")
                .defaultHeader(HttpHeaders.AUTHORIZATION,
                        "Bearer " + aiProperties.getApiKey())
                .defaultHeader(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

     @Override
    public String generate(String prompt) {

        Map<String, Object> requestBody = Map.of(
                "model", aiProperties.getModel(),
                "messages", new Object[]{
                        Map.of("role", "user", "content", prompt)
                }
        );

        return webClient.post()
                .uri("/chat/completions")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response ->
                        ((Map<?, ?>)
                                ((java.util.List<?>)
                                        response.get("choices")).get(0))
                                .get("message")
                                .toString()
                )
                .block();
    }
}
