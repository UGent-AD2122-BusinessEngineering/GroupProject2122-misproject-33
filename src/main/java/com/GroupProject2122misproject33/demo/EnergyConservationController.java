package com.GroupProject2122misproject33.demo;

import application.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@SpringBootApplication
@RestController
public class EnergyConservationController {

	public static void main(String[] args) {
		SpringApplication.run(EnergyConservationController.class, args);}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String getLoginForm(){
		return "Login";
	}

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String login(@ModelAttribute(name = "LoginForm") LoginForm loginForm, Model model){
		String username= loginForm.getUsername();
		String password= loginForm.getPassword();

		if("admin".equals(username) && "admin".equals(password)){
			return "EnergyConservation";
		}

		model.addAttribute("invalidCredentials", true);

		return "Login";
	}


	@GetMapping("/RegisterAsStudent")
	public String personToevoegen(Model model) {

		model.addAttribute("student", new Student());
		return "RegisterAsStudent";
	}

	@PostMapping( "/RegisterAsStudent")
	public String personToevoegenSubmit(@ModelAttribute Person person, Model model, RedirectAttributes redirAttrs) {

		return "Login";
	}
	/* @GetMapping("/EnergyConservation")
	public String aboveRoomTemperature(@RequestParam(value = "temperature", defaultValue = "0.0") String temperature){
		new Action().aboveRoomTemperature(Double.parseDouble(temperature));
		return "EnergyConservation";
	}
	 */


}