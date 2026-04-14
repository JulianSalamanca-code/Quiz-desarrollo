package desarrolloempresarial.quiz2.dto;

import desarrolloempresarial.quiz2.entity.Role;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String username;
    private String password;
    private Role role;
}
