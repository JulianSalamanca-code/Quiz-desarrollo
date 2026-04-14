package desarrolloempresarial.quiz2.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChefDto {

    private Long id;
    private String name;
    private String specialization;
    private int age;

    private Long userId;
}