package application;

public class Appliances {
    //zie https://ec.europa.eu/info/energy-climate-change-environment/standards-tools-and-labels/products-labelling-rules-and-requirements/energy-label-and-ecodesign/about_en

    //instantievariabelen
    public enum energyEfficiencyClasses {A, B, C, D, E, F, G}
    private energyEfficiencyClasses energyEfficiencyClass; //energy label of the appliance
    private Location applianceOf; //location that the appliance is installed at
    private String applianceId;
    private String modelIdentifier;
    private int monthlyConsumption;
    private int eei; // energy efficiencyindex
    private String nameSupplier;
    private boolean temperatuursgebonden;


    //constructor
    public Appliances(String modelIdentifier, int eei, String nameSupplier, boolean temperatuursgebonden) {
        this.modelIdentifier = modelIdentifier;
        this.eei = eei;
        this.nameSupplier = nameSupplier;
        this.temperatuursgebonden = temperatuursgebonden;
    }

    //getters
    public Location getApplianceOf () {return applianceOf;} //the location where the appliance is installed
    public energyEfficiencyClasses getEnergyEfficiencyClass() {return energyEfficiencyClass;}
    public String getApplianceId() {return applianceId;}
    public String getModelIdentifier() {return modelIdentifier;}
    public int getMonthlyConsumption() {return monthlyConsumption;}
    public int getEei() {return eei;}
    public String getNameSupplier() {return nameSupplier;}

    //setters
    public void setApplianceOf (Location room) {applianceOf = room;} //sets the household in which the appliance is installed in




    /*public void setOperationDays ()
    {
        for (int i = 0; i < getMonthlyOperation().size(); i++) {
            boolean function = false;
                monthlyOperation.get(i);
            operationDays.add(function);
        }
    }*/
}
