package MagicFridgeAI.Service;

import MagicFridgeAI.DTO.FoodItemDTO;
import MagicFridgeAI.Mapper.FoodItemMapper;
import MagicFridgeAI.Model.FoodItem;
import MagicFridgeAI.Repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {


    // Trocar Model para DTO
    private FoodItemRepository repository;

    public FoodItemService(FoodItemRepository repository) {
        this.repository = repository;
    }

    // Recebe DTO, converte pra Entity, salva, devolve DTO
    public FoodItemDTO salvar(FoodItemDTO dto) {
        FoodItem entity = FoodItemMapper.toEntity(dto);
        FoodItem salvo = repository.save(entity);
        return FoodItemMapper.toDTO(salvo);
    }

    // findAll() retorna List<FoodItem>, então mapeamos cada item pra DTO
    public List<FoodItemDTO> listar() {
        return repository.findAll()
                .stream()
                .map(FoodItemMapper::toDTO)
                .toList();
    }

    // findById retorna Optional<FoodItem>, mapeamos pra Optional<FoodItemDTO>
    public Optional<FoodItemDTO> findbyid(Long id) {
        return repository.findById(id)
                .map(FoodItemMapper::toDTO);
    }

    // Busca o item, atualiza os campos, salva, devolve DTO
    public Optional<FoodItemDTO> atualizar(Long id, FoodItemDTO dto) {
        return repository.findById(id).map(entity -> {
            entity.setNome(dto.getName());
            entity.setCategoria(dto.getCategoria());
            entity.setQuantidade(dto.getQuantidade());
            FoodItem atualizado = repository.save(entity);
            return FoodItemMapper.toDTO(atualizado);
        });
    }

    // Retorna true se deletou, false se não achou o id
    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }


}
