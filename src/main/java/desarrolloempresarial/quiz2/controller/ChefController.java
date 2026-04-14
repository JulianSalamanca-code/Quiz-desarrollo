package desarrolloempresarial.quiz2.controller;

import desarrolloempresarial.quiz2.dto.*;
import desarrolloempresarial.quiz2.entity.User;
import desarrolloempresarial.quiz2.repository.UserRepository;
import desarrolloempresarial.quiz2.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chef")
@RequiredArgsConstructor
public class ChefController {

    private final UserRepository userRepository;
    private final ChefService chefService;
    private final KitchenService kitchenService;


    @GetMapping("/me")
    public ChefDto getMyProfile(Authentication auth) {

        User user = userRepository.findByUsername(auth.getName())
                .orElseThrow();

        return chefService.getChefByUser(user.getId());
    }


    @GetMapping("/kitchens")
    public List<KitchenDto> getMyKitchens(Authentication auth) {

        User user = userRepository.findByUsername(auth.getName())
                .orElseThrow();

        ChefDto chef = chefService.getChefByUser(user.getId());

        return kitchenService.getKitchensByChef(chef.getId());
    }
}