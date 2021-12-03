package application;

import java.time.LocalDate;

public class MonthlyEnergyConsumption {
    private Location location;
    private double electricity;
    private double gas;
    // alle twee in kWh
    private double water;
    // in m^3
    private LocalDate date;

    public MonthlyEnergyConsumption(Location location, double electricity, double gas, double water, LocalDate date) {
        this.location = location;
        this.electricity = electricity;
        this.gas = gas;
        this.water = water;
        this.date = date;
    }


}
