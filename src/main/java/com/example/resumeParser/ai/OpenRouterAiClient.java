package com.example.resumeParser.ai;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Primary
@Component
public class OpenRouterAiClient implements AiClient{
    private final WebClient webClient;
    private final String model;

    public OpenRouterAiClient(
            @Value("${OPENROUTER_API_KEY}") String apiKey,
            @Value("${ai.model}") String model) {

        this.model = model;

        this.webClient = WebClient.builder()
                .baseUrl("https://openrouter.ai/api/v1")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                // REQUIRED by OpenRouter
                .defaultHeader("HTTP-Referer", "http://localhost:8080")
                .defaultHeader("X-Title", "Resume Parser AI")
                .build();
    }

//     @Override
//     public String generate(String prompt) {

//         Map<String, Object> requestBody = Map.of(
//                 "model", model,
//                 "messages", List.of(
//                         Map.of(
//                                 "role", "user",
//                                 "content", prompt
//                         )
//                 )
//         );

//         return webClient.post()
//                 .uri("/chat/completions")
//                 .bodyValue(requestBody)
//                 .retrieve()
//                 .onStatus(
//                         status -> status.is4xxClientError() || status.is5xxServerError(),
//                         response -> response.bodyToMono(String.class)
//                                 .map(body -> new RuntimeException(
//                                         "OpenRouter API error: " + body))
//                 )
//                 .bodyToMono(Map.class)
//                 .map(response -> {

//                     var choices = (List<?>) response.get("choices");
//                     if (choices == null || choices.isEmpty()) {
//                         return "No response from OpenRouter";
//                     }

//                     var firstChoice = (Map<?, ?>) choices.get(0);
//                     var message = (Map<?, ?>) firstChoice.get("message");

//                     return message.get("content").toString();
//                 })
//                 .block();
//     }
@Override
public String generate(String prompt) {

    Map<String, Object> requestBody = Map.of(
    "model", model,
    "response_format", Map.of("type", "json_object"),
    "messages", List.of(
        Map.of(
            "role", "user",
            "content", prompt
        )
    )
);


    return webClient.post()
            .uri("/chat/completions")
            .bodyValue(requestBody)
            .retrieve()
            .onStatus(
                    status -> status.is4xxClientError() || status.is5xxServerError(),
                    response -> response.bodyToMono(String.class)
                            .map(body -> new RuntimeException(
                                    "OpenRouter API error: " + body))
            )
            .bodyToMono(Map.class)
            .map(response -> {

                var choices = (List<?>) response.get("choices");
                if (choices == null || choices.isEmpty()) {
                    throw new RuntimeException("No choices returned from OpenRouter");
                }

                var firstChoice = (Map<?, ?>) choices.get(0);
                var message = (Map<?, ?>) firstChoice.get("message");

                String content = message.get("content").toString().trim();

                // 🚨 Guard: ensure JSON
                if (!content.startsWith("{")) {
                    throw new RuntimeException(
                        "AI did not return valid JSON. Response was: " + content
                    );
                }

                return content;
            })
            .block();
}

}
