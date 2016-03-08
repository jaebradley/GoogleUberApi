package api.estimates.impl;

import javax.ws.rs.BadRequestException;

import api.estimates.interfaces.AggregatedDataParser;
import api.estimates.interfaces.EstimatesService;
import api.estimates.model.AggregatedData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import services.exceptions.GoogleApiException;
import services.exceptions.UberApiException;
import services.interfaces.GeocodingService;
import services.interfaces.TripEstimatesService;
import services.model.jackson.estimates.Estimates;
import services.model.jackson.geocoding.GeocodingApiResponse;
import services.model.jackson.tripEstimates.UberPriceEstimatesApiResponse;
import services.model.jackson.tripEstimates.UberTimeEstimatesApiResponse;

public class EstimatesServiceImpl implements EstimatesService {
    private final GeocodingService geocodingService;
    private final TripEstimatesService tripEstimatesService;
    private final AggregatedDataParser aggregatedDataParser;
    private final ObjectMapper objectMapper;

    public EstimatesServiceImpl(final GeocodingService geocodingService,
                                final TripEstimatesService tripEstimatesService,
                                final AggregatedDataParser aggregatedDataParser,
                                final ObjectMapper objectMapper) {
        this.geocodingService = geocodingService;
        this.tripEstimatesService = tripEstimatesService;
        this.aggregatedDataParser = aggregatedDataParser;
        this.objectMapper = objectMapper;
    }

    public AggregatedData getAggregatedData(final String startAddress, final String endAddress) {
        try {
            final GeocodingApiResponse startGeocodingData = geocodingService.getGeocodingData(startAddress);
            try {
                final GeocodingApiResponse endGeocodingData = geocodingService.getGeocodingData(endAddress);
                final GeocodingApiResponse.Result.Geometry.Location firstStartLocation = startGeocodingData.getResults()[0].getGeometry().getLocation();
                final GeocodingApiResponse.Result.Geometry.Location firstEndLocation = endGeocodingData.getResults()[0].getGeometry().getLocation();
                try {
                    final UberPriceEstimatesApiResponse uberPriceEstimatesApiResponse = tripEstimatesService.getUberPriceEstimates(firstStartLocation.getLatitude(), firstStartLocation.getLongitude(), firstEndLocation.getLatitude(), firstEndLocation.getLongitude());
                    try {
                        final UberTimeEstimatesApiResponse uberTimeEstimatesApiResponse = tripEstimatesService.getUberTimeEstimates(firstStartLocation.getLatitude(), firstStartLocation.getLongitude());
                        return new AggregatedData(
                            startGeocodingData,
                            endGeocodingData,
                            uberPriceEstimatesApiResponse,
                            uberTimeEstimatesApiResponse
                        );
                    } catch (UberApiException e) {
                        throw new BadRequestException("Unable to get time estimates from Uber");
                    }
                } catch (UberApiException e) {
                    throw new BadRequestException("Unable to get price estimates from Uber");
                }
            } catch (GoogleApiException e) {
                throw new BadRequestException("Unable to get end address information from Google");
            }
        } catch (GoogleApiException e) {
            throw new BadRequestException("Unable to get start address information from Google");
        }
    }

    public Estimates getEstimates(final String startAddress, final String endAddress) {
        return aggregatedDataParser.createEstimates(getAggregatedData(startAddress, endAddress));
    }

    public String getSerializedEstimates(final String startAddress, final String endAddress) {
        try {
            return objectMapper.writeValueAsString(getEstimates(startAddress, endAddress));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to serialize json");
        }
    }
}
