package desarrolloempresarial.quiz2.controller;


import desarrolloempresarial.quiz2.dto.*;
import desarrolloempresarial.quiz2.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final ChefService chefService;
    private final KitchenService kitchenService;


    @PostMapping("/users")
    public UserDto createUser(@RequestBody UserDto dto) {
        return userService.createUser(dto);
    }


    @PostMapping("/chefs")
    public ChefDto createChef(@RequestBody ChefDto dto) {
        return chefService.createChef(dto);
    }



    @PostMapping("/kitchens")
    public KitchenDto createKitchen(@RequestBody KitchenDto dto) {
        return kitchenService.createKitchen(dto);
    }

    @GetMapping("/kitchens/{chefId}")
    public List<KitchenDto> getKitchensByChef(@PathVariable Long chefId) {
        return kitchenService.getKitchensByChef(chefId);
    }

    @DeleteMapping("/kitchens/{id}")
    public String deleteKitchen(@PathVariable Long id) {
        kitchenService.deleteKitchen(id);
        return "Kitchen eliminada";
    }
}