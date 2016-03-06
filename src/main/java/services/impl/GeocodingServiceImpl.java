package services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import services.exceptions.GoogleApiException;
import services.interfaces.GeocodingService;
import services.model.jackson.geocoding.GeocodingApiResponse;
import services.utils.HttpRequestUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GeocodingServiceImpl implements GeocodingService {
    public static final String GEOCODING_ENDPOINT = "https://maps.googleapis.com/maps/api/geocode/json?";
    public static final String API_KEY = "AIzaSyCqY_GY36jbVcoCF8m-SNNqgLjKizIf7rQ";

    public GeocodingApiResponse getGeocodingData(final String address) throws GoogleApiException{
        try{
            final Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("address", address);
            paramMap.put("key", API_KEY);
            final Content content = Request.Get(HttpRequestUtil.addQueryParams(GEOCODING_ENDPOINT, paramMap)).execute().returnContent();
            return new ObjectMapper().readValue(content.asStream(), GeocodingApiResponse.class);
        } catch (IOException e) {
            throw new GoogleApiException("Can't make request");
        }
    }
}
