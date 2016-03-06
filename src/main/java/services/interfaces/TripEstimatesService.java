package services.interfaces;

import services.model.jackson.tripEstimates.UberPriceEstimatesApiResponse;

import java.math.BigDecimal;

public interface TripEstimatesService {
    UberPriceEstimatesApiResponse getUberPriceEstimates(final BigDecimal startLatitude, final BigDecimal startLongitude, final BigDecimal endLatitude, final BigDecimal endLongitude);
}
