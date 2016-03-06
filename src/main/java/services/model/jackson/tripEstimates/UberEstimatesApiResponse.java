package services.model.jackson.tripEstimates;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UberEstimatesApiResponse {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Price {
        private final String surgeConfirmationHref;
        private final String surgeConfirmationId;
        private final BigDecimal surgeMultiplier;
        private final int highEstimate;
        private final int lowEstimate;
        private final int minimum;
        private final String display;
        private final String currencyCode;

        @JsonCreator
        public Price(@JsonProperty("surge_confirmation_href") final String surgeConfirmationHref,
                     @JsonProperty("surge_confirmation_id") final String surgeConfirmationId,
                     @JsonProperty("surge_multiplier") final BigDecimal surgeMultiplier,
                     @JsonProperty("high_estimate") final int highEstimate,
                     @JsonProperty("low_estimate") final int lowEstimate,
                     @JsonProperty("minimum") final int minimum,
                     @JsonProperty("display") final String display,
                     @JsonProperty("currency_code") final String currencyCode) {
            this.surgeConfirmationHref = surgeConfirmationHref;
            this.surgeConfirmationId = surgeConfirmationId;
            this.surgeMultiplier = surgeMultiplier;
            this.highEstimate = highEstimate;
            this.lowEstimate = lowEstimate;
            this.minimum = minimum;
            this.display = display;
            this.currencyCode = currencyCode;
        }

        @JsonProperty("surge_confirmation_href")
        public String getSurgeConfirmationHref() {
            return surgeConfirmationHref;
        }

        @JsonProperty("surge_confirmation_id")
        public String getSurgeConfirmationId() {
            return surgeConfirmationId;
        }

        @JsonProperty("surge_multiplier")
        public BigDecimal getSurgeMultiplier() {
            return surgeMultiplier;
        }

        @JsonProperty("high_estimate")
        public int getHighEstimate() {
            return highEstimate;
        }

        @JsonProperty("low_estimate")
        public int getLowEstimate() {
            return lowEstimate;
        }

        @JsonProperty("minimum")
        public int getMinimum() {
            return minimum;
        }

        @JsonProperty("display")
        public String getDisplay() {
            return display;
        }

        @JsonProperty("currency_code")
        public String getCurrencyCode() {
            return currencyCode;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Trip {
        private final BigDecimal distance;
        private final DistanceUnit distanceUnit;
        private final Integer durationSeconds;

        @JsonCreator
        public Trip(@JsonProperty("distance_estimate") final BigDecimal distance,
                    @JsonProperty("distance_unit") final DistanceUnit distanceUnit,
                    @JsonProperty("duration_estimate") final Integer durationSeconds) {
            this.distance = distance;
            this.distanceUnit = distanceUnit;
            this.durationSeconds = durationSeconds;
        }

        @JsonProperty("distance_estimate")
        public BigDecimal getDistance() {
            return distance;
        }

        @JsonProperty("distance_unit")
        public DistanceUnit getDistanceUnit() {
            return distanceUnit;
        }

        @JsonProperty("duration_estimate")
        public Integer getDurationSeconds() {
            return durationSeconds;
        }
    }

    private final Price price;
    private final Trip trip;

    @JsonCreator
    public UberEstimatesApiResponse(@JsonProperty("price") final Price price,
                                    @JsonProperty("trip") final Trip trip) {
        this.price = price;
        this.trip = trip;
    }

    @JsonProperty("price")
    public Price getPrice() {
        return price;
    }

    @JsonProperty("trip")
    public Trip getTrip() {
        return trip;
    }
}
