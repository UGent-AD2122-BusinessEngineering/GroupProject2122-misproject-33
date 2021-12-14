package application;

import db.ApplianceDAO;
import db.MonthlyEnergyConsumptionDAO;
import db.StudentDAO;

import java.time.LocalDate;
import java.util.ArrayList;

public class Room {
    public int roomnumber;
    public Location location;
    private ArrayList<MonthlyEnergyConsumption> monthlyEnergyConsumptions; //nog koppelen aan db,
    private ArrayList<Student> studentsPerRoom;
    public int roomID;

    public Room() {
    }

    public Room(int roomnumber, Location location) {
        this.roomnumber = roomnumber;
        this.location = location;
        monthlyEnergyConsumptions = new ArrayList<>();
        studentsPerRoom = new ArrayList<>();
    }

    public Room(int roomnumber, Location location, int roomID) {
        this.roomnumber = roomnumber;
        this.location = location;
        this.roomID = roomID;
        monthlyEnergyConsumptions = getMonthlyEnergyConsumptionPerRoom(roomID);
        studentsPerRoom = getStudentsPerRoom(roomID);
    }

    public ArrayList<Student> getStudentsPerRoom(int roomID){
        ArrayList <Student> studentsPerRoom = new ArrayList<>();
        StudentDAO studentDAO = new StudentDAO();
        studentsPerRoom = studentDAO.getStudents(roomID);
        return studentsPerRoom;
    }

    public ArrayList<MonthlyEnergyConsumption> getMonthlyEnergyConsumptionPerRoom(int roomID){
        ArrayList <MonthlyEnergyConsumption> monthlyEnergyConsumptionsPerRoom = new ArrayList<>();
        MonthlyEnergyConsumptionDAO monthlyEnergyConsumptionDAO = new MonthlyEnergyConsumptionDAO();
        monthlyEnergyConsumptionsPerRoom = monthlyEnergyConsumptionDAO.getAllMonthlyEnergyConsumptions(roomID);
        return monthlyEnergyConsumptionsPerRoom;
    }

    public int getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(int roomnumber) {
        this.roomnumber = roomnumber;
    }

    public ArrayList<MonthlyEnergyConsumption> getMonthlyEnergyConsumptions() {
        return monthlyEnergyConsumptions;
    }

    public void setMonthlyEnergyConsumptions(ArrayList<MonthlyEnergyConsumption> monthlyEnergyConsumptions) {
        this.monthlyEnergyConsumptions = monthlyEnergyConsumptions;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;

    }

    public Location getLocation() {
        return location;
    }

    public String addStudent(Student student, Boolean contactPersoon){
        StudentDAO studentDAO = new StudentDAO();
        student.setContactPerson(contactPersoon);
        studentDAO.save(student);
        studentDAO.update(student, roomID);
        return "the student " + student.firstname + " " + student.lastname + " was succesfully added to the room.";
    }

    public String deleteStudent(Student student){
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.update(student, 0);
        return "the student " + student.getFirstname() + " " + student.getLastname() + " was succesfully deleted.";
    }

    //String energyEfficiencyClass, String modelIdentifier, int annualEnergyConsumption, String supplierName, String name, boolean isTempProportionate, boolean isTempDisproportionate, boolean isEnergyConservationMode, Room room

    /*public String addAppliance(Appliance appliance){
        ApplianceDAO applianceDAO = new ApplianceDAO();
        return "The appliance has been succesfully added.";
    }*/

    public String deleteAppliance(Appliance appliance){
        ApplianceDAO applianceDAO = new ApplianceDAO();
        applianceDAO.deleteAppliance(appliance.getApplianceID());
        return "The appliance has been successfully deleted.";
    }

    public String addMonthlyEnergyConsumption (double electricity, double gas, double water, LocalDate month, Room room, ArrayList<MonthlyEnergyConsumption> monthlyEnergyConsumptions) {
        MonthlyEnergyConsumption monthlyEnergyConsumption = new MonthlyEnergyConsumption(electricity,gas,water,month);
        MonthlyEnergyConsumptionDAO monthlyEnergyConsumptionDAO = new MonthlyEnergyConsumptionDAO();
        monthlyEnergyConsumptionDAO.save(monthlyEnergyConsumption, room.roomID);
        return "Your monthly energy consumption of month " + month + " has been registered.";
    }

    public String deleteMonthlyEnergyConsumption (MonthlyEnergyConsumption monthlyEnergyConsumption){
        MonthlyEnergyConsumptionDAO monthlyEnergyConsumptionDAO = new MonthlyEnergyConsumptionDAO();
        monthlyEnergyConsumptionDAO.deleteMonthlyEnergyConsumption(monthlyEnergyConsumption.getMonthlyEnergyConsumptionId());
        return "MonthlyEnergyConsumption was successfully deleted.";
    }

}