package services.model.jackson;

public enum GeocodingResponseStatus {
    OK("OK");

    private String value;

    GeocodingResponseStatus(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
