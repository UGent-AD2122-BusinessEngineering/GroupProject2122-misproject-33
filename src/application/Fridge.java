package application;

public class Fridge extends Appliance {
    private double temperatuur;
    private int hoeveelKeerFrigoOpen;
    //als je die laatste nie goed vindt doe hem gerust weg

    public Fridge(Location applianceOf, EnergyLabel energyLabel, double temperatuur, int hoeveelKeerFrigoOpen) {
        super(applianceOf, energyLabel);
        this.temperatuur = temperatuur;
        this.hoeveelKeerFrigoOpen = hoeveelKeerFrigoOpen;
    }



    }


