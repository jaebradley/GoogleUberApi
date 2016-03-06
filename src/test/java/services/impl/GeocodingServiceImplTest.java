package services.impl;

import org.junit.Test;

public class GeocodingServiceImplTest {
    private final GeocodingServiceImpl geocodingService = new GeocodingServiceImpl();

    @Test
    public void testGeocodingEndpoint() {
        geocodingService.getGeocodingData("1600 Amphitheatre Parkway, Mountain View, CA 94043, USA");
    }

}