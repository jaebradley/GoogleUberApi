package api.estimates.interfaces;

import api.estimates.model.AggregatedData;
import services.model.jackson.estimates.Estimates;
import services.model.jackson.estimates.Estimates.VendorEstimate;
import services.model.jackson.estimates.Estimates.VendorEstimate.TripEstimate;
import services.model.jackson.geocoding.GeocodingApiResponse;
import services.model.jackson.tripEstimates.UberPriceEstimatesApiResponse;
import services.model.jackson.tripEstimates.UberTimeEstimatesApiResponse;

public interface EstimatesService {
    Estimates createEstimates(final GeocodingApiResponse startLocationResponse, final GeocodingApiResponse endLocationResponse, final UberPriceEstimatesApiResponse uberPriceEstimatesApiResponse, final UberTimeEstimatesApiResponse uberTimeEstimatesApiResponse);

    TripEstimate[] createTripEstimates(final UberPriceEstimatesApiResponse uberPriceEstimatesApiResponse, final UberTimeEstimatesApiResponse uberTimeEstimatesApiResponse);

    VendorEstimate[] createVendorEstimates(final UberPriceEstimatesApiResponse uberPriceEstimatesApiResponse, final UberTimeEstimatesApiResponse uberTimeEstimatesApiResponse);

    AggregatedData getAggregatedData(final String startAddress, final String endAddress);
}
