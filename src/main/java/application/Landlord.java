package application;

import db.LocationDAO;
import db.RoomDAO;
import db.StudentDAO;
import db.LandlordDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Landlord extends Person {
    private static int dagVdMaand = LocalDate.now().getDayOfMonth();
    private static int maandVhJaar = LocalDate.now().getMonthValue();
    private HashMap<Room, Student> roomStudentHashMap;
    private ArrayList<Room> roomsLandLord;

    public Landlord() {
    }

    public Landlord(String email, String firstname, String lastname, String password, String telephone_number, String date_of_birth) {
        super(email, firstname, lastname, password, telephone_number, date_of_birth);
        this.roomsLandLord = getRoomsLandLord(email);
    }

    public ArrayList<Landlord> allLandlords (){
        LandlordDAO landlordDAO = new LandlordDAO();
        ArrayList allLandlords = new ArrayList<>();
        allLandlords = landlordDAO.getAllLandlords();
        return allLandlords;
    }

    public HashMap<Room, Student> getRoomStudentHashmap() {
        RoomDAO roomDAO = new RoomDAO();
        ArrayList<Room> allRooms = roomDAO.getRooms(this.email);
        StudentDAO studentDAO = new StudentDAO();
        HashMap<Room, Student> roomStudentHashMap = new HashMap<Room, Student>();
        for (Room item : allRooms) {
            ArrayList<Student> studentsOfRoom = studentDAO.getStudents(item.roomID);
            for (Student item1 : studentsOfRoom) {
                if (item1.getIsContactPerson())
                    roomStudentHashMap.put(item, item1);
            }
        }
        this.roomStudentHashMap = roomStudentHashMap;
        return this.roomStudentHashMap;
    }

    public ArrayList<Location> getAllLocationsPerLandlord(){
        RoomDAO roomDAO = new RoomDAO();
        ArrayList<Room> allRooms = roomDAO.getRooms(this.email);
        ArrayList<Location> locations = new ArrayList<Location>();
        for (Room item : allRooms){
            locations.add(item.location);
        }
        return locations;
    }


    public ArrayList<Room> getRoomsLandLord(String email) {
        RoomDAO roomDAO = new RoomDAO();
        this.roomsLandLord = roomDAO.getRooms(email);
        return this.roomsLandLord;
    }

    public static int getDagVdMaand() {
        return dagVdMaand;
    }

    public static int getMaandVhJaar() {
        return maandVhJaar;
    }

    public void addRoom(Location location, int roomnumber) {
        Room room = new Room(roomnumber, location);
        RoomDAO roomDAO = new RoomDAO();
        room.roomID = roomDAO.save(room, this.email); //setRoomId
    }



    public void addLocation(String country, String city, String ZIP, String street, String number, double area, boolean insulated, String characteristics){
        Location location = new Location(country, city, ZIP, street, number, area, insulated, characteristics);
        LocationDAO locationDAO = new LocationDAO();
        location.setID(locationDAO.saveLocation(location));
    }

    public void deleteRoom(Room room) {
        RoomDAO roomDAO = new RoomDAO();
        roomDAO.deleteRoom(room.roomID);
    }

    public void toRegister(String email, String firstname, String lastname, String password, String telephone_number, String date_of_birth) {
            LandlordDAO landlordDAO = new LandlordDAO();
            ArrayList<Landlord> allLandlords = landlordDAO.getAllLandlords();
            Landlord landlord = new Landlord(email, firstname, lastname, PasswordHashing.doHashing(password), telephone_number, date_of_birth);
            landlordDAO.save(landlord);
}

    public static Object login(String email, String password) {
        LandlordDAO landlordDAO = new LandlordDAO();
        ArrayList<Landlord> allLandlords = landlordDAO.getAllLandlords();
        for (Landlord item : allLandlords) {
            if (item.getEmail().equals(email)) {
                if (item.password.equals(PasswordHashing.doHashing(password))) {
                    return item;
                }
            }
        }
        return "Your e-mail or password was incorrect.";
    }

    public String getReport () {
        String message = "";
        RoomDAO roomDAO = new RoomDAO();
        ArrayList<Room> roomsOfTheLandlord = new ArrayList<>();
        roomsOfTheLandlord = roomDAO.getRooms(this.email);
        if(roomsOfTheLandlord.isEmpty()){
            message += "There are no registered rooms found.";
        }
        else{
            for (Room item : roomsOfTheLandlord){
            ArrayList<MonthlyEnergyConsumption> monthlyEnergyConsumptionsRoom = new ArrayList<>();
            monthlyEnergyConsumptionsRoom = item.getMonthlyEnergyConsumptionPerRoom(item.roomID);
            message += "\nRoom " + item.getRoomnumber() + ": ";
                if(monthlyEnergyConsumptionsRoom.isEmpty()){
                message += "\nThis room does not have any monthly energy consumption reports.";
                }
                else{
                    for(MonthlyEnergyConsumption item2 : monthlyEnergyConsumptionsRoom){
                        message += " ("+item2.getMonth()+") ";
                        message += "\n" + "Water: " + item2.getWater() + "m^3" + "\nElectricity: " + item2.getElectricity()
                                + " kWh" + "\nGas: " + item2.getGas() + " kWh";
                    }
                }
            }
        }
        return message;
    }
}
