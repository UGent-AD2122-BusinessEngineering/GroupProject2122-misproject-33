package com.GroupProject2122misproject33.demo;


import application.Location;
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

    @GetMapping("/index2")
    public String showStudenten() {

        return "index";
    }

    @GetMapping("/test2")
    public String showtest() {

        return "test";
    }

    @GetMapping("/location")
    public String locatieToevoegen() {

        return "Location";
    }

}


