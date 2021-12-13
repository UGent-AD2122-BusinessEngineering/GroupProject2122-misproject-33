package com.GroupProject2122misproject33.demo;


import application.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;



@SpringBootApplication
@Controller
public class EnergyConservationController {

	public static void main(String[] args) {
		SpringApplication.run(EnergyConservationController.class, args);}


	@GetMapping("/RequestAsStudent")
	public String toegvoegenStudent(@RequestParam(value = "Person", defaultValue = "Naam") Boolean student, String email, String firstname, String lastname, String password, String telephone_number, String date_of_birth){

		return new Person().toRegister(student, email, firstname, lastname, password, telephone_number, date_of_birth);


	}
	/*
	@PostMapping("/RequestAsStudent")
	public String studentToevoegenSubmit(@ModelAttribute Person person, Model model, RedirectAttributes redirAttrs) {

		redirAttrs.addFlashAttribute("success", "Student werd toegevoegd");
		model.addAttribute("studenten", university.getStudents());
		return "redirect:/index";
	}


	@GetMapping("/toevoegenStudent")
	public String studentToevoegen(@RequestParam(value = "Student", defaultValue = "Naam") Student student, Room room) {
		new Student().addStudent(student, room);
		return "toevoegenstudent";
	}


	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
	*/
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String getLoginForm(){
		return "Login";
	}

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String login(@ModelAttribute(name = "LoginForm") Person person, Model model){
		String email= person.getEmail();
		String password= person.getPassword();

		if(person.loginSucces(email, password)){
			return "FucntionScreen";
		}

		model.addAttribute("invalidCredentials", true);

		return "Login";
	}
	/*

	@RequestMapping(value="/test", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute Person person){
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("test");
		modelAndView.addObject("person",person);
		return modelAndView;
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
	 @GetMapping("/EnergyConservation")
	public String aboveRoomTemperature(@RequestParam(value = "temperature", defaultValue = "0.0") String temperature){
		new Action().aboveRoomTemperature(Double.parseDouble(temperature));
		return "EnergyConservation";
	}
	 */


}