package com.GroupProject2122misproject33.demo;

import application.EnergyConservationActions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EnergyConservationController {

	public static void main(String[] args) {
		SpringApplication.run(EnergyConservationController.class, args);}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/Conservation")
	public boolean aboveRoomTemperature(@RequestParam(value = "temperature", defaultValue = "0.0") String temperature){
		return new EnergyConservationActions().aboveRoomTemperature(Double.parseDouble(temperature));
	}

	@GetMapping("/Conservation")
	public boolean lowRoomTemperature(@RequestParam(value = "temperature", defaultValue = "0.0") String temperature){
		return new EnergyConservationActions().lowRoomTemperature(Double.parseDouble(temperature));
	}
}