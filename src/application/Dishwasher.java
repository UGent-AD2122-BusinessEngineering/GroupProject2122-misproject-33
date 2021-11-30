package application;
//https://energized.edison.com/stories/6-ways-to-make-your-dishwasher-energy-efficient#:~:text=Check%20the%20settings%20on%20your,energy)%20used%20by%20other%20cycles.&text=If%20possible%2C%20choose%20an%20air,operational%20cost%20with%20this%20feature.
// nummer 4 kunnen we bij de tips zetten om energie te besparen

public class Dishwasher extends Appliance {
    int heat;
    boolean energySavingMode;

    public Dishwasher(Location applianceOf, EnergyLabel energyLabel, int heat, boolean energySavingMode) {
        super(applianceOf, energyLabel);
        this.heat = heat;
        this.energySavingMode = energySavingMode;
    }
}
