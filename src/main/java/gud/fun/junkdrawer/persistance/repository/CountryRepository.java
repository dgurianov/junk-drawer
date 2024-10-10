package gud.fun.junkdrawer.persistance.repository;

import gud.fun.junkdrawer.persistance.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID> {
    Country findByCountryCode(String countryCode);
}
