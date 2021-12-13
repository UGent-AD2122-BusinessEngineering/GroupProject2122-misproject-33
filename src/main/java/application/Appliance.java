package application;

import db.ActionDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

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
    private boolean isEnergyConservationMode; // dit houdt bij of er één is
    private ArrayList <Action> actionsPerAppliance;

    public Appliance(String energyEfficiencyClass, String modelIdentifier, int annualEnergyConsumption,
                     String supplierName, String name, boolean isTempProportionate, boolean isTempDisproportionate, boolean isEnergyConservationMode) {
        this.energyEfficiencyClass = energyEfficiencyClass;
        this.modelIdentifier = modelIdentifier;
        this.annualEnergyConsumption = annualEnergyConsumption;
        this.supplierName = supplierName;
        this.name = name;
        this.isTempProportionate = isTempProportionate;
        this.isTempDisproportionate = isTempDisproportionate;
        this.isEnergyConservationMode = isEnergyConservationMode;
        this.actionsPerAppliance = new ArrayList<>(actionsPerAppliance);
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
        this.actionsPerAppliance = new ActionDAO().getActions(applianceID);
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

    //Ook een energyConservationModeOff nodig denk ik -Simon
    public String energyConservationModeOn (Appliance appliance, LocalDate date) {
        String message = "";
        if(!(appliance.isEnergyConservationMode())) {
            return message = "Is not possible for this appliance.";
        }
        else {
            Action actie = new Action(date, "energy conservation mode activated"); //dit is de naam die zou verschijnen wanneer een gebruiker de actie kan aanklikken? -Simon
            ActionDAO actionDAO = new ActionDAO();
            actionDAO.saveAction(actie, appliance.getApplianceID());
            message += "Thank you, we have registered the energy conservation method.";
        }
        return message;
    }

    public String energyConservationModeOff (Appliance appliance, LocalDate date) {
        String message = "";
        if(!(appliance.isEnergyConservationMode())) {
            return message += "Is not possible for this appliance.";
        }
        else {
            Action actie = new Action(date, "energy conservation mode activated"); //dit is de naam die zou verschijnen wanneer een gebruiker de actie kan aanklikken? -Simon
            ActionDAO actionDAO = new ActionDAO();
            actionDAO.deleteAction(actie.getId());
            message += "Thank you, we have registered the energy conservation method.";
        }
        return message;
    }


    public String customizedEnergyConservationAction(Appliance appliance, LocalDate date, String name){
        Action actie = new Action(date, name);
        ActionDAO actionDAO = new ActionDAO();
        actionDAO.saveAction(actie, appliance.getApplianceID());
        return "Thank you, we have registered your energy conservation method: " + name;
    }

    public String tipsAppliance(Appliance appliance){
        String message = "";
        if(appliance.isEnergyConservationMode()) {
            message += "You could put the energy conservation mode on.";
        }
        if(appliance.getIsTempProportionate()){
            message += "\nMaybe you could lower the temperature of the appliance.";
        }
        if(appliance.getIsTempDisproportionate()){
            message += "\nMaybe you could increase the temperature of the appliance.";
        }
        if(message.equals("")){
            message += "We don't have predefined tips for your appliance.";
        }
        return message;
    }

    public String decreaseDegree(Appliance appliance, LocalDate date) {
        String message = "";
        if (!(appliance.getIsTempProportionate() && appliance.getIsTempDisproportionate())){
            message += "Is not possible for this appliance.";
            return message;
        }
        if (appliance.getIsTempDisproportionate()){
            message += "this is not an energy-saving measure and therefore will not help you reduce your energy consumption.";
        }
        else{
            Action actie = new Action(date, "decrease a degree");
            ActionDAO actionDAO = new ActionDAO();
            actionDAO.saveAction(actie, appliance.getApplianceID());
            message += "Thank you, we have registered your energy conservation method.";
        }
        return message;
    }

    public String increaseDegree(Appliance appliance, LocalDate date) {
        String message = "";
        if (!(appliance.getIsTempProportionate() && appliance.getIsTempDisproportionate())) {
            message += "Is not possible for this appliance.";
            return message;
        }
        if (appliance.getIsTempProportionate()){
            message += "this is not an energy-saving measure and therefore will not help you reduce your energy consumption.";
        }
        else{
            Action actie = new Action(date, "increase a degree");
            ActionDAO actionDAO = new ActionDAO();
            actionDAO.saveAction(actie, appliance.getApplianceID());
            message += "Thank you, we have registered your energy conservation method.";
        }
        return message;
    }

    public String averageTemperature() {
        String message = "";
        double[] temp = new double[7];
        double total = 0;
        System.out.println("Please enter your temperatures from last week: ");
        for (int i = 0; i < temp.length; ) {
                total = total + temp[i];
                i++;
        }
        double average = total / temp.length;
        java.util.Arrays.sort(temp);
        message += "\n Your lowest temperature = " + temp[0];
        message += "\n Your highest temperature = " + temp[temp.length - 1];
        message += "\n The average temperature of this week was: " + average;
        return message;
    }

    public String getRandomTip() {
        ArrayList<String> tips = new ArrayList<String>();
        int randomNum = ThreadLocalRandom.current().nextInt(0, 23);
        tips.add("Utilize natural light.");
        tips.add("Turn off lights and electronics when you aren't using them.");
        tips.add("Replace traditional light bulbs with LEDs.");
        tips.add("Get a smart thermostat.");
        tips.add("Ensure your home is properly insulated.");
        tips.add("Put decorative lights on a timer.");
        tips.add("Reduce appliance use.");
        tips.add("Wash only full loads of dishes and clothes.");
        tips.add("Wash clothes with cold water.");
        tips.add("Keep your appliances clean.");
        tips.add("Keep the oven door closed while cooking.");
        tips.add("Don’t stand in front of an open refrigerator door.");
        tips.add("Air-dry clothes.");
        tips.add("Air-dry dishes instead of using your dishwasher’s drying cycle.");
        tips.add("Open curtains facing the sun.");
        tips.add("Switch out incandescent lights.");
        tips.add("Turn off electronics and appliances when they’re not in use.");
        tips.add("Unplug battery chargers when not in use.");
        tips.add("Adjust your thermostat according to the time of day.");
        tips.add("Set your computer to sleep or hibernate mode.");
        tips.add("Avoid using the rinse-and-hold setting on your dishwasher.");
        tips.add("Use the dishwasher instead of washing by hand.");
        tips.add("Plug home electronics into power strips.");
        tips.add("Install low-flow shower heads.");
        return tips.get(randomNum);
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
