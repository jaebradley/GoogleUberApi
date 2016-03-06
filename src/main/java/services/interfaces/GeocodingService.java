package services.interfaces;

import services.model.jackson.GeocodingApiResponse;

public interface GeocodingService {
    GeocodingApiResponse getGeocodingData(final String address);
}
