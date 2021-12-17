package application;

import db.ApplianceDAO;
import db.MonthlyEnergyConsumptionDAO;
import db.StudentDAO;
import java.util.ArrayList;

public class Room {
    public int roomnumber;
    public Location location;
    private ArrayList<MonthlyEnergyConsumption> monthlyEnergyConsumptions; //nog koppelen aan db,
    private ArrayList<Student> studentsPerRoom;
    private ArrayList<Appliance> appliancesInTheRoom;
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

    public ArrayList<Appliance> getAppliancesInTheRoom(){
        ArrayList<Appliance> appliancesInTheRoom = new ArrayList<>();
        ApplianceDAO applianceDAO = new ApplianceDAO();
        appliancesInTheRoom = applianceDAO.getAppliances(this.roomID);
        return appliancesInTheRoom;
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
        return "the student " + student.firstname + " " + student.lastname + " was successfully added to the room.";
    }

    public String deleteStudent(Student student){
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.deleteStudentFromRoom(student);
        return "the student " + student.getFirstname() + " " + student.getLastname() + " was successfully deleted.";
    }

    public String addAppliance(String energyEfficiencyClass, String modelIdentifier, int annualEnergyConsumption, String supplierName, String name,
                               boolean isTempProportionate, boolean isTempDisproportionate, boolean isEnergyConservationMode){
        Appliance appliance = new Appliance(energyEfficiencyClass, modelIdentifier, annualEnergyConsumption, supplierName, name, isTempProportionate, isTempDisproportionate, isEnergyConservationMode);
        ApplianceDAO applianceDAO = new ApplianceDAO();
        appliance.setApplianceID(applianceDAO.save(appliance, this.roomID));
        return "The appliance has been successfully added.";
    }

    public String deleteAppliance(Appliance appliance){
        ApplianceDAO applianceDAO = new ApplianceDAO();
        applianceDAO.deleteAppliance(appliance.getApplianceID());
        return "The appliance has been successfully deleted.";
    }

    public String addMonthlyEnergyConsumption (double electricity, double gas, double water, String month) {
        MonthlyEnergyConsumption monthlyEnergyConsumption = new MonthlyEnergyConsumption(electricity,gas,water,month);
        MonthlyEnergyConsumptionDAO monthlyEnergyConsumptionDAO = new MonthlyEnergyConsumptionDAO();
        monthlyEnergyConsumptionDAO.save(monthlyEnergyConsumption, this.roomID);
        return "Your monthly energy consumption of month " + month + " has been registered.";
    }

    public String deleteMonthlyEnergyConsumption (MonthlyEnergyConsumption monthlyEnergyConsumption){
        MonthlyEnergyConsumptionDAO monthlyEnergyConsumptionDAO = new MonthlyEnergyConsumptionDAO();
        monthlyEnergyConsumptionDAO.deleteMonthlyEnergyConsumption(monthlyEnergyConsumption.getMonthlyEnergyConsumptionId());
        return "MonthlyEnergyConsumption was successfully deleted.";
    }

}

