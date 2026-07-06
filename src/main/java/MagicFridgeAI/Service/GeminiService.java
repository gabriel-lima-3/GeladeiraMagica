package MagicFridgeAI.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class GeminiService {
    private final FoodItemService foodItemService;
    private final WebClient webClient;
    private final String apiKey = System.getenv("GEMINI_API_KEY");

    private static final String MODEL = "gemini-2.5-flash";
    private static final String SYSTEM_PROMPT =
            "Voce e um assistente culinario. Sugira receitas simples e praticase com os ingredientes fornecidos.";

    public GeminiService(WebClient webClient, FoodItemService foodItemService) {
        this.webClient = webClient;
        this.foodItemService = foodItemService;
    }

    public Mono<String> generateRecipe() {

        String igredientes = foodItemService.listar()
                .stream()
                .map(item -> item.getName() + " (quantidade: " + item.getQuantidade() + ")")
                .collect(Collectors.joining(", "));


        String prompt = SYSTEM_PROMPT + "\n\nBaseado no meu banco de dados me sugira uma receita com os seguintes items " + igredientes;

        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)
                        ))
                )
        );

        return webClient.post()
                .uri("/v1beta/models/" + MODEL + ":generateContent?key=" + apiKey)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode root = mapper.readTree(response);
                        return root
                                .path("candidates")
                                .get(0)
                                .path("content")
                                .path("parts")
                                .get(0)
                                .path("text")
                                .asText();
                    } catch (Exception e) {
                        return "Erro ao processar resposta do Gemini";
                    }
                });
    }
}
