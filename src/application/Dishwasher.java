package application;
//https://energized.edison.com/stories/6-ways-to-make-your-dishwasher-energy-efficient#:~:text=Check%20the%20settings%20on%20your,energy)%20used%20by%20other%20cycles.&text=If%20possible%2C%20choose%20an%20air,operational%20cost%20with%20this%20feature.
// nummer 4 kunnen we bij de tips zetten om energie te besparen

public class Dishwasher extends Appliance {
    private double heat;
    private boolean energysavingmode;
    private Student owner;

    public Dishwasher(Location applianceOf, EnergyLabel energyLabel, double heat, boolean energysavingmode, Student owner) {
        super(applianceOf, energyLabel);
        this.heat = heat;
        this.energysavingmode = energysavingmode;
        this.owner = owner;
    }

    public boolean lowerTheHeatTo12O(){
        if (this.heat <= 120)
            return false;
        else{
            this.heat = 120;
            EnergyConservationActions eca = new EnergyConservationActions("dishwasher","lower the heat to 120 degrees");
            owner.getPerformedEnergyConservationActions().add("lowered the heat of the dishwasher to 120 degrees");
            return true;
        }
    }
    //studenten die hun vaatwas al op 120° hadden staan of energysavingmode al aanstond worden dan wel niet opgemerkt
    //moeten we mss nog iets op vinden

    public boolean energysavingmodeAanzetten (){
        if (energysavingmode == true)
            return false;
        else {
            this.energysavingmode = true;
            EnergyConservationActions eca = new EnergyConservationActions("dishwasher","energysavingsmode aanzetten");
            owner.getPerformedEnergyConservationActions().add("energysavingmode on");
            return true;
    }
    }

    //studenten die hun vaatwas al op 120° hadden staan of energysavingmode al aanstond worden dan wel niet opgemerkt
    //nog iets op vinden

}
