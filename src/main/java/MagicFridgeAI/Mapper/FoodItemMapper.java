package MagicFridgeAI.Mapper;


import MagicFridgeAI.DTO.FoodItemDTO;
import MagicFridgeAI.Model.FoodItem;

public class FoodItemMapper {


    public static FoodItemDTO toDTO(FoodItem fooditem){

        return new FoodItemDTO(
                fooditem.getId(),
                fooditem.getNome(),
                fooditem.getCategoria(),
                fooditem.getQuantidade(),
                fooditem.getValidade()

        );
    }

    public static FoodItem toEntity(FoodItemDTO dto){
        FoodItem fooditem = new FoodItem();
        fooditem.setId(dto.getId());
        fooditem.setNome(dto.getName());
        fooditem.setCategoria(dto.getCategoria());
        fooditem.setQuantidade(dto.getQuantidade());
        fooditem.setValidade(dto.getValidade());
        return fooditem;

    }




}
