package gud.fun.junkdrawer.persistance.repository;

import gud.fun.junkdrawer.persistance.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, UUID> {
}
