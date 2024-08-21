/**
 * Implements a Weather Status entity object
 *
 * @Author Bandula Gamage
 */
package com.weather.demo.api.model.dto.openweathersvc;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class OpenWeatherRecord {

    // City Id
    private Long id;

    // Base
    private String base;

    // Visibility
    private Integer visibility;

    // Last updated / Time of data calculation
    private Long dt;

    // Time zone
    private Integer timezone;

    // City name
    private String name;

    // cod
    private Integer cod;

    private List<Weather> weather;

    @Embedded
    private SysEmbeddable sys;

    public OpenWeatherRecord() {
        super();
        this.weather = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public SysEmbeddable getSys() {
        return sys;
    }

    public void setSys(SysEmbeddable sys) {
        this.sys = sys;
    }

    @Override
    public String toString() {
        return "CityWeatherStatus{" +
                "id=" + id +
                ", base='" + base + '\'' +
                ", visibility=" + visibility +
                ", dt=" + dt +
                ", timezone=" + timezone +
                ", name='" + name + '\'' +
                ", cod=" + cod +
                ", weather=" + weather +
                ", sys=" + sys +
                '}';
    }
}
