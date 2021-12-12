package application;

import db.MonthlyEnergyConsumptionDAO;

import java.time.LocalDate;
import java.util.Objects;

public class MonthlyEnergyConsumption {
    private double electricity;
    private double gas;
    private double water;
    private LocalDate month;
    private int monthlyEnergyConsumptionId;

    public MonthlyEnergyConsumption(double electricity, double gas, double water, LocalDate month) {
        this.electricity = electricity;
        this.gas = gas;
        this.water = water;
        this.month = month;
    }

    public MonthlyEnergyConsumption(double electricity, double gas, double water, LocalDate month, int monthlyEnergyConsumptionId) {
        this.electricity = electricity;
        this.gas = gas;
        this.water = water;
        this.month = month;
        this.monthlyEnergyConsumptionId = monthlyEnergyConsumptionId;
    }

    public double getElectricity() {
        return electricity;
    }

    public void setElectricity(double electricity) {
        this.electricity = electricity;
    }

    public double getGas() {
        return gas;
    }

    public void setGas(double gas) {
        this.gas = gas;
    }

    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public LocalDate getMonth() {
        return month;
    }

    public void setMonth(LocalDate month) {
        this.month = month;
    }

    public int getMonthlyEnergyConsumptionId() {
        return monthlyEnergyConsumptionId;
    }

    public void setMonthlyEnergyConsumptionId(int monthlyEnergyConsumptionId) {
        this.monthlyEnergyConsumptionId = monthlyEnergyConsumptionId;
    }

    public String addMonthlyEnergyConsumption (double electricity, double gas, double water, LocalDate month, Room room) {
        MonthlyEnergyConsumption monthlyEnergyConsumption = new MonthlyEnergyConsumption(electricity,gas,water,month);
        MonthlyEnergyConsumptionDAO monthlyEnergyConsumptionDAO = new MonthlyEnergyConsumptionDAO();
        monthlyEnergyConsumptionDAO.save(monthlyEnergyConsumption, room.roomID);
        return "Your monthly energy consumption of month " + month + " has been registered.";
    }

    public String deleteMonthlyEnergyConsumption (MonthlyEnergyConsumption monthlyEnergyConsumption){
        String message = " ";
        MonthlyEnergyConsumptionDAO monthlyEnergyConsumptionDAO = new MonthlyEnergyConsumptionDAO();
        monthlyEnergyConsumptionDAO.deleteMonthlyEnergyConsumption(monthlyEnergyConsumption.getMonthlyEnergyConsumptionId());
        return "MonthlyEnergyConsumption was succesfully deleted.";
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

    @Override
    public String toString() {
        return "MonthlyEnergyConsumption{" +
                "electricity=" + electricity +
                ", gas=" + gas +
                ", water=" + water +
                ", month=" + month /*+
                ", monthlyEnergyConsumptionId=" + monthlyEnergyConsumptionId */+
                '}';
    }
}
