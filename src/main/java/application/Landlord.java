package application;

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

    public String addRoom(Location location, int roomnumber) {
        String message = "";
        Room room = new Room(roomnumber, location);
        RoomDAO roomDAO = new RoomDAO();
        room.roomID = roomDAO.save(room, location.getID(), this.email); //setRoomId
        return message += "The room has been succesfully added.";
    }

    public String deleteRoom(Room room) {
        String message = "";
        RoomDAO roomDAO = new RoomDAO();
        roomDAO.deleteRoom(room.roomID);
        return message += "The room has been succesfully deleted.";
    }

    public static String toRegister(String email, String firstname, String lastname, String password, String telephone_number, String date_of_birth) {
            LandlordDAO landlordDAO = new LandlordDAO();
            ArrayList<Landlord> allLandlords = landlordDAO.getAllLandlords();
            for (Landlord item : allLandlords) {
                if (item.getEmail().equals(email)) {
                    return "An account already exists with this email address.";
                }
            }
            Landlord landlord = new Landlord(email, firstname, lastname, PasswordHashing.doHashing(password), telephone_number, date_of_birth);
            landlordDAO.save(landlord);
        return "Your registration has been succesfull.";
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
}
