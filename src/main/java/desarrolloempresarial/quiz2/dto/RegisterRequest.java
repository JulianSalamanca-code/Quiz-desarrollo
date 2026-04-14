package desarrolloempresarial.quiz2.dto;

import lombok.*;

@Getter @Setter
public class RegisterRequest {
    private String username;
    private String password;
    private String role;
    private String nombreChef;
    private String especialidad;
}