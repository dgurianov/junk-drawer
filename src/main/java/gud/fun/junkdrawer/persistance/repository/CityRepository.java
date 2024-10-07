package gud.fun.junkdrawer.persistance.repository;

import gud.fun.junkdrawer.persistance.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findAllByCountryCode(String countryCode);
}