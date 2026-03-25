package MagicFridgeAI.Service;

import MagicFridgeAI.Model.FoodItem;
import MagicFridgeAI.Repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {

    private FoodItemRepository repository;

    public FoodItemService(FoodItemRepository repository) {
        this.repository = repository;
    }

    public FoodItem salvar(FoodItem fooditem){
        return repository.save(fooditem);
    }

    public List<FoodItem>listar(){
        return repository.findAll();
    }


    public Optional<FoodItem> findbyid(Long id){


        return repository.findById(id);
    }

    //Listar por id

    // Alterar

    // DEletar


}
