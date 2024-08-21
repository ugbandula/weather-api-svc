/**
 * Implements a Weather status output object
 *
 * @Author Bandula Gamage
 * Date: 20/08/2024
 */

package com.weather.demo.api.model.dto;

public class CityWeatherStatusDto {

    // Detailed description of the weather status for the given city and country
    private String description;

    private String country;

    private String city;

    public CityWeatherStatusDto() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
