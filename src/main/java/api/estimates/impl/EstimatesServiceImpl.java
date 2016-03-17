package api.estimates.impl;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

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
                        throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Unable to get time estimates from Uber").build());
                    }
                } catch (UberApiException e) {
                    throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Unable to get price estimates from Uber").build());
                }
            } catch (GoogleApiException e) {
                throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Unable to get end address information from Google").build());
            }
        } catch (GoogleApiException e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Unable to get start address information from Google").build());
        }
    }

    public Estimates getEstimates(final String startAddress, final String endAddress) {
        return aggregatedDataParser.createEstimates(getAggregatedData(startAddress, endAddress));
    }

    public String getSerializedEstimates(final String startAddress, final String endAddress) {
        try {
            return objectMapper.writeValueAsString(getEstimates(startAddress, endAddress));
        } catch (JsonProcessingException e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Unable to serialize json").build());
        }
    }
}
