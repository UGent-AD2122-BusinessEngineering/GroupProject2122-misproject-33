package application;

import java.util.ArrayList;
import java.util.Objects;

public class Room extends Appliance {
    private String roomnumber;
    private ArrayList<MonthlyEnergyConsumption> monthlyEnergyConsumptions; //nog koppelen aan db
    private ArrayList<Action> energyConservationActions; //nog koppelen aan db
    private ArrayList<Appliance> appliances; //nog koppelen aan db
    private ArrayList<Student> members;

    public Room() {

    }

    public Room(String roomnumber) {
        super();
        this.roomnumber = roomnumber;
        monthlyEnergyConsumptions = new ArrayList<>();
        energyConservationActions = new ArrayList<>();
        appliances = new ArrayList<>();
        members = new ArrayList<>();

    }

    public String getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(String roomnumber) {
        this.roomnumber = roomnumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomnumber, room.roomnumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomnumber);
    }

    @Override
    public String toString() {
        return "Room{" +
                ", roomnumber='" + roomnumber + '\'' +
                '}';
    }
}
