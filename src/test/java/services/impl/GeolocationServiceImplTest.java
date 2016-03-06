package services.impl;

import org.junit.Test;

public class GeolocationServiceImplTest {
    private final GeolocationServiceImpl geolocationService = new GeolocationServiceImpl();

    @Test
    public void testGeocodingEndpoint() {
        geolocationService.getGeolocationData("1600 Amphitheatre Parkway, Mountain View, CA 94043, USA");
    }

}