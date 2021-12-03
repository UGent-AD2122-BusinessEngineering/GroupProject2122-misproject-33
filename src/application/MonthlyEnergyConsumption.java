package application;

public class MonthlyEnergyConsumption {
    private Location location;
    private double electricity;
    private double gas;
    // alle twee in kWh
    private double water;
    // in m^3
    private double month;

    public MonthlyEnergyConsumption(Location location, double electricity, double gas, double water, double month) {
        this.location = location;
        this.electricity = electricity;
        this.gas = gas;
        this.water = water;
        this.month = month;
    }
}
