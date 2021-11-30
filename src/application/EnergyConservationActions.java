package application;

public class EnergyConservationActions {
    private String name;
    private String applianceName;
    //kan hier evt ook nog owner toevoegen, hangt wat af van db denk ik

    public EnergyConservationActions(String applianceName, String name) {
        this.applianceName = applianceName;
        this.name = name;
    }
}
