package sasa.endpoints;

import com.sasa.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import sasa.repositories.CityRepository;

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
        response.setCity(cityRepository.findCity(request.getName()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "setCityRequest")
    @ResponsePayload
    public SetCityResponse setCountry(@RequestPayload SetCityRequest request) {
        SetCityResponse response = new SetCityResponse();
        response.setCity(cityRepository.addCity(request.getCity()));
        return response;
    }
}
