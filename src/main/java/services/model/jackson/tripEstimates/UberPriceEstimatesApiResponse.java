package services.model.jackson.tripEstimates;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UberPriceEstimatesApiResponse {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Price {
        private final String productId;
        private final String productName;
        private final String currencyCode;
        private final String estimateDisplay;
        private final int lowEstimate;
        private final int highEstimate;
        private final BigDecimal surgeMultiplier;
        private final Integer durationSeconds;
        private final BigDecimal distanceMiles;

        @JsonCreator
        public Price(@JsonProperty("product_id") final String productId,
                     @JsonProperty("display_name") final String productName,
                     @JsonProperty("currency_code") final String currencyCode,
                     @JsonProperty("estimate") final String estimateDisplay,
                     @JsonProperty("low_estimate") final int lowEstimate,
                     @JsonProperty("high_estimate") final int highEstimate,
                     @JsonProperty("surge_multiplier") final BigDecimal surgeMultiplier,
                     @JsonProperty("duration") final Integer durationSeconds,
                     @JsonProperty("distance") final BigDecimal distanceMiles) {
            this.productId = productId;
            this.productName = productName;
            this.currencyCode = currencyCode;
            this.estimateDisplay = estimateDisplay;
            this.lowEstimate = lowEstimate;
            this.highEstimate = highEstimate;
            this.surgeMultiplier = surgeMultiplier;
            this.durationSeconds = durationSeconds;
            this.distanceMiles = distanceMiles;
        }

        @JsonProperty("product_id")
        public String getProductId() {
            return productId;
        }

        @JsonProperty("display_name")
        public String getProductName() {
            return productName;
        }

        @JsonProperty("currency_code")
        public String getCurrencyCode() {
            return currencyCode;
        }

        @JsonProperty("estimate")
        public String getEstimateDisplay() {
            return estimateDisplay;
        }

        @JsonProperty("low_estimate")
        public int getLowEstimate() {
            return lowEstimate;
        }

        @JsonProperty("high_estimate")
        public int getHighEstimate() {
            return highEstimate;
        }

        @JsonProperty("surge_multiplier")
        public BigDecimal getSurgeMultiplier() {
            return surgeMultiplier;
        }

        @JsonProperty("duration")
        public Integer getDurationSeconds() {
            return durationSeconds;
        }

        @JsonProperty("distance")
        public BigDecimal getDistanceMiles() {
            return distanceMiles;
        }
    }

    private final Price[] prices;

    @JsonCreator
    public UberPriceEstimatesApiResponse(@JsonProperty("prices") final Price[] prices) {
        this.prices = prices;
    }

    @JsonProperty("prices")
    public Price[] getPrice() {
        return prices;
    }
}
