package api.estimates.interfaces;

import api.estimates.model.AggregatedData;
import services.model.jackson.estimates.Estimates;
import services.model.jackson.estimates.Estimates.VendorEstimate;
import services.model.jackson.estimates.Estimates.VendorEstimate.TripEstimate;
import services.model.jackson.tripEstimates.UberPriceEstimatesApiResponse;
import services.model.jackson.tripEstimates.UberTimeEstimatesApiResponse;

public interface AggregatedDataParser {
  Estimates createEstimates(final AggregatedData aggregatedData);

  TripEstimate[] createTripEstimates(final UberPriceEstimatesApiResponse uberPriceEstimatesApiResponse, final UberTimeEstimatesApiResponse uberTimeEstimatesApiResponse);

  VendorEstimate[] createVendorEstimates(final UberPriceEstimatesApiResponse uberPriceEstimatesApiResponse, final UberTimeEstimatesApiResponse uberTimeEstimatesApiResponse);
}
