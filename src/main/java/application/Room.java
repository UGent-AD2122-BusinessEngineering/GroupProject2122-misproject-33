package application;

import db.ApplianceDAO;
import db.RoomDAO;
import db.StudentDAO;
import java.util.ArrayList;

public class Room {
    public int roomnumber;
    public Location location;
    private ArrayList<MonthlyEnergyConsumption> monthlyEnergyConsumptions; //nog koppelen aan db,
    private ArrayList<Student> studentsPerRoom;
    public int roomID;

    public Room() {
    }

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

    public String addStudent(Student student){
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.save(student);
        studentDAO.update(student, roomID);
        return "the student " + student.firstname + " " + student.lastname + " was succesfully added to the room.";
    }

    public String addAppliance(String energyEfficiencyClass, String modelIdentifier, int annualEnergyConsumption, String supplierName, String name,
                               boolean isTempProportionate, boolean isTempDisproportionate, boolean isEnergyConservationMode, Room room){
        Appliance appliance = new Appliance(energyEfficiencyClass, modelIdentifier, annualEnergyConsumption, supplierName, name, isTempProportionate, isTempDisproportionate, isEnergyConservationMode);
        ApplianceDAO applianceDAO = new ApplianceDAO();
        appliance.setApplianceID(applianceDAO.save(appliance, room.roomID));
        return "The appliance has been succesfully added.";
    }

    public String deleteAppliance(Appliance appliance){

        ApplianceDAO applianceDAO = new ApplianceDAO();
        applianceDAO.deleteAppliance(appliance.getApplianceID());
        return "The appliance has been succesfully deleted.";
    }

}
