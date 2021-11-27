package application;

import java.util.Objects;

public class Address {
    private String country;
    private String city;
    private String ZIP;
    private int number;
    private String street;
    private int roomnumber;

    public Address(String country, String city, String ZIP, int number, String street, int roomnumber) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return number == address.number
                && roomnumber == address.roomnumber
                && Objects.equals(country, address.country)
                && Objects.equals(city, address.city)
                && Objects.equals(ZIP, address.ZIP)
                && Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, ZIP, number, street, roomnumber);
    }

}

