package services.interfaces;

import services.model.jackson.geocoding.GeocodingApiResponse;

public interface GeocodingService {
    GeocodingApiResponse getGeocodingData(final String address);
}
