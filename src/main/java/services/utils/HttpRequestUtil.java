package services.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class HttpRequestUtil {
    public static String addQueryParams(final String baseEndpoint, final Map<String, String> paramMap) {
        StringBuilder stringBuilder = new StringBuilder(baseEndpoint);
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            try {
                stringBuilder
                        .append("&")
                        .append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("unexpected encoding");
            }
        }
        return stringBuilder.toString();
    }
}
