package services.interfaces;

import services.model.jackson.GeocodingApiResponse;

public interface GeolocationService {
    GeocodingApiResponse getGeocodingData(final String address);
}
