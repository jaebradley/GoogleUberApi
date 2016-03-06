package services.model.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodingApiResponse {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Geometry {

            public static class Location {
                private final BigDecimal latitude;
                private final BigDecimal longitude;

                @JsonCreator
                public Location(@JsonProperty("lat") final BigDecimal latitude,
                                @JsonProperty("lng") final BigDecimal longitude) {
                    this.latitude = latitude;
                    this.longitude = longitude;
                }

                @JsonProperty("lat")
                public BigDecimal getLatitude() {
                    return latitude;
                }

                @JsonProperty("lng")
                public BigDecimal getLongitude() {
                    return longitude;
                }
            }
            private final Location location;

            @JsonCreator
            public Geometry(@JsonProperty("location") final Location location) {
                this.location = location;
            }

            @JsonProperty("location")
            public Location getLocation() {
                return location;
            }
        }

        private final Geometry geometry;
        private final String formattedAddress;

        @JsonCreator
        public Result(@JsonProperty("geometry") final Geometry geometry, @JsonProperty("formatted_address") final String formattedAddress) {
            this.geometry = geometry;
            this.formattedAddress = formattedAddress;
        }

        @JsonProperty("geometry")
        public Geometry getGeometry() {
            return geometry;
        }

        @JsonProperty("formatted_address")
        public String getFormattedAddress() {
            return formattedAddress;
        }
    }

    private final GeocodingResponseStatus status;
    private final Result[] results;

    @JsonCreator
    public GeocodingApiResponse(@JsonProperty("status") final GeocodingResponseStatus status, @JsonProperty("results") final Result[] results) {
        this.status = status;
        this.results = results;
    }

    @JsonProperty("status")
    public GeocodingResponseStatus getStatus() {
        return status;
    }

    @JsonProperty("results")
    public Result[] getResults() {
        return results;
    }
}
