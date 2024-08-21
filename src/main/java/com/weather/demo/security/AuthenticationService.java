package com.weather.demo.security;

import com.weather.demo.util.Constants;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

public class AuthenticationService {

    static final private String AUTH_METHOD = "apiKey";
    private static final String AUTH_TOKEN_HEADER_NAME = "apiKey";

    public static Authentication getAuthentication(HttpServletRequest request) {
        String apiKey = getApiKey(request); //request.getHeader(AUTH_TOKEN_HEADER_NAME);
        if (apiKey == null || !isValidApiKey(apiKey)) {
            throw new BadCredentialsException("Invalid API Key");
        }

        return new ApiKeyAuthenticationToken(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }

    private static boolean isValidApiKey(String apiKey) {
        for (String key: Constants.SAMPLE_API_KEYs) {
            if (key.equalsIgnoreCase(apiKey))
                return true;
        }

        return false;
    }

    private static String getApiKey(HttpServletRequest httpRequest) {
        String apiKey = null;

        /**
         * First checks whether the ApiKey can be found within the header
         */
        String authHeader = httpRequest.getHeader("Authorization");
        if (authHeader != null) {
            authHeader = authHeader.trim();
            if (authHeader.toLowerCase().startsWith(AUTH_METHOD + " ")) {
                apiKey = authHeader.substring(AUTH_METHOD.length()).trim();
            }
        }

        /**
         * If not checks whether the ApiKey can be found within the query string
         */
        if (apiKey == null) {
            String queryString = httpRequest.getQueryString();

            if (queryString != null && queryString.indexOf(AUTH_METHOD) >= 0) {
                String[] parameters = queryString.split("&");
                Map<String, String> queryParameters = new HashMap<>();
                for (String parameter : parameters) {
                    String[] keyValuePair = parameter.split("=");
                    queryParameters.put(keyValuePair[0], keyValuePair[1]);
                }
                apiKey = queryParameters.get(AUTH_METHOD);
            }
        }
        return apiKey;
    }

}
