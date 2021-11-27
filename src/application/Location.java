package application;

import java.util.Objects;

public class Location {

    private String address;
    private enum insulationType {Insulated, notInsulated}
    private int area;
    private enum characteristics {semi_detached, detached, terraced}
    //lijst met appliances in de kamer (arraylist van type Appliances ofzo)
    public Location(Location location, String address, int area) {
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
        Location location1 = (Location) o;
        return area == location1.area
                && Objects.equals(address, location1.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, area);
    }
}
