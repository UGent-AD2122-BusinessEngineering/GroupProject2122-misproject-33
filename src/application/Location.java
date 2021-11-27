package application;

import java.util.Objects;

public class Location {

    private String country;
    private String city;
    private String ZIP;
    private int number;
    private String street;
    private int roomnumber;
    private enum insulationType {Insulated, notInsulated}
    private int area;
    private enum characteristics {semi_detached, detached, terraced}
    //lijst met appliances in de kamer (arraylist van type Appliances ofzo)
    public Location(String country, String city, String ZIP, int number, String street, int roomnumber, int area) {
        this.area = area;
        this.country = country;
        this.city = city;
        this.ZIP = ZIP;
        this.number = number;
        this.street = street;
        this.roomnumber = roomnumber;
    }

    public String getCountry() {return country;}

    public void setCountry(String country) {this.country = country;}

    public String getCity() {return city;}

    public void setCity(String city) {this.city = city;}

    public String getZIP() {return ZIP;}

    public void setZIP(String ZIP) {this.ZIP = ZIP;}

    public int getNumber() {return number;}

    public void setNumber(int number) {this.number = number;}

    public String getStreet() {return street;}

    public void setStreet(String street) {this.street = street;}

    public int getRoomnumber() {return roomnumber;}

    public void setRoomnumber(int roomnumber) {this.roomnumber = roomnumber;}

    public int getArea() {return area;}

    public void setArea(int area) {this.area = area;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Location location = (Location) o;
        return number == location.number
                && roomnumber == location.roomnumber
                && area == location.area
                && Objects.equals(country, location.country)
                && Objects.equals(city, location.city)
                && Objects.equals(ZIP, location.ZIP)
                && Objects.equals(street, location.street);
    }

    public int hashCode() { return Objects.hash(super.hashCode(), country, city, ZIP, number, street, roomnumber, area);}

    public String toString() {
        return "Address: " + '\'' + "Country: " + getCountry() + '\'' + "City: " + getCity() + '\'' + "ZIP: " + getZIP() + '\''
                + "House number: " + getNumber() + "Street: " + getStreet() + '\'' + "Roomnumber=" + getRoomnumber();
    }

}
