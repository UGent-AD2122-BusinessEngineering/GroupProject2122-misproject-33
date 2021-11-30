package application;

public class MonthlyEnergyConsumption {
    private double electricity;
    private double gas;
    // alle twee in kWh
    private double water;
    // in m^3
    private double month;

    public MonthlyEnergyConsumption(double electricity, double gas, double water, double month) {
        this.electricity = electricity;
        this.gas = gas;
        this.water = water;
        this.month = month;
    }
}
