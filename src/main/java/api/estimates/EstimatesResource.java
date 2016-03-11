package api.estimates;

import api.estimates.impl.AggregatedDataParserImpl;
import api.estimates.impl.EstimatesServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import services.impl.GeocodingServiceImpl;
import services.impl.TripEstimatesServiceImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/estimates")
public class EstimatesResource {

    final EstimatesServiceImpl service = new EstimatesServiceImpl(
        new GeocodingServiceImpl(),
        new TripEstimatesServiceImpl(),
        new AggregatedDataParserImpl(),
        new ObjectMapper()
    );

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getEstimates(
            @QueryParam("startAddress") final String startAddress,
            @QueryParam("endAddress") final String endAddress
    ) {
        return service.getSerializedEstimates(startAddress, endAddress);
    }
}
