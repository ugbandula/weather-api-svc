package com.weather.demo.api.controller;

import com.weather.demo.api.exception.BadRequestException;
import com.weather.demo.api.model.dto.CityWeatherStatusDto;
import com.weather.demo.api.service.WeatherService;
import com.weather.demo.util.Constants;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(classes = WeatherController.class)
@AutoConfigureMockMvc
public class WeatherControllerTests {

    private static final String PATH_WEATHER_END_POINT = "http://localhost:8080/api/weather";
    private static final String API_KEY = "EFE0007467EE4762B08FD4F090DA7E94";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext applicationContext;

    @MockBean
    private WeatherService weatherService;

    @Autowired
    private WeatherController weatherController;

    @Test
    void contextLoads() {
        assertThat(weatherService).isNotNull();
        assertThat(weatherController).isNotNull();
    }

    @Test
    public void securityPermissionsTest() throws Exception {
        String country = "AU";
        String city = "Sydney";
        String invalidCity = "Sy";
        String apiKeyValue = Constants.SAMPLE_API_KEYs[0];
        Optional<String> apiKey = Optional.of(apiKeyValue);
        Optional<String> invalidApiKey = Optional.of("TEST");

        /**
         * Checks all possible apiKey input methods
         */
        assertThat(weatherController.getWeatherForTheCity(country, city, null, apiKey)).isNotNull();
        assertThat(weatherController.getWeatherForTheCity(country, city, apiKey, null)).isNotNull();
    }

//    @Test
    public void weatherForecastTest() throws Exception {
        String country = "au";
        String city = "Sydney";
        String invalidCity = "Sy";
        String apiKeyValue = Constants.SAMPLE_API_KEYs[0];
        Optional<String> apiKey = Optional.of(apiKeyValue);

        /**
         * Checks whether the correct input and output delivers correct result
         */
        CityWeatherStatusDto weatherObj = weatherService.findWeatherForCity(country, city);
        assertThat(weatherObj).isNotNull();
        assertThat(weatherObj.getDescription()).isNotNull();
        assertTrue(weatherObj.getCountry().equals(country));
        assertTrue(weatherObj.getCity().equalsIgnoreCase(city));

        System.out.println("<ApplicationTest> Tests processing");
        System.out.println("<ApplicationTest> Weather Description: " + weatherObj.getDescription());
        System.out.println("<ApplicationTest> Api Key: " + apiKeyValue);

        /**
         * Checks all possible apiKey input methods
         */
        assertThat(weatherController.getWeatherForTheCity(country, city, null, apiKey)).isNotNull();
        assertThat(weatherController.getWeatherForTheCity(country, city, apiKey, null)).isNotNull();

        /**
         * Checks the returning Exceptions due to improper inputs
         */
        assertThrows(BadRequestException.class, () -> {
            weatherController.getWeatherForTheCity(country, invalidCity, null, apiKey);
        });
    }

    @Test
    public void testGetWeatherById404NotFound() throws Exception {
        String country = "XX";
        String city = "Melbourne";
        final String requestURI = PATH_WEATHER_END_POINT + "/" + country + "/" + city + "?apiKey=" + API_KEY;

        Mockito.when(weatherService.findWeatherForCity(country, city)).thenThrow(BadRequestException.class);

        mockMvc.perform(get(requestURI))
            .andDo(print());
    }

}
