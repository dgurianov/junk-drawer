package gud.fun.junkdrawer.persistance.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "TRANSACTION")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Date dateTime;

    private TransactionEntryType entryType;

    private TransactionType type;

    @OneToOne(cascade = CascadeType.ALL)
    private Merchant merchant;

    private Long amount;

    private String currency;

    @OneToOne(cascade = CascadeType.ALL)
    private CreditCard creditCard;

}
