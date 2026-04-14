package desarrolloempresarial.quiz2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Kitchen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String restaurantName;
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "chef_id")
    private Chef chef;
}