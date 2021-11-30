package application;

public class Lights extends Appliance {
    private int hoelangZijnLichtenAan;
    //pas gerust die naam aan als je iets beter weet
    public Lights(Location inWhichRoom, EnergyLabel energyLabel, int hoelangZijnLichtenAan) {
        super(inWhichRoom, energyLabel);
        this.hoelangZijnLichtenAan = hoelangZijnLichtenAan;
    }
}
