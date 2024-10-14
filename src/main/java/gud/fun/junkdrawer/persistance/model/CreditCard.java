package gud.fun.junkdrawer.persistance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "CREDIT_CARD")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreditCard{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String Ccn;

    private String issuer;
}
