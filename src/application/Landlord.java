package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Landlord extends Person {
    private Location dormRoom; // moet ook arraylist zijn denk ik maar ik weet niet hoe je het dan doet met die methodes
    private ArrayList<Student> contacts; //for adding contacts (in this case: the students) to the rooms)
    private static int dagVdMaand = LocalDate.now().getDayOfMonth();
    private static int maandVhJaar = LocalDate.now().getMonthValue();

    public Landlord(String email, String firstname, String lastname, int idnumber, int telephonenumber, int age, LocalDate dateofbirth) {
        super(email, firstname, lastname, idnumber, telephonenumber, age, dateofbirth);
        contacts = new ArrayList<Student>();

    }


    //add a contact person for the room
    public void addContact(Student contact) {
        contacts.add(contact);
    }

    //assign student to a new dorm room
    public void setRoom(Location room, Gender gender){

        if (dormRoom != null) {
            dormRoom.removeStudent(this);
        }
        dormRoom = room;
        dormRoom.addStudent(this);

        if (room == null) {
            switch (gender){
                case MALE:
                    System.out.println("Student " + lastname + " " + firstname + " with ID-number " + idnumber + " leaves his room.");
                    break;
                case FEMALE:
                    System.out.println("Student " + lastname + " " + firstname + " with ID-number " + idnumber + " leaves her room.");
                    break;
            }
        }
    }

    //1. ik doe hier nog geen test voor eerste dag vd maand omdat me het beter lijkt dat te doen waar je de methode oproept
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
    }

    //test
    public void test(){
        dormRoom = new Location("Belgium","Gent",9000,250,"Krijgslaan",526,10);
    }
}
