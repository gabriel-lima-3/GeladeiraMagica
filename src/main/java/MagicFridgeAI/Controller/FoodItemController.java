package MagicFridgeAI.Controller;
import MagicFridgeAI.Model.FoodItem;
import MagicFridgeAI.Service.FoodItemService;
import org.hibernate.type.internal.UserTypeVersionJavaTypeWrapper;
import org.hibernate.usertype.UserVersionType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/food")
public class FoodItemController {


    private FoodItemService service;

    public FoodItemController(FoodItemService foodItemService) {
        this.service = foodItemService;
    }

    //POST
    @PostMapping("/criar")
    public ResponseEntity<FoodItem> criar (@RequestBody FoodItem fooditem){

        FoodItem salvo = service.salvar(fooditem);
        return ResponseEntity.ok(salvo);

    }

    //GET

    @GetMapping("/listar")
    public ResponseEntity<List<FoodItem>>listar(){

       List<FoodItem> listar = service.listar();
        return ResponseEntity.ok(listar);

    }

    //GET BY ID
    @GetMapping("/listar/{id}")
    public ResponseEntity<FoodItem> listarPorID(@PathVariable  Long id){

        Optional<FoodItem> user = service.findbyid(id);
        return user.map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());

    }







}
