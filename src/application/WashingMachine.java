package application;
//https://energysavingtrust.org.uk/how-save-energy-when-using-your-washing-machine/
//van nummer 2 kunnen we eventueel een tip maken

public class WashingMachine extends Appliance {
    private int averageTemp;
    public WashingMachine(Location inWhichRoom, EnergyLabel energyLabel, int averageTemp) {
        super(inWhichRoom, energyLabel);
        this.averageTemp = averageTemp;
    }
}
