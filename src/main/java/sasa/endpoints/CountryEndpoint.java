package sasa.endpoints;

import com.sasa.services.GetCountryRequest;
import com.sasa.services.GetCountryResponse;
import com.sasa.services.SetCountryRequest;
import com.sasa.services.SetCountryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import sasa.repositories.CountryRepository;

@Endpoint
public class CountryEndpoint {

    private static final String NAMESPACE_URI = "http://sasa.com/services";

    private CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "setCountryRequest")
    @ResponsePayload
    public SetCountryResponse setCountry(@RequestPayload SetCountryRequest request) {
        SetCountryResponse response = new SetCountryResponse();
        response.setCountry(countryRepository.addCountry(request.getCountry()));
        return response;
    }


}
