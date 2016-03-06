package services.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import services.model.jackson.GeocodingApiResponse;

import java.io.File;
import java.math.BigDecimal;

public class GeocodingApiResponseTest {

    private final ObjectMapper mapper = new ObjectMapper();
    private final String geocodingResponseFilePath = "src/test/resources/services.model.jackson/GeocodingResponse.json";
    private final File geocodingResponseFile = new File(geocodingResponseFilePath);

    @Test
    public void testGeocodingDeserialization() throws Exception {
        GeocodingApiResponse response = mapper.readValue(geocodingResponseFile, GeocodingApiResponse.class);
        Assert.assertTrue(response.getStatus().getValue().equals("OK"));
        Assert.assertEquals(response.getResults().length, 1);
        Assert.assertEquals(response.getResults()[0].getFormattedAddress(), "1600 Amphitheatre Parkway, Mountain View, CA 94043, USA");
        Assert.assertEquals(response.getResults()[0].getGeometry().getLocation().getLatitude(), new BigDecimal("37.4224764"));
        Assert.assertEquals(response.getResults()[0].getGeometry().getLocation().getLongitude(), new BigDecimal("-122.0842499"));
    }
}