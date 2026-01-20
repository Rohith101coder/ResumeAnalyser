// package com.example.resumeParser.ai;

// import java.util.List;
// import java.util.Map;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.http.MediaType;
// import org.springframework.stereotype.Component;
// import org.springframework.web.reactive.function.client.WebClient;

// @Component
// public class GeminiAiClient implements AiClient {

//     private static final Logger log = LoggerFactory.getLogger(GeminiAiClient.class);

//     private final WebClient webClient;
//     private final String model;
//     private final String apiKey;

//     public GeminiAiClient(
//             @Value("${GEMINI_API_KEY}") String apiKey,
//             @Value("${ai.model}") String model) {

//         this.apiKey = apiKey;
//         this.model = model;

//         this.webClient = WebClient.builder()
//                 .baseUrl("https://generativelanguage.googleapis.com/v1beta/models")
//                 .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//                 .build();

//         log.info("Using Gemini model: {}", model);
//     }

//     @Override
//     public String generate(String prompt) {

//         Map<String, Object> requestBody = Map.of(
//                 "contents", List.of(
//                         Map.of(
//                                 "parts", List.of(
//                                         Map.of("text", prompt)
//                                 )
//                         )
//                 )
//         );

//         return webClient.post()
//                 .uri(uriBuilder -> uriBuilder
//                         .path("/" + model + ":generateContent")
//                         .queryParam("key", apiKey)
//                         .build())
//                 .bodyValue(requestBody)
//                 .retrieve()
//                 .onStatus(
//                         status -> status.is4xxClientError() || status.is5xxServerError(),
//                         response -> response.bodyToMono(String.class)
//                                 .map(body -> new RuntimeException("Gemini API error: " + body))
//                 )
//                 .bodyToMono(Map.class)
//                 .map(response -> {

//                     Map<String, Object> responseMap = (Map<String, Object>) response;

//                     List<Map<String, Object>> candidates =
//                             (List<Map<String, Object>>) responseMap.get("candidates");

//                     if (candidates == null || candidates.isEmpty()) {
//                         return "No response from Gemini";
//                     }

//                     Map<String, Object> firstCandidate = candidates.get(0);
//                     Map<String, Object> content =
//                             (Map<String, Object>) firstCandidate.get("content");

//                     if (content == null) {
//                         return "Gemini returned no content";
//                     }

//                     List<Map<String, Object>> parts =
//                             (List<Map<String, Object>>) content.get("parts");

//                     if (parts == null || parts.isEmpty()) {
//                         return "Gemini returned empty content parts";
//                     }

//                     return parts.get(0).get("text").toString();
//                 })
//                 .block();
//     }
// }


// // this code is not in service