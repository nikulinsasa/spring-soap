package sasa.endpoints;

import com.sasa.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import sasa.persistence.repositories.CityRepository;


@Endpoint
public class CityEndpoint {
    private static final String NAMESPACE_URI = "http://sasa.com/services";

    private CityRepository cityRepository;

    @Autowired
    public CityEndpoint(CityRepository countryRepository) {
        this.cityRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCityRequest")
    @ResponsePayload
    public GetCityResponse getCity(@RequestPayload GetCityRequest request) {
        GetCityResponse response = new GetCityResponse();
        sasa.persistence.entities.City city = cityRepository.findByName(request.getName());

        City answerCity = new City();
        answerCity.setName(city.getName());
        answerCity.setCountry(city.getCountry());
        answerCity.setPopulation(city.getPopulation());
        answerCity.setTimeZoneUTC(city.getTimeZone());
        response.setCity(answerCity);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "setCityRequest")
    @ResponsePayload
    public SetCityResponse setCountry(@RequestPayload SetCityRequest request) {
        SetCityResponse response = new SetCityResponse();

        City city = request.getCity();

        sasa.persistence.entities.City savedCity = cityRepository.findByName(city.getName());
        if(savedCity==null) {
            savedCity = new sasa.persistence.entities.City();
            savedCity.setName(city.getName());
            savedCity.setCountry(city.getCountry());
            savedCity.setPopulation(city.getPopulation());
            savedCity.setTimeZone(city.getTimeZoneUTC());
        }
        cityRepository.save(savedCity);
        response.setCity(city);

        response.setCity(request.getCity());
        return response;
    }
}
