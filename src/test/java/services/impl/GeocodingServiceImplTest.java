package services.impl;

import org.junit.Test;

public class GeocodingServiceImplTest {
    private final GeocodingServiceImpl geolocationService = new GeocodingServiceImpl();

    @Test
    public void testGeocodingEndpoint() {
        geolocationService.getGeocodingData("1600 Amphitheatre Parkway, Mountain View, CA 94043, USA");
    }

}