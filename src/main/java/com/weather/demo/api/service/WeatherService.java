/**
 * Implements a Weather Service
 *
 * @Author Bandula Gamage
 * Date: 20/08/2024
 */
package com.weather.demo.api.service;

import com.weather.demo.api.exception.ResourceNotFoundException;
import com.weather.demo.api.model.dto.CityWeatherStatusDto;
import com.weather.demo.api.model.dto.openweathersvc.OpenWeatherRecord;
import com.weather.demo.api.model.dto.openweathersvc.Weather;
import com.weather.demo.api.model.entity.CityWeatherStatus;
import com.weather.demo.api.model.entity.WeatherRecord;
import com.weather.demo.api.repository.CityRepository;
import com.weather.demo.util.Utils;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherService.class);

    @Autowired
    private OpenWeatherMapService openWeatherMapService;

    @Autowired
    private CityRepository cityRepository;

    /**
     * Finds the weather condition for a given city in a country
     * @param country Country
     * @param city City
     * @return CityWeatherStatus object
     * @throws Exception
     */
    public CityWeatherStatusDto findWeatherForCity(String country, String city) throws Exception {
        CityWeatherStatusDto statusDto = new CityWeatherStatusDto();
        CityWeatherStatus cityRecord = cityRepository.findCityDetails(country.toUpperCase(), city.toLowerCase());

        if (cityRecord == null) {
            OpenWeatherRecord openWeatherRecord = openWeatherMapService.findWeatherForTheCity(country, city);
            if (openWeatherRecord == null)
                throw new ResourceNotFoundException("Weather information could not be found");
            cityRecord = generateEntityObj(openWeatherRecord);

            // Save the entity record
            cityRecord = cityRepository.save(cityRecord);

            LOGGER.info("[WeatherService.findWeatherForCity] Record updated: {}", cityRecord.toString());
        }

        return generateDto(cityRecord);
    }

    /**
     * Generates the DTO object using the entity object
     * @param entityObj {@link CityWeatherStatus} entity object
     * @return dto {@link CityWeatherStatusDto} object
     */
    private CityWeatherStatusDto generateDto(CityWeatherStatus entityObj) {
        CityWeatherStatusDto dtoObj = new CityWeatherStatusDto();
        dtoObj.setCountry(entityObj.getCountry());
        dtoObj.setCity(entityObj.getCity());
        dtoObj.setDescription(getWeatherDescription(entityObj.getWeather()));

        return dtoObj;
    }

    private CityWeatherStatus generateEntityObj(OpenWeatherRecord openRecord) {
        CityWeatherStatus entityRecord = new CityWeatherStatus();
        entityRecord.setId(Utils.getNewGuid());
        entityRecord.setCity(openRecord.getName().toLowerCase());
        entityRecord.setCountry(openRecord.getSys().getCountry().toUpperCase());

        for (Weather weather: openRecord.getWeather()) {
            WeatherRecord entityWeather = new WeatherRecord();
            entityWeather.setId(Utils.getNewGuid());
            entityWeather.setCityId(entityRecord.getId());
            entityWeather.setDescription(weather.getDescription());
            entityWeather.setMain(weather.getMain());
            entityWeather.setLastUpdated(System.currentTimeMillis());

            entityRecord.getWeather().add(entityWeather);
        }

        return entityRecord;
    }

    /**
     * Accumulates all weather conditions to a single string
     * @param records {@link WeatherRecord} records
     * @return Weather description
     */
    private String getWeatherDescription(List<WeatherRecord> records) {
        StringBuffer buff = new StringBuffer();

        for (WeatherRecord record : records) {
            if (buff.length() > 0)
                buff.append(", ");
            buff.append(record.getDescription());
        }
        return buff.toString();
    }
}
