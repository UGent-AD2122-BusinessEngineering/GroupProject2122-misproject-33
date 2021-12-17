package application;

import java.util.Objects;

public class MonthlyEnergyConsumption {
    private double electricity;
    private double gas;
    private double water;
    private String month;
    private int monthlyEnergyConsumptionId;

    public MonthlyEnergyConsumption() {

    }

    public MonthlyEnergyConsumption(double electricity, double gas, double water, String month) {
        this.electricity = electricity;
        this.gas = gas;
        this.water = water;
        this.month = month;
    }

    public MonthlyEnergyConsumption(double electricity, double gas, double water, String month, int monthlyEnergyConsumptionId) {
        this.electricity = electricity;
        this.gas = gas;
        this.water = water;
        this.month = month;
        this.monthlyEnergyConsumptionId = monthlyEnergyConsumptionId;
    }

    public double getElectricity() {
        return electricity;
    }

    public double getGas() {
        return gas;
    }

    public double getWater() {
        return water;
    }

    public String getMonth() {
        return month;
    }

    public int getMonthlyEnergyConsumptionId() {
        return monthlyEnergyConsumptionId;
    }

    public void setMonthlyEnergyConsumptionId(int monthlyEnergyConsumptionId) {
        this.monthlyEnergyConsumptionId = monthlyEnergyConsumptionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthlyEnergyConsumption that = (MonthlyEnergyConsumption) o;
        return Double.compare(that.electricity, electricity) == 0 && Double.compare(that.gas, gas) == 0 && Double.compare(that.water, water) == 0 /*&& monthlyEnergyConsumptionId == that.monthlyEnergyConsumptionId */&& Objects.equals(month, that.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(electricity, gas, water, month/*, monthlyEnergyConsumptionId*/);
    }

}
