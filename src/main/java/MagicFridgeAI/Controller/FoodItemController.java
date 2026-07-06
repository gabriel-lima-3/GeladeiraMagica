package MagicFridgeAI.Controller;

import MagicFridgeAI.DTO.FoodItemDTO;
import MagicFridgeAI.Service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private FoodItemService service;

    public FoodItemController(FoodItemService foodItemService) {
        this.service = foodItemService;
    }

    // Controller só fala DTO, Service faz o resto
    @PostMapping("/criar")
    public ResponseEntity<FoodItemDTO> criar(@RequestBody FoodItemDTO dto) {
        FoodItemDTO salvo = service.salvar(dto);
        return ResponseEntity.status(201).body(salvo);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<FoodItemDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<FoodItemDTO> listarPorId(@PathVariable Long id) {
        return service.findbyid(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Recebe DTO no body, id na URL, devolve o item atualizado
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<FoodItemDTO> atualizar(@PathVariable Long id,
                                                 @RequestBody FoodItemDTO dto) {
        return service.atualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE não tem body, só o id na URL
    // 204 No Content = deletou com sucesso, sem nada pra retornar
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
