package application;

import db.ActionDAO;
import db.ApplianceDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Appliance {

    //zie https://ec.europa.eu/info/energy-climate-change-environment/standards-tools-and-labels/products-labelling-rules-and-requirements/energy-label-and-ecodesign/about_en

    private String energyEfficiencyClass; //energy label of the appliance {A, B, C, D, E, F, G}
    private String modelIdentifier;
    private int annualEnergyConsumption;
    private String supplierName;
    private String name;
    private int applianceID;
    private boolean isTempProportionate;
    private boolean isTempDisproportionate;
    private double temperature; //deze moet er niet meer staan vermoed ik, temperature wordt niet opgeslaan in db -Simon
    private boolean isEnergyConservationMode; // dit houdt bij of er één is
    private ArrayList <Action> actionsPerAppliance;

    public Appliance(String energyEfficiencyClass, String modelIdentifier, int annualEnergyConsumption,
                     String supplierName, String name, boolean isTempProportionate, boolean isTempDisproportionate, boolean isEnergyConservationMode) {
        super();
        this.energyEfficiencyClass = energyEfficiencyClass;
        this.modelIdentifier = modelIdentifier;
        this.annualEnergyConsumption = annualEnergyConsumption;
        this.supplierName = supplierName;
        this.name = name;
        this.isTempProportionate = isTempProportionate;
        this.isTempDisproportionate = isTempDisproportionate;
        this.isEnergyConservationMode = isEnergyConservationMode;
    }

    public Appliance(String energyEfficiencyClass, String modelIdentifier, int annualEnergyConsumption, String supplierName, String name, boolean isTempProportionate, boolean isTempDisproportionate, boolean isEnergyConservationMode, int applianceID) {
        this.energyEfficiencyClass = energyEfficiencyClass;
        this.modelIdentifier = modelIdentifier;
        this.annualEnergyConsumption = annualEnergyConsumption;
        this.supplierName = supplierName;
        this.name = name;
        this.applianceID = applianceID;
        this.isTempProportionate = isTempProportionate;
        this.isTempDisproportionate = isTempDisproportionate;
        this.isEnergyConservationMode = isEnergyConservationMode;
    }

    public ArrayList<Action> getActionsPerAppliance(Appliance appliance) {
        ActionDAO actionDAO = new ActionDAO();
        actionDAO.getActions(appliance.getApplianceID());
        return actionsPerAppliance;
    }

    public boolean isEnergyConservationMode() {
        return isEnergyConservationMode;
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

    public int getApplianceID() {
        return applianceID;
    }

    public void setApplianceID(int applianceID) {
        this.applianceID = applianceID;
    }

    public boolean getIsTempProportionate() {
        return isTempProportionate;
    }

    public void setIsTempProportionate(boolean tempProportionate) {
        isTempProportionate = tempProportionate;
    }

    public boolean getIsTempDisproportionate() {
        return isTempDisproportionate;
    }

    public void setIsTempDisproportionate(boolean tempDisproportionate) {
        isTempDisproportionate = tempDisproportionate;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }


    public String addAppliance(String energyEfficiencyClass, String modelIdentifier, int annualEnergyConsumption, String supplierName, String name,
                               boolean isTempProportionate, boolean isTempDisproportionate, boolean isEnergyConservationMode, Room room){
        String message = " ";
        Appliance appliance = new Appliance(energyEfficiencyClass, modelIdentifier, annualEnergyConsumption, supplierName, name, isTempProportionate, isTempDisproportionate, isEnergyConservationMode);
        ApplianceDAO applianceDAO = new ApplianceDAO();
        appliance.setApplianceID(applianceDAO.save(appliance, room.roomID));
        return message = "The appliance has been succesfully added.";
    }

    public String deleteAppliance(Appliance appliance){
        String message = " ";
        ApplianceDAO applianceDAO = new ApplianceDAO();
        applianceDAO.deleteAppliance(appliance.getApplianceID());
        return message = "The appliance has been succesfully deleted.";
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
