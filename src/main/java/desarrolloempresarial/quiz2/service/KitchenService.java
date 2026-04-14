package desarrolloempresarial.quiz2.service;

import desarrolloempresarial.quiz2.dto.KitchenDto;
import desarrolloempresarial.quiz2.entity.*;
import desarrolloempresarial.quiz2.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KitchenService {

    private final KitchenRepository kitchenRepository;
    private final ChefRepository chefRepository;

    public KitchenDto createKitchen(KitchenDto dto) {

        Chef chef = chefRepository.findById(dto.getChefId())
                .orElseThrow(() -> new RuntimeException("Chef no encontrado"));

        Kitchen kitchen = Kitchen.builder()
                .restaurantName(dto.getRestaurantName())
                .capacity(dto.getCapacity())
                .chef(chef)
                .build();

        kitchen = kitchenRepository.save(kitchen);

        return mapToDto(kitchen);
    }

    public List<KitchenDto> getKitchensByChef(Long chefId) {

        Chef chef = chefRepository.findById(chefId)
                .orElseThrow();

        return kitchenRepository.findByChef(chef)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // 🔁 mapper
    private KitchenDto mapToDto(Kitchen kitchen) {
        return KitchenDto.builder()
                .id(kitchen.getId())
                .restaurantName(kitchen.getRestaurantName())
                .capacity(kitchen.getCapacity())
                .chefId(kitchen.getChef().getId())
                .build();
    }

    public void deleteKitchen(Long id) {
        kitchenRepository.deleteById(id);
    }
}