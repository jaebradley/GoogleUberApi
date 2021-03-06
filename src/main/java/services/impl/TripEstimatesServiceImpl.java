package services.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import com.fasterxml.jackson.databind.ObjectMapper;

import services.exceptions.UberApiException;
import services.interfaces.TripEstimatesService;
import services.model.jackson.tripEstimates.UberPriceEstimatesApiResponse;
import services.model.jackson.tripEstimates.UberTimeEstimatesApiResponse;
import services.utils.HttpRequestUtil;

public class TripEstimatesServiceImpl implements TripEstimatesService{
    private static final String UBER_PRICE_ESTIMATES_ENDPOINT = "https://api.uber.com/v1/estimates/price?";
    private static final String UBER_TIME_ESTIMATES_ENDPOINT = "https://api.uber.com/v1/estimates/time?";
    private static final String SERVER_TOKEN = "We0MNCaIpx00F_TUopt4jgL9BzW3bWWt16aYM4mh";

    public UberPriceEstimatesApiResponse getUberPriceEstimates(final BigDecimal startLatitude,
                                                               final BigDecimal startLongitude,
                                                               final BigDecimal endLatitude,
                                                               final BigDecimal endLongitude) throws UberApiException {
        try{
            final Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("start_latitude", startLatitude.toPlainString());
            paramMap.put("start_longitude", startLongitude.toPlainString());
            paramMap.put("end_latitude", endLatitude.toPlainString());
            paramMap.put("end_longitude", endLongitude.toPlainString());
            paramMap.put("server_token", SERVER_TOKEN);
            final Content content = Request.Get(HttpRequestUtil.addQueryParams(UBER_PRICE_ESTIMATES_ENDPOINT, paramMap)).execute().returnContent();
            return new ObjectMapper().readValue(content.asStream(), UberPriceEstimatesApiResponse.class);
        } catch (IOException e) {
            throw new UberApiException("Can't make request");
        }
    }

    public UberTimeEstimatesApiResponse getUberTimeEstimates(final BigDecimal startLatitude,
                                                             final BigDecimal startLongitude) throws UberApiException{
        try{
            final Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("start_latitude", startLatitude.toPlainString());
            paramMap.put("start_longitude", startLongitude.toPlainString());
            paramMap.put("server_token", SERVER_TOKEN);
            final Content content = Request.Get(HttpRequestUtil.addQueryParams(UBER_TIME_ESTIMATES_ENDPOINT, paramMap)).execute().returnContent();
            return new ObjectMapper().readValue(content.asStream(), UberTimeEstimatesApiResponse.class);
        } catch (IOException e) {
            throw new UberApiException("Can't make request");
        }
    }
}
