package application;

import java.time.LocalDate;

public class Student extends Person {
    private Location location;
    //private Landlord landlord;
    //private ArrayList <String> performedEnergyConservationActions;

    public Student(String email, String firstname, String lastname, String telephone_number, String dateofbirth) {
        super(email, firstname, lastname, telephone_number, dateofbirth);
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
