package application;

public class Television extends Appliance {
    private int schermtijd;
    public Television(Location inWhichRoom, EnergyLabel energyLabel, int schermtijd) {
        super(inWhichRoom, energyLabel);
        this.schermtijd = schermtijd;
    }
}
