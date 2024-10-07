package gud.fun.junkdrawer.persistance.repository;

import gud.fun.junkdrawer.persistance.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByCountryCode(String countryCode);
}
