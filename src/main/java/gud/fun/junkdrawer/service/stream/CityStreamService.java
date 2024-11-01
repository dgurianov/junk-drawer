package gud.fun.junkdrawer.service.stream;

import gud.fun.junkdrawer.dto.city.CityResponseDto;
import gud.fun.junkdrawer.persistance.model.City;
import gud.fun.junkdrawer.util.generator.CityGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CityStreamService implements JunkStreamService<CityResponseDto> {

    @Autowired
    CityGenerator cityGenerator;

    private List<CityResponseDto> responseDtos = new ArrayList<>();
    private City city;

    @Override
    public List<CityResponseDto> getAllStream(int limit) {
        if(responseDtos.size() > 0) responseDtos.clear();
        while(limit > 0) {
            city = cityGenerator.generateRandom();

            responseDtos.add(
                    CityResponseDto.builder()
                            .id(city.getId())
                            .name(city.getName())
                            .countryCode(city.getCountryCode())
                            .build()
            );
            limit--;
        }

        return responseDtos;
    }


}
