/**
 * Implements a RESTful controller to deliver weather information
 *
 * @Author Bandula Gamage
 * Date: 20/08/2024
 */
package com.weather.demo.api.controller;

import com.weather.demo.api.exception.BadRequestException;
import com.weather.demo.api.service.WeatherService;
import com.weather.demo.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping( value = Constants.API_ROOT + "/weather" )
public class WeatherController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    private WeatherService weatherService;

    @GetMapping(path="/{country}/{city}")
    public ResponseEntity<Object> getWeatherForTheCity(
            @PathVariable String country,
            @PathVariable String city,
            @RequestHeader(value = "apiKey") Optional<String> apiKey,
            @RequestParam(value = "apiKey") Optional<String> apiKeyQueryParam) throws Exception {

        if (!StringUtils.hasText(country))
            throw new BadRequestException("Invalid parameter - Country not found");
        if (country.length() < 2)
            throw new BadRequestException("Invalid parameter - Invalid country found");

        if (!StringUtils.hasText(city))
            throw new BadRequestException("Invalid parameter - City not found");
        if (city.length() < 3)
            throw new BadRequestException("Invalid parameter - Invalid city found");

        String extractedApiKey = null;
        if (apiKey != null && apiKey.isPresent()) {
            extractedApiKey = apiKey.get();
        } else if (apiKeyQueryParam != null && apiKeyQueryParam.isPresent()) {
            extractedApiKey = apiKeyQueryParam.get();
        }

        LOGGER.info("[WeatherController] Reading weather data for {}, {}", country, city);
        return ResponseEntity.status(HttpStatus.OK).body(weatherService.findWeatherForCity(country, city));
    }

}
