package application;

import java.util.Objects;

public class Room {
    //private Room room;
    //ik begrijp die instantvariabele niet echt... heb hem ook weggedaan in de constructor en de rest
    private String address;
    private enum insulationType {Insulated, notInsulated}
    private int area;
    private enum characteristics {semi_detached, detached, terraced}
    //lijst met appliances in de kamer (arraylist van type Appliances ofzo)
    public Room(Room room, String address, int area) {
        this.address = address;
        this.area = area;
    }

    //addAppliance methode

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room1 = (Room) o;
        return area == room1.area
                && Objects.equals(address, room1.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, area);
    }
}
