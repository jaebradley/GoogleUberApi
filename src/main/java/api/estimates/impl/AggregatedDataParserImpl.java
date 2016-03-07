package api.estimates.impl;

import api.estimates.interfaces.AggregatedDataParser;
import api.estimates.model.AggregatedData;
import services.model.jackson.estimates.Estimates;
import services.model.jackson.estimates.Estimates.Location;
import services.model.jackson.estimates.Estimates.VendorEstimate;
import services.model.jackson.estimates.Estimates.VendorEstimate.TripEstimate;
import services.model.jackson.estimates.Estimates.VendorEstimate.TripEstimate.Trip;
import services.model.jackson.estimates.TripVendor;
import services.model.jackson.tripEstimates.UberPriceEstimatesApiResponse;
import services.model.jackson.tripEstimates.UberPriceEstimatesApiResponse.Price;
import services.model.jackson.tripEstimates.UberTimeEstimatesApiResponse;
import services.model.jackson.tripEstimates.UberTimeEstimatesApiResponse.Time;

public class AggregatedDataParserImpl implements AggregatedDataParser {
  public VendorEstimate[] createVendorEstimates(final UberPriceEstimatesApiResponse uberPriceEstimatesApiResponse,
      final UberTimeEstimatesApiResponse uberTimeEstimatesApiResponse) {
    final TripEstimate[] tripEstimates = createTripEstimates(uberPriceEstimatesApiResponse, uberTimeEstimatesApiResponse);
    return new VendorEstimate[] {
        new VendorEstimate(
            TripVendor.UBER,
            tripEstimates
        )
    };
  }

  public TripEstimate[] createTripEstimates(final UberPriceEstimatesApiResponse uberPriceEstimatesApiResponse,
      final UberTimeEstimatesApiResponse uberTimeEstimatesApiResponse) {
    final TripEstimate[] tripEstimates = new TripEstimate[]{};
    int counter = 0;
    for (final Price price : uberPriceEstimatesApiResponse.getPrices()) {
      for (final Time time : uberTimeEstimatesApiResponse.getTimes()) {
        if (price.getProductId().equals(time.getProductId())) {
          tripEstimates[counter] = new TripEstimate(
              price.getProductName(),
              new TripEstimate.Price(
                  price.getLowEstimate(),
                  price.getHighEstimate(),
                  price.getSurgeMultiplier(),
                  price.getCurrencyCode()
              ),
              new Trip(
                  price.getDurationSeconds(),
                  time.getWaitSeconds(),
                  price.getDistanceMiles()
              )
          );
          counter++;
        }
      }
    }
    return tripEstimates;
  }

  public Estimates createEstimates(final AggregatedData aggregatedData) {
    final VendorEstimate[] vendorEstimates = createVendorEstimates(aggregatedData.getUberPriceEstimatesApiResponse(), aggregatedData.getUberTimeEstimatesApiResponse());
    return new Estimates(
        new Location(
            aggregatedData.getStartLocation().getResults()[0].getFormattedAddress(),
            aggregatedData.getStartLocation().getResults()[0].getGeometry().getLocation().getLatitude(),
            aggregatedData.getStartLocation().getResults()[0].getGeometry().getLocation().getLongitude()
        ),
        new Location(
            aggregatedData.getEndLocation().getResults()[0].getFormattedAddress(),
            aggregatedData.getEndLocation().getResults()[0].getGeometry().getLocation().getLatitude(),
            aggregatedData.getEndLocation().getResults()[0].getGeometry().getLocation().getLongitude()
        ),
        vendorEstimates
    );
  }
}
