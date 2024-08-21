/**
 * Implements a service which communicates with the OpenWeatherMapService.
 *
 * @Author Bandula Gamage
 * Date: 20/08/2024
 */

package com.weather.demo.api.service;

import com.weather.demo.api.exception.ResourceNotFoundException;
import com.weather.demo.api.model.dto.openweathersvc.OpenWeatherRecord;
import com.weather.demo.util.Constants;
import javax.ws.rs.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenWeatherMapService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenWeatherMapService.class);

    private transient RestTemplate restTemplate;

    // Last read date and time
    private long lastReadTime;

    public OpenWeatherRecord findWeatherForTheCity(String country, String city) throws Exception {
        OpenWeatherRecord status = null;

        return readFromOpenWeatherService(country, city);
    }

    /**
     * Reads from the OpenWeatherMapService for a given country and a city.
     * @param country Country
     * @param city City
     * @return CityWeatherStatus object or throws an exception if any error comes across.
     */
    private OpenWeatherRecord readFromOpenWeatherService(String country, String city) {
        try {
            restTemplate = new RestTemplate();
            final String fetchUrl = generateUrl(country, city);
            LOGGER.info("[OpenWeatherMapService.readData] Fetching data from {}", fetchUrl);

            lastReadTime = System.currentTimeMillis();

            return restTemplate.getForObject(fetchUrl, OpenWeatherRecord.class);
        } catch (final HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ResourceNotFoundException("Cannot find a record for the combination " + country + ", " + city);
            } else {
                throw new BadRequestException("Unknown Error message", e);
            }
        }
    }

    /**
     * Generates the data read url with the given parameters.
     * @param country Country
     * @param city City
     * @return URL string
     */
    private String generateUrl(String country, String city) {
        StringBuffer fetchUrl = new StringBuffer(Constants.OPEN_WEATHER_MAP_API_BASE);
        fetchUrl.append(city);
        fetchUrl.append(",");
        fetchUrl.append(country);
        fetchUrl.append("&appid=");
        fetchUrl.append(Constants.OPEN_WEATHER_MAP_API_KEY);
        return fetchUrl.toString();
    }
}
