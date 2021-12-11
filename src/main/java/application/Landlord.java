package application;

import db.RoomDAO;
import db.StudentDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Landlord extends Person {
    private static int dagVdMaand = LocalDate.now().getDayOfMonth();
    private static int maandVhJaar = LocalDate.now().getMonthValue();
    private HashMap<Room, Student> contactsRoom; //?
    private ArrayList<Room> roomsLandLord;

    public Landlord(String email, String firstname, String lastname, String telephone_number, String date_of_birth, String password) {
        super(email, firstname, lastname, telephone_number, date_of_birth, password);
        contactsRoom = new HashMap<>(); //nog koppelen aan db
    }

    public ArrayList<Room> getRoomsLandLord(Landlord landlord) {
        RoomDAO roomDAO = new RoomDAO();
        roomsLandLord = roomDAO.getRooms(landlord.email);
        return roomsLandLord;
    }

    public static int getDagVdMaand() {
        return dagVdMaand;
    }

    public static int getMaandVhJaar() {
        return maandVhJaar;
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
