package gud.fun.junkdrawer.persistance.model;

import gud.fun.junkdrawer.persistance.model.deleteorder.EntityLevelParent;
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
@Table(name = "PHONE_NUMBER")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PhoneNumber extends EntityLevelParent {
    public PhoneNumber(String phoneNumber, String countryCode) {
        this.phoneNumber = phoneNumber;
        this.countryCode = countryCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String phoneNumber;
    private String countryCode;

}
