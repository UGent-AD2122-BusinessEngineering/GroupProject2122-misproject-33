package com.GroupProject2122misproject33.demo;


import application.Location;
import application.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SpringBootApplication

@Controller
public class EnergyConservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnergyConservationApplication.class, args);
    }

    @GetMapping("/index")
    public String showStudenten() {

        return "index";
    }

    @GetMapping("/functionscreen")
    public String showFunction() {

        return "FunctionScreen";
    }

    @GetMapping("/ContactUs")
    public String showContact() {

        return "ContactUs";
    }

    @GetMapping("/Login")
    public String showLogin() {

        return "Login";
    }
    /*
    @PostMapping("/Login")
    public String goToLogin(@ModelAttribute(name = "login") Person person, Model model){
        person= Person.get
    }
    */
    @GetMapping("/Location")
    public String showLocation(Model model) {
        model.addAttribute("location", new Location());
        return "Location";
    }

    @PostMapping("/toevoegenStudent")
    public String studentToevoegenSubmit(@ModelAttribute Location location, Model model, RedirectAttributes redirAttrs) {

        university.addStudent(student);
        redirAttrs.addFlashAttribute("success", "Student werd toegevoegd");
        model.addAttribute("studenten", university.getStudents());
        return "redirect:/index";
    }


    @GetMapping("/Appliances")
    public String showAppliances() {

        return "Appliances";
    }

}


