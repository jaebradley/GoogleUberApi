package api.estimates.impl;

import api.estimates.interfaces.EstimatesService;
import services.model.jackson.estimates.Estimates;
import services.model.jackson.estimates.Estimates.Estimate;
import services.model.jackson.estimates.TripVendor;
import services.model.jackson.geocoding.GeocodingApiResponse;
import services.model.jackson.tripEstimates.UberPriceEstimatesApiResponse;
import services.model.jackson.tripEstimates.UberTimeEstimatesApiResponse;

public class EstimatesServiceImpl implements EstimatesService {
    public Estimates createEstimates(final GeocodingApiResponse geocodingApiResponse, final UberPriceEstimatesApiResponse uberPriceEstimatesApiResponse, final UberTimeEstimatesApiResponse uberTimeEstimatesApiResponse) {
        final Estimate[] estimates = new Estimate[] {
                new Estimate(
                        TripVendor.UBER,
                )
        }
        return new Estimates(

        )
    }
}
