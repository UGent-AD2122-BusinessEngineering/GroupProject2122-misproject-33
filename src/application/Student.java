package application;

import java.time.LocalDate;
import java.util.ArrayList;

public class Student extends Person {
    private Location location;
    //private Landlord landlord;
    private ArrayList <String> performedEnergyConservationActions;

    public Student(String email, String firstname, String lastname, int idnumber, int telephonenumber, int age, LocalDate dateofbirth) {
        super(email, firstname, lastname, idnumber, telephonenumber, age, dateofbirth);
        performedEnergyConservationActions = new ArrayList<>();
    }



    public ArrayList<String> getPerformedEnergyConservationActions() {
        return performedEnergyConservationActions;
    }
}
