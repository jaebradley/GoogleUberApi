package api.estimates.interfaces;

import api.estimates.model.AggregatedData;

public interface EstimatesService {
    AggregatedData getAggregatedData(final String startAddress, final String endAddress);
}
