package application;

import java.time.LocalDate;
import java.util.ArrayList;

public class Student extends Person {
    private Location location;
    //private Landlord landlord;
    private ArrayList<Action>energyConservationActions;

    public Student(String email, String firstname, String lastname, String password, String telephone_number, String dateofbirth) {
        super(email, firstname, lastname, password, telephone_number, dateofbirth);
        energyConservationActions = new ArrayList<>();
    }

    public void addEnergyConservationAction (Action energyConservationAction){
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
