package gud.fun.junkdrawer.persistance.repository;

import gud.fun.junkdrawer.persistance.model.Bic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BicRepository extends JpaRepository<Bic, UUID> {
}
