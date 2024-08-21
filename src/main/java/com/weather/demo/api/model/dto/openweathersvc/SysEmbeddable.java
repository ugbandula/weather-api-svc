/**
 * Implements a System Status embeddable entity object
 *
 * @Author Bandula Gamage
 */
package com.weather.demo.api.model.dto.openweathersvc;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SysEmbeddable {

    private Integer type;

    private Integer sysId;

    private String country;

    private Long sunrise;

    private Long sunset;

    public SysEmbeddable() {
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @JsonProperty("id")
    public Integer getSysId() {
        return sysId;
    }

    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getSunrise() {
        return sunrise;
    }

    public void setSunrise(Long sunrise) {
        this.sunrise = sunrise;
    }

    public Long getSunset() {
        return sunset;
    }

    public void setSunset(Long sunset) {
        this.sunset = sunset;
    }
}
