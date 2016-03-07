package api.estimates.model;

import services.model.jackson.geocoding.GeocodingApiResponse;
import services.model.jackson.tripEstimates.UberPriceEstimatesApiResponse;
import services.model.jackson.tripEstimates.UberTimeEstimatesApiResponse;

public class AggregatedData {

  private final GeocodingApiResponse startLocation;
  private final GeocodingApiResponse endLocation;
  private final UberPriceEstimatesApiResponse uberPriceEstimatesApiResponse;
  private final UberTimeEstimatesApiResponse uberTimeEstimatesApiResponse;

  public AggregatedData(final GeocodingApiResponse startLocation,
      final GeocodingApiResponse endLocation,
      final UberPriceEstimatesApiResponse uberPriceEstimatesApiResponse,
      final UberTimeEstimatesApiResponse uberTimeEstimatesApiResponse) {
    this.startLocation = startLocation;
    this.endLocation = endLocation;
    this.uberPriceEstimatesApiResponse = uberPriceEstimatesApiResponse;
    this.uberTimeEstimatesApiResponse = uberTimeEstimatesApiResponse;
  }

  public GeocodingApiResponse getStartLocation() {
    return startLocation;
  }

  public GeocodingApiResponse getEndLocation() {
    return endLocation;
  }

  public UberPriceEstimatesApiResponse getUberPriceEstimatesApiResponse() {
    return uberPriceEstimatesApiResponse;
  }

  public UberTimeEstimatesApiResponse getUberTimeEstimatesApiResponse() {
    return uberTimeEstimatesApiResponse;
  }
}
