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
    }

    public HashMap<Room, Student> getRoomStudentHashmap(Landlord landlord) {
        RoomDAO roomDAO = new RoomDAO();
        ArrayList<Room> allRooms = roomDAO.getRooms(landlord.email);
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

    public ArrayList<Room> getRoomsLandLord(Landlord landlord) {
        RoomDAO roomDAO = new RoomDAO();
        this.roomsLandLord = roomDAO.getRooms(landlord.email);
        return this.roomsLandLord;
    }

    public static int getDagVdMaand() {
        return dagVdMaand;
    }

    public static int getMaandVhJaar() {
        return maandVhJaar;
    }

    public String addRoom(Landlord landlord, Location location, int roomnumber) {
        String message = "";
        Room room = new Room(roomnumber, location);
        RoomDAO roomDAO = new RoomDAO();
        room.roomID = roomDAO.save(room, location.getID(), landlord.email);
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

    /*public HashMap <Location, Student> getStudents(){
        //communiceren met db (zie vb)
        return hashmap die we uit de db halen...
    } staat in commentaar omdat we anders error krijgen
    */


    /*1. ik doe hier nog geen test voor eerste dag vd maand omdat me het beter lijkt dat te doen waar je de methode oproept
    //   ik heb hier wel tussen de instantievariabelen een "dagVdMaand" gezet dat ik getest heb en zeker werkt
    //2. moeten we de energieconsumptie per maand per landlord of per kamer bijhouden ? (anders moet ik dat hier nog wat aanpassen)
    public void getMonthlyEnergyConsumption(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What was the elektricity consumption (in kWh) this month?");
        int electricity = keyboard.nextInt();
        System.out.println("What was the gas consumption (in kWh) this month?");
        int gas = keyboard.nextInt();
        System.out.println("What was the water consumption (in m^3) this month?");
        int water = keyboard.nextInt();
        System.out.println("Okay thank you!");
        //ik ben nie zeker ofdat dit wel hoeft...
        MonthlyEnergyConsumption monthlyEnergyConsumption = new MonthlyEnergyConsumption(electricity, gas, water, maandVhJaar);
        //moet toegevoegd worden ad db
    }*/
}
