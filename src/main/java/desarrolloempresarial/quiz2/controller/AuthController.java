package desarrolloempresarial.quiz2.controller;



import desarrolloempresarial.quiz2.dto.AuthRequest;
import desarrolloempresarial.quiz2.dto.RegisterRequest;
import desarrolloempresarial.quiz2.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private AuthenticationManager authManager;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private UserRepository userRepo;
    @Autowired private ChefRepository chefRepo;
    @Autowired private PasswordEncoder encoder;

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        return jwtUtil.generateToken(request.getUsername());
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword();
        user.setRole(Role.vauleOf(request.getRole().toUpperCase()));

        if (user.getRole() == Role.CHEF) {
            Chef chef = new Chef();
            chef.setNombre(request.getNombreChef());
            chef.setEspecialidad(request.getEspecialidad());
            chef.setUser(user);
            user.setChef(chef);
            chefRepo.save(chef);
        }

        userRepo.save(user);


        return jwtUtil.generateToken(user.getUsername());
    }
}