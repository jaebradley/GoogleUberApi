package api.estimates.interfaces;

import api.estimates.model.AggregatedData;
import services.model.jackson.estimates.Estimates;

public interface EstimatesService {
    AggregatedData getAggregatedData(final String startAddress, final String endAddress);

    Estimates getEstimates(final String startAddress, final String endAddress);

    String getSerializedEstimates(final String startAddress, final String endAddress);
}
