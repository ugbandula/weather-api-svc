/**
 * Implements the city repository
 *
 * @Author Bandula Gamage
 * Date: 20/08/2024
 */

package com.weather.demo.api.repository;

import com.weather.demo.api.model.entity.CityWeatherStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CityRepository extends JpaRepository<CityWeatherStatus, String> {

    @Query(value="select * from city where country=?1 and city=?2", nativeQuery = true)
    CityWeatherStatus findCityDetails(String country, String city);

}
