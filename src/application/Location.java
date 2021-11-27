package application;

import java.util.Objects;

public class Location extends Address {


    private enum insulationType {Insulated, notInsulated}
    private int area;
    private enum characteristics {semi_detached, detached, terraced}
    //lijst met appliances in de kamer (arraylist van type Appliances ofzo)
    public Location(String country, String city, String ZIP, int number, String street, int roomnumber) {
        super(country, city, ZIP, number, street, roomnumber);
        this.area = area;
    }

    public int getArea() {return area;}

    public void setArea(int area) {this.area = area;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Location location = (Location) o;
        return area == location.area;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), area);
    }
}
