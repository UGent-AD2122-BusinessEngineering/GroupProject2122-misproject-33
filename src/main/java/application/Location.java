package application;

import db.LocationDAO;
import java.util.Objects;

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

    public int getID() {return ID;}
    public void setId(int ID) {this.ID = ID;}
    public void setArea(int area) {this.area = area;}
    public String getCountry() {return country;}
    public void setCountry(String country) {this.country = country;}
    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}
    public String getZIP() {return ZIP;}
    public void setZIP(String ZIP) {this.ZIP = ZIP;}
    public String getNumber() {return number;}
    public void setNumber(String number) {this.number = number;}
    public String getStreet() {return street;}
    public void setStreet(String street) {this.street = street;}
    public double getArea() {return area;}
    public boolean isInsulated() {
        return insulated;
    }
    public String getCharacteristics() {
        return characteristics;
    }

    public String addLocation(String country, String city, String ZIP, String street, String number, double area, boolean insulated, String characteristics){
        Location location = new Location(country, city, ZIP, street, number, area, insulated, characteristics);
        LocationDAO locationDAO = new LocationDAO();
        location.ID = locationDAO.saveLocation(location);
        return "The location has been succesfully added.";
    }

    //wanneer alle kamers van een locatie verwijderd zijn wordt de locatie vanzelf verwijderd
    public String toString(Location location){
        String message = "Country: " + country + "\n" + "City: " + city;
        return message;
    }


    /*
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

    /*public void addAppliance(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please mind your grammar for the following question.");
        System.out.println("What is the type of the appliance (for example: dishwasher)?");
        String applianceType = keyboard.nextLine();
        System.out.println("What is the name of the supplier?");
        String supplierName = keyboard.nextLine();
        System.out.println("What is the EEI (energyeffeciencyindex)?");
        int eei = keyboard.nextInt();
        boolean temperatuursgebonden = true;
        boolean oké = false;
        while(oké = false) {
            System.out.println("Is het temperatuursgebonden?");
            String s1 = keyboard.nextLine();
            if (s1.equalsIgnoreCase("ja"))
                temperatuursgebonden = true;
            if (s1.equalsIgnoreCase("nee"))
                temperatuursgebonden = false;
            else
                System.out.println("Uw antwoord is niet wat we verwachten, antwoord ja of nee alstublieft");
        }
        Appliances appliance = new Appliances(applianceType, eei, supplierName, temperatuursgebonden);
        appliancesInLocation.add(appliance);
        //moet toegevoegd worden ad database
    }*/

/*
    public void getMonthlyEnergyConsumptions () {
        //geeft AL terug
        //communiceren met db (zie vb)
    }

 */


    /*
    //student toevoegen aan kamer
    public void addStudent(Landlord s) {
        students.add(s);
    }

    //student verwijderen van kamer
    public void removeStudent(Landlord s) {
        if (students.contains(s)) {
            students.remove(s);
        }
    }*/

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