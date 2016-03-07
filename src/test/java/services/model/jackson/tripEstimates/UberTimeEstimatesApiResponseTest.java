package services.model.jackson.tripEstimates;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UberTimeEstimatesApiResponseTest {
    private final ObjectMapper mapper = new ObjectMapper();
    private final String uberTimeEstimatesApiResponseFileName = "src/test/resources/services.model.jackson/tripEstimates/UberTimeEstimatesApiResponse.json";
    private final File uberTimeEstimatesApiResponseFile = new File(uberTimeEstimatesApiResponseFileName);

    @Test
    public void testUberTimeEstimates() throws IOException {
        UberTimeEstimatesApiResponse response = mapper.readValue(uberTimeEstimatesApiResponseFile, UberTimeEstimatesApiResponse.class);
        Assert.assertEquals(response.getTimes().length, 5);
        Assert.assertEquals(response.getTimes()[0].getProductId(), "55c66225-fbe7-4fd5-9072-eab1ece5e23e");
        Assert.assertEquals(response.getTimes()[0].getProductName(), "uberX");
        Assert.assertEquals(response.getTimes()[0].getWaitSeconds(), 120);
    }
}
