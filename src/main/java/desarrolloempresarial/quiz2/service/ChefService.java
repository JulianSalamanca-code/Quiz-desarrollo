package desarrolloempresarial.quiz2.service;

import desarrolloempresarial.quiz2.dto.ChefDto;
import desarrolloempresarial.quiz2.entity.*;
import desarrolloempresarial.quiz2.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChefService {

    private final ChefRepository chefRepository;
    private final UserRepository userRepository;

    public ChefDto createChef(ChefDto dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User no encontrado"));

        Chef chef = Chef.builder()
                .name(dto.getName())
                .specialization(dto.getSpecialization())
                .age(dto.getAge())
                .user(user)
                .build();

        chef = chefRepository.save(chef);

        return mapToDto(chef);
    }

    public ChefDto getChefByUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow();

        Chef chef = chefRepository.findByUser(user)
                .orElseThrow();

        return mapToDto(chef);
    }

    // 🔁 mapper
    private ChefDto mapToDto(Chef chef) {
        return ChefDto.builder()
                .id(chef.getId())
                .name(chef.getName())
                .specialization(chef.getSpecialization())
                .age(chef.getAge())
                .userId(chef.getUser().getId())
                .build();
    }
}