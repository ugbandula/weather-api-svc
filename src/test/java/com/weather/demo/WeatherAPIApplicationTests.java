package com.weather.demo;

import com.weather.demo.api.controller.WeatherController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WeatherAPIApplicationTests {

	@Autowired
	private WeatherController controller;

	@Test
	private void contextLoads() {
		Assertions.assertThat(controller).isNotNull();
	}

}
