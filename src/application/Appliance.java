package application;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Appliance {

    //instantievariabelen
    public GregorianCalendar date;
    public enum EnergyLabel {A, B, C, D, E, F, G}
    public enum ApplianceType {Dishwasher, Fridge, Lights, WashingMachine, Television}
    //zie https://ec.europa.eu/info/energy-climate-change-environment/standards-tools-and-labels/products-labelling-rules-and-requirements/energy-label-and-ecodesign/about_en

    private EnergyLabel energyLabel; //energy label of the appliance
    private ApplianceType applianceType; //appliance name
    private Location applianceOf; //location that the appliance is installed at
    private int power; //power used by the appliance
    private ArrayList<ArrayList<Integer>> monthlyConsumption = new ArrayList<ArrayList<Integer>>(); //final monthly consumption of the appliance
    ArrayList<Boolean> operationDays = new ArrayList<Boolean>();
    private ArrayList<ArrayList<Boolean>> monthlyOperation = new ArrayList<ArrayList<Boolean>>(); //final monthly operation of the appliance
    private int time; //the amount of times the appliance may work in a month

    //getters
    public int getPower () {return power;} //returns power variable of the appliance
    public Location getApplianceOf () {return applianceOf;} //the location where the appliance is installed
    public EnergyLabel getEnergyLabel() {return energyLabel;}
    public ApplianceType getApplianceType() {return applianceType;}
    public ArrayList<ArrayList<Integer>> getMonthlyConsumption() {return monthlyConsumption;}
    public ArrayList<ArrayList<Boolean>> getMonthlyOperation() {return monthlyOperation;}
    public int getTime() {return time;}

    //setters
    public void setApplianceOf (Location room) {applianceOf = room;} //sets the household in which the appliance is installed in

    public void setOperationDays ()
    {
        for (int i = 0; i < getMonthlyOperation().size(); i++) {
            boolean function = false;
                monthlyOperation.get(i);
            operationDays.add(function);
        }
    }

    //first day of month (klopt wss niet)
    LocalDate getFirstDayOfMonth(GregorianCalendar c) {
        LocalDate date = c.toZonedDateTime().toLocalDate();
        return date.withDayOfMonth(1);
    }

    GregorianCalendar calendar = GregorianCalendar.from(ZonedDateTime.now());
    LocalDate first = getFirstDayOfMonth(calendar);



}
