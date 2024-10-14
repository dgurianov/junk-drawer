package gud.fun.junkdrawer.persistance.repository;

import gud.fun.junkdrawer.persistance.model.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
