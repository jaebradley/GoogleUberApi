package api.estimates.interfaces;

import services.model.jackson.estimates.Estimates;
import services.model.jackson.geocoding.GeocodingApiResponse;
import services.model.jackson.tripEstimates.UberPriceEstimatesApiResponse;
import services.model.jackson.tripEstimates.UberTimeEstimatesApiResponse;

public interface EstimatesService {
    Estimates createEstimates(final GeocodingApiResponse geocodingApiResponse, final UberPriceEstimatesApiResponse uberPriceEstimatesApiResponse, final UberTimeEstimatesApiResponse uberTimeEstimatesApiResponse);
}
