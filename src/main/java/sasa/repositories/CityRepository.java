package sasa.repositories;

import com.sasa.services.City;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class CityRepository {

    private static final Map<String, City> cities = new HashMap<>();

    @PostConstruct
    public void initData() {
        City moscow = new City();
        moscow.setCountry("RU");
        moscow.setName("Moscow");
        moscow.setPopulation(8000000);
        moscow.setTimeZoneUTC(3);
        cities.put(moscow.getName(),moscow);


        City london = new City();
        london.setCountry("UK");
        london.setTimeZoneUTC(0);
        london.setPopulation(10000000);
        london.setName("London");
        cities.put(london.getName(),london);

    }

    public City findCity(String name){
        return cities.get(name);
    }

    public City addCity(City city){
        cities.put(city.getName(),city);

        return city;
    }

}
