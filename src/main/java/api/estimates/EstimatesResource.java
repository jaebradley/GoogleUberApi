package api.estimates;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import api.estimates.impl.AggregatedDataParserImpl;
import api.estimates.impl.EstimatesServiceImpl;
import api.estimates.interfaces.EstimatesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import services.impl.GeocodingServiceImpl;
import services.impl.TripEstimatesServiceImpl;
import services.model.jackson.estimates.Estimates;

@Path("estimates")
public class EstimatesResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Estimates getEstimates(
            @QueryParam("startAddress") final String startAddress,
            @QueryParam("endAddress") final String endAddress
    ) {
        final EstimatesServiceImpl service = new EstimatesServiceImpl(
                new GeocodingServiceImpl(),
                new TripEstimatesServiceImpl(),
                new AggregatedDataParserImpl(),
                new ObjectMapper()
        );
        return service.getEstimates(startAddress, endAddress);
    }
}
