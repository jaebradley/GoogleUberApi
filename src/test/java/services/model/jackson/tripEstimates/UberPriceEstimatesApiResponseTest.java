package services.model.jackson.tripEstimates;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class UberPriceEstimatesApiResponseTest {

    private final ObjectMapper mapper = new ObjectMapper();
    private final String uberPriceEstimatesApiResponseFileName = "src/test/resources/services.model.jackson/tripEstimates/UberPriceEstimatesApiResponse.json";
    private final File uberPriceEstimatesApiResponseFile = new File(uberPriceEstimatesApiResponseFileName);

    @Test
    public void testUberPriceEstimates() throws IOException {
        UberPriceEstimatesApiResponse response = mapper.readValue(uberPriceEstimatesApiResponseFile, UberPriceEstimatesApiResponse.class);
        Assert.assertEquals(response.getPrices().length, 5);
        Assert.assertEquals(response.getPrices()[0].getProductId(), "55c66225-fbe7-4fd5-9072-eab1ece5e23e");
        Assert.assertEquals(response.getPrices()[0].getDistanceMiles(), new BigDecimal("2.98"));
        Assert.assertEquals(response.getPrices()[0].getCurrencyCode(), "USD");
        Assert.assertEquals(response.getPrices()[0].getDurationSeconds(), 660);
        Assert.assertEquals(response.getPrices()[0].getEstimateDisplay(), "$8-11");
        Assert.assertEquals(response.getPrices()[0].getHighEstimate(), 11);
        Assert.assertEquals(response.getPrices()[0].getLowEstimate(), 8);
        Assert.assertEquals(response.getPrices()[0].getProductName(), "uberX");
        Assert.assertEquals(response.getPrices()[0].getSurgeMultiplier(), new BigDecimal("1"));
    }
}