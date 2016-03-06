package services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import services.interfaces.GeolocationService;
import services.model.GeolocationData;

import javax.ws.rs.BadRequestException;
import java.io.IOException;

public class GeolocationServiceImpl implements GeolocationService {
    public static final String GEOCODING_ENDPOINT = "https://maps.googleapis.com/maps/api/geocode/output?";

    public GeolocationData getGeolocationData() {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(GEOCODING_ENDPOINT);
        try {
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            try {
                System.out.println(response1.getStatusLine());
                HttpEntity entity1 = response1.getEntity();
                GeolocationData geolocationData = new ObjectMapper().readValue(entity1.getContent(), GeolocationData.class);
                EntityUtils.consume(entity1);
                return geolocationData;
            } finally {
                response1.close();
            }
        } catch (IOException e) {
            throw new BadRequestException("Can't make request");
        }
    }
}
