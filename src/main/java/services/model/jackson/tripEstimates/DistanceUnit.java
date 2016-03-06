package services.model.jackson.tripEstimates;

public enum DistanceUnit {
    MILE("mile"),
    KILOMETER("km");

    private final String name;

    DistanceUnit(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
