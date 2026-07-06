package MagicFridgeAI.DTO;

import MagicFridgeAI.enums.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodItemDTO {

    private Long id;
    private String name;
    private Categoria categoria;
    private Integer quantidade;
    private LocalDateTime validade;

}
