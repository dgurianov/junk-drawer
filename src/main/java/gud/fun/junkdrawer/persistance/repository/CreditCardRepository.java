package gud.fun.junkdrawer.persistance.repository;

import gud.fun.junkdrawer.persistance.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditCardRepository extends JpaRepository<CreditCard, UUID> {
}
