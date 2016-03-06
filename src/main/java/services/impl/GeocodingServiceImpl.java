package services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import services.interfaces.GeocodingService;
import services.model.jackson.GeocodingApiResponse;

import javax.ws.rs.BadRequestException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class GeocodingServiceImpl implements GeocodingService {
    public static final String GEOCODING_ENDPOINT = "https://maps.googleapis.com/maps/api/geocode/json?";
    public static final String API_KEY = "AIzaSyCqY_GY36jbVcoCF8m-SNNqgLjKizIf7rQ";

    public GeocodingApiResponse getGeocodingData(final String address) {
        try{
            final Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("address", address);
            paramMap.put("key", API_KEY);
            final Content content = Request.Get(addQueryParams(paramMap)).execute().returnContent();
            GeocodingApiResponse geocodingApiResponse = new ObjectMapper().readValue(content.asStream(), GeocodingApiResponse.class);
            return geocodingApiResponse;
        } catch (IOException e) {
            throw new BadRequestException("Can't make request");
        }
    }

    private String addQueryParams(final Map<String, String> paramMap) {
        String parameterizedEndpoint = GEOCODING_ENDPOINT;
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            try {
                parameterizedEndpoint = parameterizedEndpoint + "&" + entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("unexpected encoding");
            }
        }
        return parameterizedEndpoint;
    }
}
