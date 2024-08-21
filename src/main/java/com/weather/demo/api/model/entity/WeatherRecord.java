/**
 * Implements the entity record
 *
 * @Author Bandula Gamage
 * Date: 20/08/2024
 */

package com.weather.demo.api.model.entity;

import javax.persistence.*;

@Entity
@Table( name = "weather_record" )
public class WeatherRecord {

    @Id
//    @GeneratedValue( strategy = GenerationType.AUTO )
//    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @Column(name = "main")
    private String main;

    @Column(name = "description")
    private String description;

    @Column(name = "city_id")
    private String cityId;

    // Last updated / Time of data calculation
    @Column(name = "last_updated")
    private Long lastUpdated;

    public WeatherRecord() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "WeatherRecord{" +
                "id=" + id +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                ", cityId=" + cityId +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
