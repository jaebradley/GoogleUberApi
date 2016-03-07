package api.estimates.impl;

import api.estimates.interfaces.EstimatesService;
import services.model.jackson.estimates.Estimates;
import services.model.jackson.estimates.Estimates.Location;
import services.model.jackson.estimates.Estimates.VendorEstimate;
import services.model.jackson.estimates.Estimates.VendorEstimate.TripEstimate;
import services.model.jackson.estimates.Estimates.VendorEstimate.TripEstimate.Trip;
import services.model.jackson.estimates.TripVendor;
import services.model.jackson.geocoding.GeocodingApiResponse;
import services.model.jackson.tripEstimates.UberPriceEstimatesApiResponse;
import services.model.jackson.tripEstimates.UberPriceEstimatesApiResponse.Price;
import services.model.jackson.tripEstimates.UberTimeEstimatesApiResponse;
import services.model.jackson.tripEstimates.UberTimeEstimatesApiResponse.Time;

public class EstimatesServiceImpl implements EstimatesService {
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

    public Estimates createEstimates(final GeocodingApiResponse startLocationResponse, final GeocodingApiResponse endLocationResponse, final UberPriceEstimatesApiResponse uberPriceEstimatesApiResponse, final UberTimeEstimatesApiResponse uberTimeEstimatesApiResponse) {
        final VendorEstimate[] vendorEstimates = createVendorEstimates(uberPriceEstimatesApiResponse, uberTimeEstimatesApiResponse);
        return new Estimates(
            new Location(
                startLocationResponse.getResults()[0].getFormattedAddress(),
                startLocationResponse.getResults()[0].getGeometry().getLocation().getLatitude(),
                startLocationResponse.getResults()[0].getGeometry().getLocation().getLongitude()
            ),
            new Location(
                endLocationResponse.getResults()[0].getFormattedAddress(),
                endLocationResponse.getResults()[0].getGeometry().getLocation().getLatitude(),
                endLocationResponse.getResults()[0].getGeometry().getLocation().getLongitude()
            ),
            vendorEstimates
        );
    }
}
