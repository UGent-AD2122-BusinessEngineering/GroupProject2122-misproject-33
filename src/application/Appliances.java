package application;

public class Appliances {
    private enum EnergyLabel {A, B, C, D, E, F, G};
    private enum ApplianceType {fridges_and_freezers, dishwashers, washing_machines, televisions, lamps};
    //zie https://ec.europa.eu/info/energy-climate-change-environment/standards-tools-and-labels/products-labelling-rules-and-requirements/energy-label-and-ecodesign/about_en
    private Room inWhichRoom;
    //ik ben niet zeker of die echt nodig is...
    private EnergyLabel energyLabel;
    private ApplianceType applianceType;

    public Appliances(Room inWhichRoom, EnergyLabel energyLabel, ApplianceType applianceType) {
        this.inWhichRoom = inWhichRoom;
        this.energyLabel = energyLabel;
        this.applianceType = applianceType;
    }

    public Room getInWhichRoom() {
        return inWhichRoom;
    }

    public EnergyLabel getEnergyLabel() {
        return energyLabel;
    }

    public ApplianceType getApplianceType() {
        return applianceType;
    }
}
