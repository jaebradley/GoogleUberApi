package api.estimates;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import api.estimates.interfaces.EstimatesService;

@Path("estimates")
public class EstimatesResource {

    private final EstimatesService estimatesService;

    public EstimatesResource(final EstimatesService estimatesService) {
        this.estimatesService = estimatesService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String foo() {
        estimatesService.getAggregatedData();
    }
}
