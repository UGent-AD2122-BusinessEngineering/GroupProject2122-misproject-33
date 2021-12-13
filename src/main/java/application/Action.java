package application;


import db.LocationDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/*
Mogelijke energy conservation acties die we kunnen implementeren
- Ledlampen gebruiken /niet haalbaar (of ik weet op zijn minst toch niet hoe, kunnen we wel gebruiken voor tips mss)
- Temperatuur graad lager /Done
- Toestellen met energiezuiniger label /Done
- Zet beeldscherm volledig uit /niet haalbaar (of ik weet op zijn minst toch niet hoe, kunnen we wel gebruiken voor tips mss)
*/

public class Action {
    private LocalDate date;
    private String name;
    private int Id;

    public Action() {
    }

    public Action(LocalDate date, String name) {
        this.date = date;
        this.name = name;
    }

    public Action(LocalDate date, String name, int id) {
        this.date = date;
        this.name = name;
        this.Id = id;
    }


    public int getId() {
        return Id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }



/*
    //detail
    public double energyLabelScore(int value){
        char A = 'A';
        char B = 'B';
        char C = 'C';
        char D = 'D';
        char E = 'E';
        char F = 'F';
        char G = 'G';

        int intA = (int) A - 40;
        int intB = (int) B - 40;
        int intC = (int) C - 40;
        int intD = (int) D - 40;
        int intE = (int) E - 40;
        int intF = (int) F - 40;
        int intG = (int) G - 40;

        return 0;
    } */

    public static void main(String[] args) {
        Location l = new Location("Belgie", "gent", "9000", "volmolen", "20", 200, false, "wide", 10);
        Room r = new Room(1, l, 8);
        Landlord la = new Landlord("y.z@gmail.com", "mathias", "brabants", "ikbenmathias123", "0479052433", "02.11.2001");
        Student st = new Student("r.b@gmail.com", "r", "b", "rb", "041", "02", true);
        st.toRegister(true, "r.b@gmail.com", "r", "b", "rb", "041", "02" );
        Appliance a = new Appliance("A", "001", 20, "simon", "thalongi", false, true, true, 16 );
        //Action ac = new Action(LocalDate.parse("2001-11-02"), "power");
        LocationDAO ldao = new LocationDAO();
        ldao.saveLocation(l);
        la.toRegister("y.z@gmail.com", "mathias", "brabants", "ikbenmathias123", "0479052433", "02.11.2001");
        System.out.println(la.addRoom(la, l, 1));
        r.addAppliance("A", "001", 20, "simon", "thalongi", false, true, true, r );
        //System.out.println(a.energyConservationModeOn(a, LocalDate.parse("2001-11-02")));
        System.out.println(la.getRoomsLandLord(la));
    }
}
   /*
    //method for certain temperatures between certain hours
    public Action() {
        try {
            String string1 = "08:00:00";
            Date time1 = new SimpleDateFormat("HH:mm:ss").parse(string1);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(time1);
            calendar1.add(Calendar.DATE, 1);

            String string2 = "00:00:00";
            Date time2 = new SimpleDateFormat("HH:mm:ss").parse(string2);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(time2);
            calendar2.add(Calendar.DATE, 1);

            String someRandomTime = String.valueOf(LocalDateTime.now());
            Date d = new SimpleDateFormat("HH:mm:ss").parse(someRandomTime);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(d);
            calendar3.add(Calendar.DATE, 1);

            Date now = calendar3.getTime();

            if (now.after(calendar1.getTime()) && now.before(calendar2.getTime())) {
                //checks whether the current time is between 08:00:00 and 00:00:00.
                if (temperature == 20) {
                    this.temperature = temperature;
                }
                else System.out.println("Adjust the temperature to 20°C");
            }

            if (now.after(calendar2.getTime()) && now.before(calendar1.getTime())) {
                //checks whether the current time is between 00:00:00 and 08:00:00.
                if (temperature > 20) {
                    System.out.println("The temperature is too high. Lower the temperature.");
                }
                else if (temperature < 18) {
                    System.out.println("The temperature is too low. Higher the temperature.");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    */


