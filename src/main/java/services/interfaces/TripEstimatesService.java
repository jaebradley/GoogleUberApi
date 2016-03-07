package services.interfaces;

import java.math.BigDecimal;

import services.exceptions.UberApiException;
import services.model.jackson.tripEstimates.UberPriceEstimatesApiResponse;
import services.model.jackson.tripEstimates.UberTimeEstimatesApiResponse;

public interface TripEstimatesService {
    UberPriceEstimatesApiResponse getUberPriceEstimates(final BigDecimal startLatitude, final BigDecimal startLongitude, final BigDecimal endLatitude, final BigDecimal endLongitude) throws UberApiException;

    UberTimeEstimatesApiResponse getUberTimeEstimates(final BigDecimal startLatitude, final BigDecimal startLongitude) throws UberApiException;
}
