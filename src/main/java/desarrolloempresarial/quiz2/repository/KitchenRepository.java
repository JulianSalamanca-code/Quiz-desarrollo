package desarrolloempresarial.quiz2.repository;

import desarrolloempresarial.quiz2.entity.Kitchen;
import desarrolloempresarial.quiz2.entity.Chef;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KitchenRepository extends JpaRepository<Kitchen, Long> {
    List<Kitchen> findByChef(Chef chef);
}