package services.model.jackson.estimates;

public enum TripVendor {
    UBER("UBER");

    private final String name;

    TripVendor(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
