package services.model.jackson.geocoding;

public enum GeocodingResponseStatus {
    OK("OK"),
    ZERO_RESULTS("ZERO_RESULTS"),
    OVER_QUERY_LIMIT("OVER_QUERY_LIMIT"),
    REQUEST_DENIED("REQUEST_DENIED"),
    INVALID_REQUEST("INVALID_REQUEST"),
    UNKNOWN_ERROR("UNKNOWN_ERROR");

    private String value;

    GeocodingResponseStatus(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
