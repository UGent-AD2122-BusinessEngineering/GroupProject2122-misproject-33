package application;

import db.ApplianceDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Room {
    public int roomnumber;
    public Location location;
    private ArrayList<MonthlyEnergyConsumption> monthlyEnergyConsumptions; //nog koppelen aan db
    private ArrayList<Action> energyConservationActions; //nog koppelen aan db
    private ArrayList<Student> students;
    public int roomID;
    public Landlord landlord;

    public Room(int roomnumber, Location location) {
        this.roomnumber = roomnumber;
        this.location = location;
        monthlyEnergyConsumptions = new ArrayList<>();
        energyConservationActions = new ArrayList<>();
        students = new ArrayList<>();
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

    public ArrayList<Action> getEnergyConservationActions() {
        return energyConservationActions;
    }

    public void setEnergyConservationActions(ArrayList<Action> energyConservationActions) {
        this.energyConservationActions = energyConservationActions;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
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

    public Landlord getLandlord() {
        return landlord;
    }

    public String addAppliance(String energyEfficiencyClass, String modelIdentifier, int annualEnergyConsumption, String supplierName, String name,
                               boolean isTempProportionate, boolean isTempDisproportionate, boolean isEnergyConservationMode, Room room){
        String message = " ";
        Appliance appliance = new Appliance(energyEfficiencyClass, modelIdentifier, annualEnergyConsumption, supplierName, name, isTempProportionate, isTempDisproportionate, isEnergyConservationMode);
        ApplianceDAO applianceDAO = new ApplianceDAO();
        appliance.setApplianceID(applianceDAO.save(appliance, room.roomID));
        return message = "The appliance has been succesfully added.";
    }

    public String getMonthlyEnergyConsumption (double electricity, double gas, double water, LocalDate month, int monthlyEnergyConsumptionId) {
        MonthlyEnergyConsumption monthlyEnergyConsumption = new MonthlyEnergyConsumption(electricity,gas,water,month,monthlyEnergyConsumptionId);
        return "Your monthly energy consumption of month " + month + " has been registered.";
    }
}
