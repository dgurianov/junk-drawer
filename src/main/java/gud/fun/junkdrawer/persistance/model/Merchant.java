package gud.fun.junkdrawer.persistance.model;

import com.neovisionaries.i18n.CountryCode;
import gud.fun.junkdrawer.persistance.model.deleteorder.EntityLevelOne;
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
@Table(name = "MERCHANT")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Merchant extends EntityLevelOne {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private CountryCode country;

    private String address;

}
