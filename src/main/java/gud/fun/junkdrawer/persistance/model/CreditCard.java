package gud.fun.junkdrawer.persistance.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bic_id", referencedColumnName = "id")
    private Bic bic;
}
