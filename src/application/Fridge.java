package application;

public class Fridge extends Appliance {
    private int temperatuur;
    private int hoeveelKeerFrigoOpen;
    //als je die laatste nie goed vindt doe hem gerust weg

    public Fridge(Location applianceOf, EnergyLabel energyLabel, int temperatuur, int hoeveelKeerFrigoOpen) {
        super(applianceOf, energyLabel);
        this.temperatuur = temperatuur;
        this.hoeveelKeerFrigoOpen = hoeveelKeerFrigoOpen;
    }

    //public Fridge(Appliance.EnergyLabel energyLabel, Appliance.ApplianceType applianceType) {
    // }
    // dit was de originele maar ik denk niet dat die klopte

    }


