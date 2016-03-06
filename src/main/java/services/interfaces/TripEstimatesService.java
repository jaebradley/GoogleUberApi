package services.interfaces;

import services.model.jackson.tripEstimates.UberPriceEstimatesApiResponse;
import services.model.jackson.tripEstimates.UberTimeEstimatesApiResponse;

import java.math.BigDecimal;

public interface TripEstimatesService {
    UberPriceEstimatesApiResponse getUberPriceEstimates(final BigDecimal startLatitude, final BigDecimal startLongitude, final BigDecimal endLatitude, final BigDecimal endLongitude);

    UberTimeEstimatesApiResponse getUberTimeEstimates(final BigDecimal startLatitude, final BigDecimal startLongitude);
}
