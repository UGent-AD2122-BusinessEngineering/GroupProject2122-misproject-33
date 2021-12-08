package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Scanner;

/*
Mogelijke energy conservation acties die we kunnen implementeren
- Ledlampen gebruiken
- Temperatuur graad lager
- Toestellen met energiezuiniger label
- Zet beeldscherm volledig uit
*/

public class Action {
    private String description;
    private Appliance appliance;
    private LocalDateTime time;
    private double temperature;
    private String name;


    public Action() {
        this.time = time;
        this.appliance = appliance;
        this.description = description;
        this.temperature = temperature;
        this.name = name;
    }


    //method for high room temperatures (suppose room temperature = 20°C)
    public boolean aboveRoomTemperature(double temp) {
        if (this.temperature > 20) {
            temp = temperature;
            System.out.println("The room temperature is above 20°C, please lower to conserve energy.");
        }
        return temp > 20;
    }

    public boolean lowRoomTemperature(double temp) {
        if (this.temperature < 18) {
            temp = temperature;
            System.out.println("The room temperature is too low. Cold rooms require a lot of energy to heat up again.");
        }
        return temp < 18;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double[] temp = new double[6];
        double total = 0;

        for (int i = 0; i < temp.length; ) {
            System.out.printf("Please enter your temperatures from last week ", i);
            if (scan.hasNextFloat()) {
                temp[i] = scan.nextFloat();
                total = total + temp[i];
                i++;
            } else {
                scan.next();
            }
        }
        double average = total / temp.length;
        java.util.Arrays.sort(temp);
        System.out.println("Your lowest temperature = " + temp[0]);
        System.out.println("Your highest temperature = " + temp[temp.length - 1]);
        System.out.println("The average temperature of this week was " + average);
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


