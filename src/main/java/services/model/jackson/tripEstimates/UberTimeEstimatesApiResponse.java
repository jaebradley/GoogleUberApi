package services.model.jackson.tripEstimates;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UberTimeEstimatesApiResponse {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Time {
        private final String productId;
        private final String productName;
        private final int waitSeconds;

        @JsonCreator
        public Time(@JsonProperty("product_id") final String productId,
                    @JsonProperty("display_name") final String productName,
                    @JsonProperty("estimate") final int waitSeconds) {
            this.productId = productId;
            this.productName = productName;
            this.waitSeconds = waitSeconds;
        }

        @JsonProperty("product_id")
        public String getProductId() {
            return productId;
        }

        @JsonProperty("display_name")
        public String getProductName() {
            return productName;
        }

        @JsonProperty("estimate")
        public int getWaitSeconds() {
            return waitSeconds;
        }
    }

    private final Time[] times;

    @JsonCreator
    public UberTimeEstimatesApiResponse(@JsonProperty("times") final Time[] times) {
        this.times = times;
    }

    @JsonProperty("times")
    public Time[] getTimes() {
        return times;
    }
}
