package gud.fun.junkdrawer.persistance.model;

import gud.fun.junkdrawer.persistance.model.deleteorder.EntityLevelParent;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "TRANSACTION")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction extends EntityLevelParent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID correlationId;

    private Date dateTime;

    private TransactionEntryType entryType;

    private TransactionState state;

    private TransactionType type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchant;

    private BigDecimal amount;

    private String currency;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cc_id", referencedColumnName = "id")
    private CreditCard creditCard;

}
