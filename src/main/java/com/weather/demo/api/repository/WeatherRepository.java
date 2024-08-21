/**
 * Implements the weather repository
 *
 * @Author Bandula Gamage
 * Date: 20/08/2024
 */

package com.weather.demo.api.repository;

import com.weather.demo.api.model.dto.openweathersvc.Weather;
import com.weather.demo.api.model.entity.WeatherRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WeatherRepository extends JpaRepository<WeatherRecord, String> {

    /**
     * Implements reading of a given order details
     * @return	List of {@link Weather} object
     */
    @Query(value="select w.* from weather where ws.country=?1 and archive_status = false order by pickup_date asc", nativeQuery = true)
    List<WeatherRecord> findWeatherRecordsForTheCity(String city);

}
