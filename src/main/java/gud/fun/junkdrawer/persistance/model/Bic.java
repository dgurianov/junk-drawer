package gud.fun.junkdrawer.persistance.model;

import gud.fun.junkdrawer.persistance.model.deleteorder.EntityLevelTwo;
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
@Table(name = "BIC")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Bic extends EntityLevelTwo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String identifier;

    private String institution;
}
