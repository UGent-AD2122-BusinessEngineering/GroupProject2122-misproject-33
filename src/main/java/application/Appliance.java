package application;

import java.util.ArrayList;
import java.util.Objects;

public class Appliance extends Action {

    public Appliance() {
        super();
    }

    //zie https://ec.europa.eu/info/energy-climate-change-environment/standards-tools-and-labels/products-labelling-rules-and-requirements/energy-label-and-ecodesign/about_en

    //instantievariabelen
    public enum energyEfficiencyClasses {A, B, C, D, E, F, G}
    private energyEfficiencyClasses energyEfficiencyClass; //energy label of the appliance
    private String modelIdentifier;
    private int annualEnergyConsumption;
    private String supplierName;
    private String name;
    private boolean energyConservationMode;
    private boolean tempProportionate;
    private boolean tempDisproportionate;
    private double temperature;

    public Appliance(energyEfficiencyClasses energyEfficiencyClass, String modelIdentifier, int annualEnergyConsumption, String supplierName, String name,
                     boolean energyConservationMode, boolean tempProportionate, boolean tempDisproportionate) {
        super();
        this.energyEfficiencyClass = energyEfficiencyClass;
        this.modelIdentifier = modelIdentifier;
        this.annualEnergyConsumption = annualEnergyConsumption;
        this.supplierName = supplierName;
        this.name = name;
        this.energyConservationMode = energyConservationMode;
        this.tempProportionate = tempProportionate;
        this.tempDisproportionate = tempDisproportionate;
    }

    public void setTemperature (Appliance appliance, double temperature){ //parameter wordt wrs nog name
        if (!(tempProportionate && tempDisproportionate))
            System.out.println("Is not necessary for this appliance");
        else
            this.temperature = temperature;
    }
    //kwn wat makkelijker is, met Scanner werken voor de temp of als parameter



    public energyEfficiencyClasses getEnergyEfficiencyClass() {
        return energyEfficiencyClass;
    }

    public void setEnergyEfficiencyClass(energyEfficiencyClasses energyEfficiencyClass) {
        this.energyEfficiencyClass = energyEfficiencyClass;
    }

    public String getModelIdentifier() {
        return modelIdentifier;
    }

    public void setModelIdentifier(String modelIdentifier) {
        this.modelIdentifier = modelIdentifier;
    }

    public int getAnnualEnergyConsumption() {
        return annualEnergyConsumption;
    }

    public void setAnnualEnergyConsumption(int annualEnergyConsumption) {
        this.annualEnergyConsumption = annualEnergyConsumption;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appliance appliance = (Appliance) o;
        return annualEnergyConsumption == appliance.annualEnergyConsumption && energyEfficiencyClass == appliance.energyEfficiencyClass && Objects.equals(modelIdentifier, appliance.modelIdentifier) && Objects.equals(supplierName, appliance.supplierName) && Objects.equals(name, appliance.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(energyEfficiencyClass, modelIdentifier, annualEnergyConsumption, supplierName, name);
    }

    /*public void setOperationDays ()
    {
        for (int i = 0; i < getMonthlyOperation().size(); i++) {
            boolean function = false;
                monthlyOperation.get(i);
            operationDays.add(function);
        }
    }*/
}
