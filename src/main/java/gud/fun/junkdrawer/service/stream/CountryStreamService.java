package gud.fun.junkdrawer.service.stream;

import gud.fun.junkdrawer.dto.country.CountryResponseDto;
import gud.fun.junkdrawer.persistance.model.Country;
import gud.fun.junkdrawer.util.generator.CountryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CountryStreamService implements JunkStreamService<CountryResponseDto> {

    @Autowired
    CountryGenerator countryGenerator;

    private List<CountryResponseDto> responseDtos = new ArrayList<>();
    private Country country;

    @Override
    public List<CountryResponseDto> getAllStream(int limit) {
        if(responseDtos.size() > 0) responseDtos.clear();
        while(limit > 0) {
            country = countryGenerator.generateRandom();

            responseDtos.add(
                    CountryResponseDto.builder()
                            .id(country.getId())
                            .name(country.getName())
                            .countryCode(country.getCountryCode())
                            .build()
            );
            limit--;
        }

        return responseDtos;
    }


}
