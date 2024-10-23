package gud.fun.junkdrawer.util.reader;

import gud.fun.junkdrawer.dto.city.CityResponseDto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CityCsvToDtoFileReader implements CsvFileReader<CityResponseDto> {

    @Override
    public List<CityResponseDto> read() {
        List<CityResponseDto> cities = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/stub/cities.csv")))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                CityResponseDto city = new CityResponseDto();
                city.setName(values[0]);
                city.setCountryCode(values[1]);
                cities.add(city);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cities;
    }
}
