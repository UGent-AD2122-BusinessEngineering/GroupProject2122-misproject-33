package application;

import db.ActionDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.time.LocalDate;
import java.util.Scanner;

/*
Mogelijke energy conservation acties die we kunnen implementeren
- Ledlampen gebruiken /niet haalbaar (of ik weet op zijn minst toch niet hoe, kunnen we wel gebruiken voor tips mss)
- Temperatuur graad lager /Done
- Toestellen met energiezuiniger label /Done
- Zet beeldscherm volledig uit /niet haalbaar (of ik weet op zijn minst toch niet hoe, kunnen we wel gebruiken voor tips mss)
*/
//kheb de methodes bij appliance gezet omdat ik het wat raar vond om een action object hierin in te maken, (komt op hetzelfde neer uiteindelijk)

public class Action {
    private LocalDate date;
    private String name;
    private int Id;


    public Action(LocalDate date, String name) {
        this.date = date;
        this.name = name;
    }

    public int getId() {
        return getId();
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String decreaseDegree(Appliance appliance, LocalDate date) {
        String message = "";
        if (!(appliance.getIsTempProportionate() && appliance.getIsTempDisproportionate())){
            message = "Is not possible for this appliance.";
            return message;
        }
        if (appliance.getIsTempDisproportionate()){
            message = "this is not an energy-saving measure and therefore will not help you reduce your energy consumption.";
            appliance.setTemperature(appliance.getTemperature() - 1);
        }
        else{
            appliance.setTemperature(appliance.getTemperature()-1);
            Action actie = new Action(date, "decrease a degree");
            ActionDAO actionDAO = new ActionDAO();
            actionDAO.saveAction(actie, appliance.getApplianceID());
            message = "Thank you, we have registered your energy conservation method.";
        }
        return message;
    }

    public String increaseDegree(Appliance appliance, LocalDate date) {
        String message = "";
        if (!(appliance.getIsTempProportionate() && appliance.getIsTempDisproportionate())) {
            message = "Is not possible for this appliance.";
            return message;
        }
        if (appliance.getIsTempProportionate()){
            message = "this is not an energy-saving measure and therefore will not help you reduce your energy consumption.";
            appliance.setTemperature(appliance.getTemperature() + 1);
        }
        else{
            appliance.setTemperature(appliance.getTemperature() + 1);
            Action actie = new Action(date, "increase a degree");
            ActionDAO actionDAO = new ActionDAO();
            actionDAO.saveAction(actie, appliance.getApplianceID());
            message = "Thank you, we have registered your energy conservation method.";
        }
        return message;
    }

    public String energyConservationModeOn (Appliance appliance, LocalDate date) {
        String message = "";
        if(!(appliance.isEnergyConservationMode())) {
            return message = "Is not possible for this appliance.";
        }
        else {
            Action actie = new Action(date, "energy conservation mode activated");
            ActionDAO actionDAO = new ActionDAO();
            actionDAO.saveAction(actie, appliance.getApplianceID());
            message = "Thank you, we have registered the energy conservation method.";
        }
        return message;
    }

    public String customizedEnergyConservationAction(Appliance appliance, LocalDate date,String name){
        String message = "";
        Action actie = new Action(date, name);
        ActionDAO actionDAO = new ActionDAO();
        actionDAO.saveAction(actie, appliance.getApplianceID());
        return message = "Thank you, we have registered your energy conservation method: " + name;
    }


    public String averageTemperature(double temperature) {
        String message = "";
        double[] temp = new double[6];
        double total = 0;
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < temp.length; ) {
            System.out.println("Please enter your temperatures from last week ");
            if (scan.hasNextDouble()) {
                temp[i] = scan.nextDouble();
                total = total + temp[i];
                i++;
            } else {
                scan.next();
            }
        }
        double average = total / temp.length;
        java.util.Arrays.sort(temp);
        message += "Your lowest temperature = " + temp[0];
        message += "Your highest temperature = " + temp[temp.length - 1];
        message += "The average temperature of this week was: " + average;
        return message;
    }


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


