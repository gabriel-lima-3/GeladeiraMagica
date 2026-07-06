package MagicFridgeAI.Controller;
import MagicFridgeAI.Model.FoodItem;
import MagicFridgeAI.Service.FoodItemService;
import MagicFridgeAI.Service.GeminiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final FoodItemService service;
    private final GeminiService geminiService;

    public RecipeController(GeminiService geminiService, FoodItemService service) {
        this.geminiService = geminiService;
        this.service = service;
    }

    @GetMapping("/sugerir")
    public Mono<ResponseEntity<String>> generateRecipe() {
        return geminiService.generateRecipe()
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

}