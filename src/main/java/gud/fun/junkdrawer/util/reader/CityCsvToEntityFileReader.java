package gud.fun.junkdrawer.util.reader;

import gud.fun.junkdrawer.persistance.model.City;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CityCsvToEntityFileReader implements CsvFileReader<City> {

    @Override
    public List<City> read() {
        List<City> cities = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/stub/cities.csv")))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                City city = new City();
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
