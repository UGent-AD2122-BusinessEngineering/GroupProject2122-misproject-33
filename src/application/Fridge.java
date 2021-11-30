package application;

//zie commentaar dishwasher

public class Fridge extends Appliance {
    private double temperatuur;
    private int hoeveelKeerFrigoOpen;
    //als je die laatste nie goed vindt doe hem gerust weg
    private Student owner;

    public Fridge(Location applianceOf, EnergyLabel energyLabel, double temperatuur, int hoeveelKeerFrigoOpen, Student owner) {
        super(applianceOf, energyLabel);
        this.temperatuur = temperatuur;
        this.hoeveelKeerFrigoOpen = hoeveelKeerFrigoOpen;
    }

    public void frigoGraadHogerZetten() {
        temperatuur = temperatuur + 1;
        EnergyConservationActions eca = new EnergyConservationActions("fridge", "frigo graad hoger gezet");
        owner.getPerformedEnergyConservationActions().add("frigo graad hoger gezet");
    }
}


