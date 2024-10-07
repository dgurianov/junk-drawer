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

@Entity
@Table(name = "COUNTRY")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Country {

    public Country(CountryCode code){
        this.countryCode = code.getAlpha2();
        this.countryName = code.getName();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String countryName;
    private String countryCode;

    @OneToMany
    @JoinColumn(name="CITY_ID")
    private List<City> cities = new ArrayList<>();
}
