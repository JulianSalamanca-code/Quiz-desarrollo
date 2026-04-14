package desarrolloempresarial.quiz2.controller;

import desarrolloempresarial.quiz2.dto.AuthRequest;
import desarrolloempresarial.quiz2.dto.RegisterRequest;
import desarrolloempresarial.quiz2.dto.UserDto;
import desarrolloempresarial.quiz2.entity.Chef;
import desarrolloempresarial.quiz2.entity.Role;
import desarrolloempresarial.quiz2.entity.User;
import desarrolloempresarial.quiz2.repository.ChefRepository;
import desarrolloempresarial.quiz2.repository.UserRepository;
import desarrolloempresarial.quiz2.security.JwtUtil;
import desarrolloempresarial.quiz2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepo;
    private final ChefRepository chefRepo;
    private final PasswordEncoder encoder;
    private final UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        return jwtUtil.generateToken(request.getUsername());
    }

    @PostMapping("/register")
    public UserDto register(@RequestBody RegisterRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole().toUpperCase()))
                .build();


        if (user.getRole() == Role.CHEF) {
            Chef chef = new Chef();
            chef.setName(request.getNameChef());
            chef.setSpecialization(request.getSpecialization());
            chef.setUser(user);
            user.setChef(chef);
            chefRepo.save(chef);
        }

        userRepo.save(user);


        return userService.findByUsername(user.getUsername());
    }
}
