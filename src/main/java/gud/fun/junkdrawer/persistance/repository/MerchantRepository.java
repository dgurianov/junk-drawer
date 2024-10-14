package gud.fun.junkdrawer.persistance.repository;

import gud.fun.junkdrawer.persistance.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
}
