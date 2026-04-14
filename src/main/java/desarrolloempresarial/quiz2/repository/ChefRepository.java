package desarrolloempresarial.quiz2.repository;

import desarrolloempresarial.quiz2.entity.Chef;
import desarrolloempresarial.quiz2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChefRepository extends JpaRepository<Chef, Long> {
    Optional<Chef> findByUser(User user);
}
