package application;

import db.RoomDAO;
import db.StudentDAO;
import java.util.ArrayList;

public class Room {
    public int roomnumber;
    public Location location;
    private ArrayList<MonthlyEnergyConsumption> monthlyEnergyConsumptions; //nog koppelen aan db,
    private ArrayList<Student> studentsPerRoom;
    public int roomID;

    public Room(int roomnumber, Location location) {
        this.roomnumber = roomnumber;
        this.location = location;
        monthlyEnergyConsumptions = new ArrayList<>();
        studentsPerRoom = new ArrayList<>();
    }

    public Room(int roomnumber, Location location, int roomID) {
        this.roomnumber = roomnumber;
        this.location = location;
        this.roomID = roomID;
    }

    public ArrayList<Student> getStudentPerRoom (Room room){
        StudentDAO studentDAO = new StudentDAO();
        return studentDAO.getStudents(room.getRoomID());
    }

    public int getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(int roomnumber) {
        this.roomnumber = roomnumber;
    }

    public ArrayList<MonthlyEnergyConsumption> getMonthlyEnergyConsumptions() {
        return monthlyEnergyConsumptions;
    }

    public void setMonthlyEnergyConsumptions(ArrayList<MonthlyEnergyConsumption> monthlyEnergyConsumptions) {
        this.monthlyEnergyConsumptions = monthlyEnergyConsumptions;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public Location getLocation() {
        return location;
    }


    public String addRoom(Landlord landlord, Location location, int roomnumber){
        String message = " ";
        Room room = new Room(roomnumber, location);
        RoomDAO roomDAO = new RoomDAO();
        room.roomID = roomDAO.save(room, location.getID(), landlord.email);
        return message = "The room has been succesfully added.";
    }

    public String deleteRoom(Room room){
        String message = "";
        RoomDAO roomDAO = new RoomDAO();
        roomDAO.deleteRoom(room.roomID);
        return message = "The room has been succesfully deleted.";
    }
}
