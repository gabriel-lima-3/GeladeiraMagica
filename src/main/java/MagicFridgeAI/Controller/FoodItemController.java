package MagicFridgeAI.Controller;


import MagicFridgeAI.model.FoodItem;
import MagicFridgeAI.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
public class FoodItemController {


    private FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }



    //POST
    @PostMapping("/criar")
    public ResponseEntity<FoodItem> criar (@RequestBody FoodItem fooditem){



    }


}
