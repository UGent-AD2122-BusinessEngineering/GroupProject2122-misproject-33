package com.GroupProject2122misproject33.demo;


import application.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
        return "FunctionScreenLandlord";
    }

    @GetMapping("/ContactUs")
    public String showContact() {
        return "ContactUs";
    }

    @GetMapping("/Login")
    public String showLogin(Model model) {
        model.addAttribute("login", new LoginForm());
        return "Login";
    }/*
    @PostMapping("/Login")
    public String goToLogin(@ModelAttribute(name = "login") Person person, Model model){
        person= Person.get
    }*/


    @GetMapping("/Location")
    public String showLocation(Model model) {
        model.addAttribute("location", new Location());
        return "Location";
    }

    @PostMapping("/Location")
    public String addLocation(@ModelAttribute String country, String city, String ZIP, String street, String number, double area, boolean insulated, String characteristics){

    Landlord landlord= new Landlord();
    landlord.addLocation(country, city, ZIP, street, number, area,insulated,characteristics);


    return "FunctionScreenLandlord";
    }

    @GetMapping("/Appliances")
    public String showAppliances(Model model) {
        model.addAttribute("appliance", new Appliance());
        return "Appliances";
    }

    @PostMapping("/Appliances")
    public String addAppliance(@ModelAttribute String energyEfficiencyClass, String modelIdentifier, int annualEnergyConsumption, String supplierName, String name,
                               boolean isTempProportionate, boolean isTempDisproportionate, boolean isEnergyConservationMode){

        Room room= new Room();
        room.addAppliance(energyEfficiencyClass, modelIdentifier, annualEnergyConsumption, supplierName, name,
        isTempProportionate, isTempDisproportionate, isEnergyConservationMode);

        return "/test2";
    }

    /*
    @PostMapping("/Appliances")
    public String applianceToevoegen(@ModelAttribute Appliance appliance, Model model, RedirectAttributes redirAttrs) {
        Room room= new Room();
        university.addStudent(student);
        redirAttrs.addFlashAttribute("success", "Student werd toegevoegd");
        model.addAttribute("studenten", university.getStudents());
        return "redirect:/index";
    }
    */

    @GetMapping("/RegisterAsStudent")
    public String registerStudent(Model model) {
        model.addAttribute("student", new Student());
        return "RegisterAsStudent";
    }/*
    @PostMapping("/RegisterAsStudent")
    public String studentToevoegen(@ModelAttribute(name= "student") Student student, Model model) {
        model.addAttribute("inlogtudent", new Student());
        return "FunctionScreenStudent";
    }*/

    @GetMapping("/RegisterAsLandlord")
    public String registerLandlord(Model model) {
        model.addAttribute("landlord", new Landlord());
        return "RegisterAsLandlord";
    }/*
    @PostMapping("/RegisterAsLandlord")
    public String studentToevoegen(@ModelAttribute(name= "landlord") Landlord landlord, Model model) {
        Landlord landlord1 = new Landlord();
        model.addAttribute("inlogLandlord", landlord.toRegister());
        return "FunctionScreenLandlord";
    }*/

    @GetMapping("/MonthlyEnergyConsumption")
    public String MonthlyEnergyCons(Model model) {
        model.addAttribute("monthlyenergyconsumption", new MonthlyEnergyConsumption());
        return "MonthlyEnergyConsumption";
    }

    @GetMapping("/Actions")
    public String givesActions(Model model) {
        model.addAttribute("actions", new Action());
        return "Actions";
    }

    @GetMapping("/FunctionScreen")
    public String givesFunctions(Model model) {

        return "FunctionScreenLandlord";
    }





}


