package services.interfaces;

import services.model.GeolocationData;

public interface GeolocationService {
    GeolocationData getGeolocationData(final String address);
}
