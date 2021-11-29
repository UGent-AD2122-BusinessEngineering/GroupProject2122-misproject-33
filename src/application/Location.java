package application;

import java.util.Objects;
import java.util.ArrayList;

public class Location {

    private ArrayList<Landlord> students;
    //wat is dit?
    private String country;
    private String city;
    private int ZIP;
    //mss best niet in hoodletters... klopt dit dan nog?
    private int number;
    private String street;
    private int roomnumber;
    private int area;
    private enum insulationType {Insulated, notInsulated}
    public enum typeOfRoom {OnePersonRoom, TwoPersonRoom}
    private enum characteristics {semi_detached, detached, terraced}
    private ArrayList<Appliance> appLiancesInLocation;
    //lijst met appliances in de kamer (arraylist van type Appliances ofzo)
    public Location(String country, String city, int ZIP, int number, String street, int roomnumber, int area) {
        this.area = area;
        this.country = country;
        this.city = city;
        this.ZIP = ZIP;
        this.number = number;
        this.street = street;
        this.roomnumber = roomnumber;
        appLiancesInLocation = new ArrayList<Appliance>();
    }

    public String getCountry() {return country;}
    public void setCountry(String country) {this.country = country;}
    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}
    public int getZIP() {return ZIP;}
    public void setZIP(int ZIP) {this.ZIP = ZIP;}
    public int getNumber() {return number;}
    public void setNumber(int number) {this.number = number;}
    public String getStreet() {return street;}
    public void setStreet(String street) {this.street = street;}
    public int getRoomnumber() {return roomnumber;}
    public void setRoomnumber(int roomnumber) {this.roomnumber = roomnumber;}
    public int getArea() {return area;}

    //Wettelijk gestelde minimumnormen voor studentenkamers:
    //https://stad.gent/nl/wonen-bouwen/huren-verhuren/studentenkamers-verhuren/minimumnormen-voor-studentenkamers
    public void setArea(int area, typeOfRoom room) {
        try {
            switch (room) {
                case OnePersonRoom:
                    if (area >= 12)
                        this.area = area;
                    break;
                case TwoPersonRoom:
                    if(area >= 18)
                        this.area = area;
                    break;
            }
        } catch (Exception e) {
            System.out.println("The room does not meet the minimum standards of living quality.");
        }
    }

    //student toevoegen aan kamer
    public void addStudent(Landlord s) {
        students.add(s);
    }

    //student verwijderen van kamer
    public void removeStudent(Landlord s) {
        if (students.contains(s)) {
            students.remove(s);
        }
    }



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
