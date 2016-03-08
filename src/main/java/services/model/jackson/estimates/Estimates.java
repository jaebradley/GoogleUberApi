package services.model.jackson.estimates;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Estimates {
    public static class Location {
        private final String address;
        private final BigDecimal latitude;
        private final BigDecimal longitude;

        public Location(final String address, final BigDecimal latitude, final BigDecimal longitude) {
            this.address = address;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        @JsonProperty("address")
        public String getAddress() {
            return address;
        }

        @JsonProperty("latitude")
        public BigDecimal getLatitude() {
            return latitude;
        }

        @JsonProperty("longitude")
        public BigDecimal getLongitude() {
            return longitude;
        }
    }

    public static class VendorEstimate {
        public static class TripEstimate {
            public static class Price {
                private final int lowEstimate;
                private final int highEstimate;
                private final BigDecimal surgeMultiplier;
                private final String currencyCode;

                public Price(final int lowEstimate, final int highEstimate, final BigDecimal surgeMultiplier, final String currencyCode) {
                    this.lowEstimate = lowEstimate;
                    this.highEstimate = highEstimate;
                    this.surgeMultiplier = surgeMultiplier;
                    this.currencyCode = currencyCode;
                }

                @JsonProperty("lowEstimate")
                public int getLowEstimate() {
                    return lowEstimate;
                }

                @JsonProperty("highEstimate")
                public int getHighEstimate() {
                    return highEstimate;
                }

                @JsonProperty("surgeMultiplier")
                public BigDecimal getSurgeMultiplier() {
                    return surgeMultiplier;
                }

                @JsonProperty("currencyCode")
                public String getCurrencyCode() {
                    return currencyCode;
                }
            }

            public static class Trip {
                private final int durationSeconds;
                private final int waitSeconds;
                private final BigDecimal distanceMiles;

                public Trip(final int durationSeconds, final int waitSeconds, final BigDecimal distanceMiles) {
                    this.durationSeconds = durationSeconds;
                    this.waitSeconds = waitSeconds;
                    this.distanceMiles = distanceMiles;
                }

                @JsonProperty("durationSeconds")
                public int getDurationSeconds() {
                    return durationSeconds;
                }

                @JsonProperty("waitSeconds")
                public int getWaitSeconds() {
                    return waitSeconds;
                }

                @JsonProperty("distanceMiles")
                public BigDecimal getDistanceMiles() {
                    return distanceMiles;
                }
            }

            private final String productName;
            private final Price price;
            private final Trip trip;

            public TripEstimate(final String productName, final Price price, final Trip trip) {
                this.productName = productName;
                this.price = price;
                this.trip = trip;
            }

            @JsonProperty("productName")
            public String getProductName() {
                return productName;
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

        private final TripVendor vendor;
        private final TripEstimate[] tripEstimates;

        public VendorEstimate(final TripVendor vendor,
            final TripEstimate[] tripEstimates) {
            this.vendor = vendor;
            this.tripEstimates = tripEstimates;
        }

        @JsonProperty("vendor")
        public TripVendor getVendor() {
            return vendor;
        }

        @JsonProperty("tripEstimates")
        public TripEstimate[] getTripEstimates() {
            return tripEstimates;
        }
    }

    private final Location startLocation;
    private final Location endLocation;
    private final VendorEstimate[] vendorEstimates;

    public Estimates(final Location startLocation, final Location endLocation, final VendorEstimate[] vendorEstimates) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.vendorEstimates = vendorEstimates;
    }

    @JsonProperty("startLocation")
    public Location getStartLocation() {
        return startLocation;
    }

    @JsonProperty("endLocation")
    public Location getEndLocation() {
        return endLocation;
    }

    @JsonProperty("vendorEstimates")
    public VendorEstimate[] getVendorEstimates() {
        return vendorEstimates;
    }
}
