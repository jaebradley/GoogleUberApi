package services.impl;

import org.junit.Test;
import services.exceptions.GoogleApiException;

public class GeocodingServiceImplTest {
    private final GeocodingServiceImpl geocodingService = new GeocodingServiceImpl();

    @Test
    public void testGeocodingEndpoint() {
        try {
            geocodingService.getGeocodingData("1600 Amphitheatre Parkway, Mountain View, CA 94043, USA");
        } catch (GoogleApiException e) {
            throw new RuntimeException("unexpected");
        }
    }

}