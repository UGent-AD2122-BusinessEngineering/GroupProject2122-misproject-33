package com.GroupProject2122misproject33.demo;

import application.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;


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

    @GetMapping("/RegisterAsStudent")
    public String registerStudent(Model model) {
        model.addAttribute("student", new Student());
        return "RegisterAsStudent";
    }
    @PostMapping("/RegisterAsStudent")
    public String registerNewStudent(@ModelAttribute String email, String firstname, String lastname, String password, String telephoneNumber, String dateOfBirth){
        Student student = new Student();
        ArrayList<Student> allStudents = student.getAllStudents();
        for (Student item : allStudents) {
            if (item.getEmail().equals(email)) {
                return "An account already exists with this email address.";
            }
        }
        student.toRegister(email, firstname, lastname, password, telephoneNumber, dateOfBirth);
        //System.out.println(student.getReport());
        return "/test2";
    }

    @GetMapping("/RegisterAsLandlord")
    public String registerLandlord(Model model) {
        model.addAttribute("landlord", new Landlord());
        return "RegisterAsLandlord";
    }
    @PostMapping("/RegisterAsLandlord")
    public String registerNewLandlord(@ModelAttribute String email, String firstname, String lastname, String password, String telephoneNumber, String dateOfBirth){
        Landlord landlord = new Landlord();
        ArrayList<Landlord> allLandlords= new ArrayList<>();
        allLandlords = landlord.allLandlords();
        for(Landlord item : allLandlords){
            if(item.getEmail().equals(email))
                return "An account already exists with this email adress.";
        }
        landlord.toRegister(email, firstname, lastname, password, telephoneNumber, dateOfBirth);
        return "/test2";
    }

    @GetMapping("/FunctionScreenLandlord")
    public String showFunctionLandlord() {
        return "FunctionScreenLandlord";
    }

    @GetMapping("/FunctionScreenStudent")
    public String showFunctionStudent() {
        return "FunctionScreenStudent";
    }

    @GetMapping("/Location")
    public String showLocation(Model model) {
        model.addAttribute("room", new Landlord());
        return "Location";
    }
    /*
    @PostMapping("/Location")
    public String addLocation(@ModelAttribute String country, String city, String ZIP, String street, String number, double area, boolean insulated, String characteristics, int roomnumber){
        Landlord landlord= new Landlord();
        Location location1 = new Location();
        landlord.addLocationAndRoom(country, city, ZIP, street, number, area,insulated,characteristics, roomnumber);
        landlord.addRoom(location1,roomnumber);
        return"FunctionScreenLandlord";
    }*/

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

    @GetMapping("/MonthlyEnergyConsumption")
    public String MonthlyEnergyCons(Model model) {
        model.addAttribute("monthlyenergyconsumption", new MonthlyEnergyConsumption());
        return "MonthlyEnergyConsumption";
    }

    @PostMapping("/MonthlyEnergyConsumption")
    public String addAppliance(@ModelAttribute double electricity, double gas, double water, LocalDate month){
        Room room= new Room();
        room.addMonthlyEnergyConsumption(electricity, gas, water, month);
        return "/test2";
    }

    @GetMapping("/Actions")
    public String givesActions(Model model) {
        model.addAttribute("actions", new Action());
        return "Actions";
    }




    /*@Autowired
    private UserRepository userRepo;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Landlord());

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(Landlord landlord) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(landlord.getPassword());
        landlord.setPassword(encodedPassword);

        userRepo.save(landlord);

        return "register_success";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<Landlord> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }*/

}