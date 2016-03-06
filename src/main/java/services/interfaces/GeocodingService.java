package services.interfaces;

import services.exceptions.GoogleApiException;
import services.model.jackson.geocoding.GeocodingApiResponse;

public interface GeocodingService {
    GeocodingApiResponse getGeocodingData(final String address) throws GoogleApiException;
}
