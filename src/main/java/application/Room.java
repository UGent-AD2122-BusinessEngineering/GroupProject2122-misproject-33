package application;

import java.util.ArrayList;
import java.util.Objects;

public class Room extends Appliance {
    public String roomnumber;
    public Location location;
    public Landlord landlord;
    private ArrayList<MonthlyEnergyConsumption> monthlyEnergyConsumptions; //nog koppelen aan db
    private ArrayList<Action> energyConservationActions; //nog koppelen aan db
    private ArrayList<Student> students;
    public int roomID;

    public Room() {
    }

    public Room(ArrayList<MonthlyEnergyConsumption> monthlyEnergy, ArrayList<Action> energyConservationActions, ArrayList<Student> students, String roomnumber, int roomID) {
        super();
        this.roomID = roomID;
        this.roomnumber = roomnumber;
        monthlyEnergyConsumptions = new ArrayList<>();
        energyConservationActions = new ArrayList<>();
        students = new ArrayList<>();
    }

    public String getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(String roomnumber) {
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
}
