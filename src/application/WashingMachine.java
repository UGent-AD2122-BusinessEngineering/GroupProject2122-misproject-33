package application;
//https://energysavingtrust.org.uk/how-save-energy-when-using-your-washing-machine/
//van nummer 2 kunnen we eventueel een tip maken

public class WashingMachine extends Appliance {
    private double averageTemp;
    private int hoeveelKeerperMaand;
    public WashingMachine(Location inWhichRoom, EnergyLabel energyLabel, double averageTemp, int hoeveelKeerperMaand) {
        super(inWhichRoom, energyLabel);
        this.averageTemp = averageTemp;
        this.hoeveelKeerperMaand = hoeveelKeerperMaand;
    }
}
