package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Appliance extends Action {

    public Appliance() {
        super();
    }

    //zie https://ec.europa.eu/info/energy-climate-change-environment/standards-tools-and-labels/products-labelling-rules-and-requirements/energy-label-and-ecodesign/about_en

    //instantievariabelen
    private String energyEfficiencyClass; //energy label of the appliance {A, B, C, D, E, F, G}
    private String modelIdentifier;
    private int annualEnergyConsumption;
    private String supplierName;
    private String name;
    private String applianceID;
    private boolean isTempProportionate;
    private boolean isTempDisproportionate;
    private double temperature;
    private boolean isEnergyConservationMode; // dit houdt bij of er één is
    private boolean energyConservationMode; // dit houdt bij of hij aanstaa, als er géén is blijft dit op false staan

    public Appliance(String energyEfficiencyClass, String modelIdentifier, int annualEnergyConsumption,
                     String supplierName, String name, boolean isTempProportionate, boolean isTempDisproportionate, boolean isEnergyConservationMode, boolean energyConservationMode) {
        super();
        this.energyEfficiencyClass = energyEfficiencyClass;
        this.modelIdentifier = modelIdentifier;
        this.annualEnergyConsumption = annualEnergyConsumption;
        this.supplierName = supplierName;
        this.name = name;
        this.isTempProportionate = isTempProportionate;
        this.isTempDisproportionate = isTempDisproportionate;
        this.isEnergyConservationMode = isEnergyConservationMode;
        this.energyConservationMode = energyConservationMode;
    }

    public String getEnergyEfficiencyClass() {
        return energyEfficiencyClass;
    }

    public void setEnergyEfficiencyClass(String energyEfficiencyClass) {
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

    public String getApplianceID() {
        return applianceID;
    }

    public void setApplianceID(String applianceID) {
        this.applianceID = applianceID;
    }

    public boolean isTempProportionate() {
        return isTempProportionate;
    }

    public void setTempProportionate(boolean tempProportionate) {
        isTempProportionate = tempProportionate;
    }

    public boolean isTempDisproportionate() {
        return isTempDisproportionate;
    }

    public void setTempDisproportionate(boolean tempDisproportionate) {
        isTempDisproportionate = tempDisproportionate;
    }

    public boolean isEnergyConservationMode() {
        return energyConservationMode;
    }

    public void setEnergyConservationMode(boolean energyConservationMode) {
        this.energyConservationMode = energyConservationMode;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
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

    public String decreaseDegree(Appliance appliance) {
        String message = "";
        if (!(appliance.isTempProportionate() && appliance.isTempDisproportionate())){
            message = "Is not possible for this appliance.";
            return message;
        }
        if (appliance.isTempDisproportionate()){
            message = "this is not an energy-saving measure and therefore will not help you reduce your energy consumption.";
            appliance.setTemperature(appliance.getTemperature() - 1);
        }
        else{
            appliance.setTemperature(appliance.getTemperature()-1);
            Action actie = new Action(appliance, LocalDate.now(), "decrease a degree");
            message = "Thank you, we have registered your energy conservation method.";
        }
        return message;
    }

    public String increaseDegree(Appliance appliance) {
        String message = "";
        if (!(appliance.isTempProportionate() && appliance.isTempDisproportionate())) {
            message = "Is not possible for this appliance.";
            return message;
        }
        if (appliance.isTempProportionate()){
            message = "this is not an energy-saving measure and therefore will not help you reduce your energy consumption.";
            appliance.setTemperature(appliance.getTemperature() + 1);
        }
        else{
            appliance.setTemperature(appliance.getTemperature() + 1);
            Action actie = new Action(appliance, LocalDateTime.now(), "increase a degree");
            message = "Thank you, we have registered your energy conservation method.";
        }
        return message;
    }

    public String energyConservationModeOn (Appliance appliance) {
        String message = "";
        if(!(appliance.isEnergyConservationMode)) {
            return message = "Is not possible for this appliance.";
        }
        if(energyConservationMode)
            message = "The energy conservation mode of this appliance is already activated.";
        else {
            energyConservationMode = true;
            message = "Thank you, we have registered your energy conservation method.";
            Action actie = new Action(appliance, LocalDate.now(), "energy conservation mode activated");
        }
        return message;
    }

    public String customizedEnergyConservationAction(Appliance appliance, LocalDate date,String name){
        String message = "";
        Action actie = new Action(appliance, date, name);
        return message = "Thank you, we have registered your energy conservation method: " + name;
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
