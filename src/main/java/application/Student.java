package application;
import db.RoomDAO;
import db.StudentDAO;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Student extends Person {

    private boolean isContactPerson;

    public Student(String email, String firstname, String lastname, String password, String telephoneNumber, String dateOfBirth) {
        super(email, firstname, lastname, password, telephoneNumber, dateOfBirth);
        this.isContactPerson = false;
    }

    public Student(String email, String firstname, String lastname, String password, String telephoneNumber, String dateOfBirth, boolean isContactPerson) {
        super(email, firstname, lastname, password, telephoneNumber, dateOfBirth);
        this.isContactPerson = isContactPerson;
    }


    public void setContactPerson(boolean contactPerson) {
        isContactPerson = contactPerson;
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.save(new Student(this.email, this.firstname, this.lastname, this.password, this.telephoneNumber, this.dateOfBirth, contactPerson));
    }

    public Student() {
        super();
    }

    public boolean getIsContactPerson() {
        return isContactPerson;
    }

    //get student with given room_id (db)
    public ArrayList<Student> getStudents(int room_id) {
        StudentDAO studentDAO = new StudentDAO();
        return studentDAO.getStudents(room_id);
    }

    public ArrayList<Student> getAllStudents (){
        ArrayList<Student> allStudents = new ArrayList<>();
        StudentDAO studentDAO = new StudentDAO();
        allStudents = studentDAO.getAllStudents();
        return allStudents;
    }

    public static String toRegister(String email, String firstname, String lastname, String password, String telephoneNumber, String dateOfBirth) {
        StudentDAO studentDAO = new StudentDAO();
        Student student1 = new Student(email, firstname, lastname, PasswordHashing.doHashing(password), telephoneNumber, dateOfBirth);
        studentDAO.save(student1);
        return "Your registration has been successful.";
    }

    public Object login(String email, String password) {
            StudentDAO studentDAO = new StudentDAO();
            ArrayList<Student> allStudents = studentDAO.getAllStudents();
            for (Student item : allStudents) {
                if (item.getEmail().equals(email)) {
                    if (item.password.equals(PasswordHashing.doHashing(password))) {
                        return item;
                    }
                }
            }
            String message = "Your e-mail or password was incorrect.";
            return message;
    }

    public boolean loginSucces(String email, String password) {
        StudentDAO studentDAO = new StudentDAO();
        ArrayList<Student> allStudents = studentDAO.getAllStudents();
        for (Student item : allStudents) {
            if (item.getEmail().equals(email)) {
                if (item.password.equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String test()
    {
        String message = "test";
        return message;
    }

    public String getReport() {
        String message = "";
        Room room;
        RoomDAO roomDAO = new RoomDAO();
        room = roomDAO.getRoom(this.email);
        ArrayList<Appliance> appliancesInTheRoom = new ArrayList<>();
        appliancesInTheRoom = room.getAppliancesInTheRoom();
        ArrayList<Action> actionsPerAppliance = new ArrayList<>();
        message += "The following is a report of all the appliances in the room where the student lives and all the energy conservation actions performed on each appliance: ";
        String message2 = "\nWe were not able to generate a relevant report since there were no appliances found in the room.";
        if (appliancesInTheRoom.isEmpty()) {
            message += message2;
        } else {
            for (Appliance item : appliancesInTheRoom) {
                message += "\n" + item.getName() + " of supplier " + item.getSupplierName();
                actionsPerAppliance = item.getActionsPerAppliance();
                if (actionsPerAppliance.isEmpty()) {
                    message += "\n" + "There were no energy conservation actions performed on this appliance.";
                } else {
                    for (Action item1 : actionsPerAppliance) {
                        message += "\n" + "The following energy conservation action were performed on the appliance:";
                        message += "\n" + item1.getName();
                    }
                }
            }
        }
        message += "\n" + "Monthly energy consumptions of the room: ";
        ArrayList<MonthlyEnergyConsumption> mecOfTheRoom = new ArrayList<>(); //mec = monthly energy consumption
        mecOfTheRoom = room.getMonthlyEnergyConsumptionPerRoom(room.roomID);
        if (mecOfTheRoom.isEmpty()) {
            message += "\n" + "There were no monthly energy consumptions found.";
        } else {
            for (MonthlyEnergyConsumption item : mecOfTheRoom) {
                message += "\n" + "\nMonth " + mecOfTheRoom.indexOf(item) + 1 + ":" + "\nElectricity: " + item.getElectricity() + " kWh" + "\nGas: " + item.getGas() + " kWh" + "\nWater: " + item.getWater() + " m^3" + "\n";
                if(!((mecOfTheRoom.indexOf(item)) == 0)){
                    if(((mecOfTheRoom.get(mecOfTheRoom.indexOf(item) - 1).getElectricity()) > item.getElectricity()) && ((mecOfTheRoom.get(mecOfTheRoom.indexOf(item) - 1).getGas()) < item.getGas())
                            && ((mecOfTheRoom.get(mecOfTheRoom.indexOf(item) - 1).getWater()) < item.getWater())){
                        message += "\nThe electricity, gas and water consumption are all decreased compared to last month!";
                    }
                }
            }
        }
        return message;
    }

    //makes a text file from the report, don't know if this works with Spring
    public void downloadStudentReport() {
        String message = this.getReport();
        try {
            PrintWriter output = new PrintWriter("Report.txt");
            output.println(message);
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
