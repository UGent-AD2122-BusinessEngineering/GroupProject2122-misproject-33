package application;

public class Appliance {
    public enum EnergyLabel {A, B, C, D, E, F, G}
    public enum ApplianceType {Dishwasher, Fridge, Lights, WashingMachine, Television}
    //zie https://ec.europa.eu/info/energy-climate-change-environment/standards-tools-and-labels/products-labelling-rules-and-requirements/energy-label-and-ecodesign/about_en
    private Location inWhichRoom;
    //ik ben niet zeker of die echt nodig is...
    private EnergyLabel energyLabel;
    private ApplianceType applianceType;

    public Appliance(Location inWhichRoom, EnergyLabel energyLabel, ApplianceType applianceType) {
        this.inWhichRoom = inWhichRoom;
        this.energyLabel = energyLabel;
        this.applianceType = applianceType;
    }

    public Location getInWhichRoom() {
        return inWhichRoom;
    }

    public EnergyLabel getEnergyLabel() {
        return energyLabel;
    }

    public ApplianceType getApplianceType() {
        return applianceType;
    }
}
