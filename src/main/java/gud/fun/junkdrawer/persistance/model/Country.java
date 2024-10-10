package gud.fun.junkdrawer.persistance.model;

import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "COUNTRY")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Country {

    public Country(CountryCode code){
        this.countryCode = code.getAlpha2();
        this.name = code.getName();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String countryCode;

    @OneToMany
    @JoinColumn(name="CITY_ID")
    private List<City> cities = new ArrayList<>();
}
