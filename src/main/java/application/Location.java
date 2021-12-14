package application;

import db.LocationDAO;

import java.util.ArrayList;
import java.util.Objects;
import db.RoomDAO;

public class Location {
    private int ID;
    private String country;
    private String city;
    private String ZIP;
    private String number;
    private String street;
    private double area;
    private boolean insulated;
    private String characteristics;

    public Location() {
    }

    public Location(String country, String city, String ZIP, String street, String number, double area, boolean insulated, String characteristics) {
        this.area = area;
        this.country = country;
        this.city = city;
        this.ZIP = ZIP;
        this.number = number;
        this.street = street;
        this.insulated = insulated;
        this.characteristics = characteristics;
    }

    public Location(String country, String city, String ZIP, String street, String number, double area, boolean insulated, String characteristics, int ID) {
        this.ID = ID;
        this.country = country;
        this.city = city;
        this.ZIP = ZIP;
        this.number = number;
        this.street = street;
        this.area = area;
        this.insulated = insulated;
        this.characteristics = characteristics;
    }

    public ArrayList<Location> getAllLocationsPerLandlord(Landlord landlord){
        RoomDAO roomDAO = new RoomDAO();
        ArrayList<Room> allRooms = roomDAO.getRooms(landlord.email);
        ArrayList<Location> locations = new ArrayList<Location>();
        for (Room item : allRooms){
            locations.add(item.location);
        }
        return locations;
    }





    public int getID() {return ID;}
    public String getCountry() {return country;}
    public String getCity() {return city;}
    public String getZIP() {return ZIP;}
    public String getNumber() {return number;}
    public String getStreet() {return street;}
    public double getArea() {return area;}
    public boolean isInsulated() {
        return insulated;
    }
    public String getCharacteristics() {
        return characteristics;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    //wanneer alle kamers van een locatie verwijderd zijn wordt de locatie vanzelf verwijderd
    public String toString(){
        String message = "Country: " + country + "\n" + "City: " + city;
        return message;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return  ZIP == location.ZIP && number == location.number && area == location.area && Objects.equals(country, location.country) && Objects.equals(city, location.city) && Objects.equals(street, location.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash( country, city, ZIP, number, street, area);
    }
}