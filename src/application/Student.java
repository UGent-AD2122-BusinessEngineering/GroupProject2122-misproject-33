package application;

import java.time.LocalDate;
import java.util.ArrayList;

public class Student extends Person {
    private Location location;
    //private Landlord landlord;
    //private ArrayList <String> performedEnergyConservationActions;

    public Student(String email, String firstname, String lastname, int idnumber, int telephonenumber, int age, LocalDate dateofbirth) {
        super(email, firstname, lastname, idnumber, telephonenumber, age, dateofbirth);
        //performedEnergyConservationActions = new ArrayList<>();
    }

    public void addEnergyConservationAction (EnergyConservationActions energyConservationAction){
        //communiceren met db (zie vb)
    }

    public void getEnergyConservationActions (){
        //communiceren met db (zie vb)
        //is geen void maar gwn om geen error te krijgen geeft arraylist terug
    }




    /*public ArrayList<String> getPerformedEnergyConservationActions() {
        return performedEnergyConservationActions;
    }*/
}
