package services.impl;

import services.interfaces.TripEstimatesService;
import services.model.jackson.tripEstimates.UberPriceEstimatesApiResponse;

import java.math.BigDecimal;

public class TripEstimatesServiceImpl implements TripEstimatesService{
    private static final String UBER_PRICE_ESTIMATES_ENDPOINT = "https://api.uber.com/v1/estimates/price?";
    private static final String SERVER_TOKEN = "We0MNCaIpx00F_TUopt4jgL9BzW3bWWt16aYM4mh";

    public UberPriceEstimatesApiResponse getUberPriceEstimates(final BigDecimal startLatitude, final BigDecimal startLongitude, final BigDecimal endLatitude, final BigDecimal endLongitude) {
        return null;
    }
}
