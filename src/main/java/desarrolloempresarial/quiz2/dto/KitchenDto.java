package desarrolloempresarial.quiz2.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KitchenDto {

    private Long id;
    private String restaurantName;
    private int capacity;

    private Long chefId;
}
