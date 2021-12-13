package com.GroupProject2122misproject33.demo;

import application.Room;
import application.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SpringBootApplication
@RestController
public class StudentController {

    public static void main(String[] args) {
        SpringApplication.run(EnergyConservationController.class, args);}

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/toevoegenStudent")
    public String studentToevoegen(@RequestParam(value = "Student", defaultValue = "Naam") Student student) {
        new Room().addStudent(student);
        return "toevoegenstudent";
    }

    /*
    @PostMapping("/toevoegenStudent")

    public String studentToevoegenKamer(@ModelAttribute Student student, Model model, RedirectAttributes redirAttrs) {
        University university = new University();
        university.addStudent(student);
        redirAttrs.addFlashAttribute("success", "Student werd toegevoegd");
        model.addAttribute("studenten", university.getStudents());
        return "redirect:/index";
    }
     */



}
