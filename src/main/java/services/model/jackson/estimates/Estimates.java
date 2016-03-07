package services.model.jackson.estimates;

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

        public String getAddress() {
            return address;
        }

        public BigDecimal getLatitude() {
            return latitude;
        }

        public BigDecimal getLongitude() {
            return longitude;
        }
    }

    public static class Estimate {

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

                public int getLowEstimate() {
                    return lowEstimate;
                }

                public int getHighEstimate() {
                    return highEstimate;
                }

                public BigDecimal getSurgeMultiplier() {
                    return surgeMultiplier;
                }

                public String getCurrencyCode() {
                    return currencyCode;
                }
            }

            public static class Trip {
                private final int durationSeconds;
                private final BigDecimal distanceMiles;

                public Trip(final int durationSeconds, final BigDecimal distanceMiles) {
                    this.durationSeconds = durationSeconds;
                    this.distanceMiles = distanceMiles;
                }

                public int getDurationSeconds() {
                    return durationSeconds;
                }

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

            public String getProductName() {
                return productName;
            }

            public Price getPrice() {
                return price;
            }

            public Trip getTrip() {
                return trip;
            }
        }

        private final TripVendor vendor;
        private final Location startLocation;
        private final Location endLocation;
        private final TripEstimate[] tripEstimates;

        public Estimate(final TripVendor vendor, final Location startLocation, final Location endLocation, final TripEstimate[] tripEstimates) {
            this.vendor = vendor;
            this.startLocation = startLocation;
            this.endLocation = endLocation;
            this.tripEstimates = tripEstimates;
        }

        public TripVendor getVendor() {
            return vendor;
        }

        public Location getStartLocation() {
            return startLocation;
        }

        public Location getEndLocation() {
            return endLocation;
        }

        public TripEstimate[] getTripEstimates() {
            return tripEstimates;
        }
    }

    private final Estimate[] estimates;

    public Estimates(final Estimate[] estimates) {
        this.estimates = estimates;
    }

    public Estimate[] getEstimates() {
        return estimates;
    }
}
