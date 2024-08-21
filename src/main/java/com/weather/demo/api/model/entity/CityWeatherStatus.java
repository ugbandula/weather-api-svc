/**
 * Implements the city weather status entity record
 *
 * @Author Bandula Gamage
 * Date: 20/08/2024
 */

package com.weather.demo.api.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "city" )
public class CityWeatherStatus {

    @Id
//    @GeneratedValue( strategy = GenerationType.AUTO )
//    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    // Base
    @Column(name = "country")
    private String country;

    // City name
    @Column(name = "city")
    private String city;

    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private List<WeatherRecord> weather;

    public CityWeatherStatus() {
        super();
        this.weather = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<WeatherRecord> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherRecord> weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "WeatherStatusUpdate{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", weather=" + weather +
                '}';
    }
}
