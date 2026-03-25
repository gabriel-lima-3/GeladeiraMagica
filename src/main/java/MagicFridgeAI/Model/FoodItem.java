package MagicFridgeAI.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "Food_Item")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String categoria;
    private Integer quantidade;
    private LocalDateTime validade;




}
