package com.GroupProject2122misproject33.demo;

import application.*;

import db.*;
import models.*;
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

    @GetMapping("/teruggaveFout")
    public String showFout() {
        return "errorContainsEmail";
    }

    @GetMapping("/Login")
    public String showLogin(Model model) {
        model.addAttribute("login", new LoginForm());
        return "Login";
    }

    /*
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
    public String registerNewStudent(String email, String firstname, String lastname, String password, String telephoneNumber, String dateOfBirth){
        Student student = new Student();
        if(!(email.contains("@"))) {
            return "/errorContainsEmail";
        }
        ArrayList<Student> allStudents = student.getAllStudents();
        for (Student item : allStudents) {
            if (item.getEmail().equals(email)) {
                return "/errorContainsEmail";
            }
        }
        student.toRegister(email, firstname, lastname, password, telephoneNumber, dateOfBirth);

        return "/FunctionScreenStudent";
    }

    @GetMapping("/RegisterAsLandlord")
    public String registerLandlord(Model model) {
        model.addAttribute("landlord", new Landlord());
        return "RegisterAsLandlord";
    }
    @PostMapping("/RegisterAsLandlord")
    public String registerNewLandlord(String email, String firstname, String lastname, String password, String telephoneNumber, String dateOfBirth){
        Landlord landlord = new Landlord();
        if(!(email.contains("@"))) {
            return "errorContainsEmail";
        }
        ArrayList<Landlord> allLandlords= new ArrayList<>();
        allLandlords = landlord.allLandlords();
        for(Landlord item : allLandlords){
            if(item.getEmail().equals(email))
                return "errorContainsEmail";
        }
        landlord.toRegister(email, firstname, lastname, password, telephoneNumber, dateOfBirth);
        return "FunctionScreenLandlord";
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
        model.addAttribute("location", new Location());
        return "Location";
    }

    @PostMapping("/Location")
    public String addLocation( String country, String city, String ZIP, String street, String number, double area, boolean insulated, String characteristics){
        Landlord landlord= new Landlord();
        landlord.addLocation(country, city, ZIP, street, number, area,insulated,characteristics);

        return"FunctionScreenLandlord";
    }

    @GetMapping("/Room")
    public String showRoom(Model model) {

        var landlords= new LandlordDAO().getAllLandlords();
        var locations= new LocationDAO().getAllLocations();
        model.addAttribute("room", new AddRoomModel());
        model.addAttribute("locations",locations);

        model.addAttribute("landlords", landlords);


        return "Room";
    }

    @PostMapping("/Room")
    public String addRoom(@ModelAttribute AddRoomModel roomModel){
        var landlord=new LandlordDAO().getLandlord(roomModel.getLandlordEmail());
        var location= new LocationDAO().getLocation(roomModel.getLocationID());
        landlord.addRoom(location, roomModel.getRoomnumber());

        return"FunctionScreenLandlord";
    }


    @GetMapping("/DeleteRoom")
    public String showDeleteRoom(Model model) {

        var landlords= new LandlordDAO().getAllLandlords();
        var locations= new LocationDAO().getAllLocations();
        var rooms= new RoomDAO().getAllRooms();
        model.addAttribute("room", new AddRoomModel());

        model.addAttribute("locations",locations);

        model.addAttribute("landlords", landlords);
        model.addAttribute("rooms", rooms);

        return "DeleteRoom";
    }

    @PostMapping("/DeleteRoom")
    public String deleteRoom(@ModelAttribute AddRoomModel roomModel){
        var landlord=new LandlordDAO().getLandlord(roomModel.getLandlordEmail());
        var room= new RoomDAO().getRoom(roomModel.getRoomid());
        landlord.deleteRoom(room);

        return"FunctionScreenLandlord";
    }



    @GetMapping("/Appliances")
    public String showAppliances(Model model) {
        var rooms= new RoomDAO().getAllRooms();
        var appliances= new ApplianceDAO().getAllAppliances();
        model.addAttribute("appliance", new AddApplianceModel());
        model.addAttribute("rooms",rooms);
        model.addAttribute("appliances", appliances);

        return "Appliances";
    }

    @PostMapping("/Appliances")
    public String addAppliance(@ModelAttribute AddApplianceModel appModel){

        var room=new RoomDAO().getRoom(appModel.getRoomid());

        room.addAppliance(appModel.getEnergyEfficiencyClass(),appModel.getModelIdentifier(), appModel.getAnnualEnergyConsumption(), appModel.getSupplierName(), appModel.getName(),
                appModel.getIsTempProportionate(), appModel.getIsTempDisproportionate(), appModel.getIsEnergyConservationMode());

        return"FunctionScreenLandlord";
    }



    @GetMapping("/Actions")
    public String showActions(Model model) {
        var appliances= new ApplianceDAO().getAllAppliances();

        model.addAttribute("action", new AddActionModel());
        model.addAttribute("appliances",appliances);

        return "Actions";
    }

    @PostMapping("/Actions")
    public String addAction(@ModelAttribute AddActionModel actionModel){

        var appliance=new ApplianceDAO().getAppliance(actionModel.getApplianceid());

        appliance.customizedEnergyConservationAction(actionModel.getDate(), actionModel.getName());
        return"FunctionScreenLandlord";
    }

    @GetMapping("/DeleteAppliance")
    public String showDeleteAppliance(Model model) {

        var locations= new LocationDAO().getAllLocations();
        var rooms= new RoomDAO().getAllRooms();
        var appliances= new ApplianceDAO().getAllAppliances();
        model.addAttribute("appliance", new AddApplianceModel());

        model.addAttribute("locations", locations);
        model.addAttribute("rooms", rooms);
        model.addAttribute("appliances",appliances);

        return "DeleteAppliance";
    }

    @PostMapping("/DeleteAppliance")
    public String deleteAppliance(@ModelAttribute AddApplianceModel appModel){
        var location=new LocationDAO().getLocation(appModel.getLocationID());
        var room=new RoomDAO().getRoom(appModel.getRoomid());
        var appliance= new ApplianceDAO().getAppliance(appModel.getApplianceid());
        room.deleteAppliance(appliance);

        return"FunctionScreenLandlord";
    }

    @GetMapping("/AddStudentToRoom")
    public String ShowAddStudentToRoom(Model model) {
        var rooms= new RoomDAO().getAllRooms();
        var students= new StudentDAO().getAllStudents();
        model.addAttribute("student",new AddStudentModel());
        model.addAttribute("rooms",rooms);
        model.addAttribute("students", students);

        return "AddStudentToRoom";
    }

    @PostMapping("/AddStudentToRoom")
    public String addStudentToRoom(@ModelAttribute AddStudentModel addStudentModel){

        var room=new RoomDAO().getRoom(addStudentModel.getRoomid());
        var student=new StudentDAO().getStudent(addStudentModel.getEmail());
        room.addStudent(student, addStudentModel.getContactPersoon());
        return"FunctionScreenLandlord";
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



    @GetMapping("/Tip")
    public String showTip(Model model) {
        var appliances= new ApplianceDAO().getAllAppliances();
        model.addAttribute("applianceModel", new SelectApplianceModel());
        model.addAttribute("appliances", appliances);
        return "Tip";
    }


    @PostMapping("/Tip")
    public String selectTip(@ModelAttribute SelectApplianceModel selectApplianceModel){

        var appliance=new ApplianceDAO().getAppliance(selectApplianceModel.getApplianceid());

        return "redirect:/stringprinter?applianceid="+ selectApplianceModel.getApplianceid();
    }

    @GetMapping("/stringprinter")
    public String showSP(@RequestParam String applianceid, Model model) {
        var appliance= new ApplianceDAO().getAppliance((Integer.parseInt(applianceid)));
        model.addAttribute("message", appliance.tipsAppliance());
        return "stringprinter";
    }




}