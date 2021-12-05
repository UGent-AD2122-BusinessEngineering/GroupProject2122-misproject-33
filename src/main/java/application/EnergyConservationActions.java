package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class EnergyConservationActions {
    private String description;
    private Appliances appliance;
    private LocalDateTime time;
    private double temperature;

    public EnergyConservationActions(LocalDateTime time, Appliances appliance, String description, double temperature) {
        this.time = time;
        this.appliance = appliance;
        this.description = description;
        this.temperature = temperature;
    }

    //method for high room temperatures (suppose room temperature = 20°C)
    public boolean aboveRoomTemperature(double temp) {
        if (this.temperature > 20) {
            temp = temperature;
            System.out.println("The room temperature is above 20°C, please lower to conserve energy.");
        }
        return true;
    }

    public boolean lowRoomTemperature(double temp) {
        if(this.temperature < 18){
            temp = temperature;
            System.out.println("The room temperature is too low. Temperatures that are too low require a lot of energy to heat up the room again.");
        }
        return true;
    }

    //method for certain temperatures between certain hours
    public EnergyConservationActions() {
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

}
