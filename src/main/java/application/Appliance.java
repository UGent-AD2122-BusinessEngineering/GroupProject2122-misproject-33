package application;

import db.ActionDAO;
import java.util.ArrayList;
import java.util.Objects;
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

    public Appliance() {

    }

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
        this.actionsPerAppliance = new ArrayList<>();
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
        this.actionsPerAppliance = getActionsPerApplianceId(applianceID);
    }



    public ArrayList<Action> getActionsPerAppliance() {
        ActionDAO actionDAO = new ActionDAO();
        actionsPerAppliance = actionDAO.getActions(this.applianceID);
        return actionsPerAppliance;
    }

    public ArrayList<Action> getActionsPerApplianceId(int applianceID){
        ActionDAO actionDAO = new ActionDAO();
        ArrayList<Action> actionsPerAppliance  = new ArrayList<Action>();
        actionsPerAppliance  = actionDAO.getActions(applianceID);
        return actionsPerAppliance;
     }

    public boolean getIsEnergyConservationMode() {
        return isEnergyConservationMode;
    }

    public String getEnergyEfficiencyClass() {
        return energyEfficiencyClass;
    }

    public String getModelIdentifier() {
        return modelIdentifier;
    }

    public int getAnnualEnergyConsumption() {
        return annualEnergyConsumption;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getName() {
        return name;
    }

    public int getApplianceID() {
        return applianceID;
    }

    public boolean getIsTempProportionate() {
        return isTempProportionate;
    }

    public boolean getIsTempDisproportionate() {
        return isTempDisproportionate;
    }

    public void setApplianceID(int applianceID) {
        this.applianceID = applianceID;
    }

    public String energyConservationModeOn (String date) {

        String message = "";
        if(!(this.getIsEnergyConservationMode())) {
            return message += "Is not possible for this appliance.";
        }
        else {
            Action actie = new Action(date, "energy conservation mode activated");
            ActionDAO actionDAO = new ActionDAO();
            actionDAO.saveAction(actie, this.getApplianceID());
            message += "Thank you, we have registered the energy conservation method.";
        }
        return message;
    }


    public String customizedEnergyConservationAction(String date, String name){
        Action actie = new Action(date, name);
        ActionDAO actionDAO = new ActionDAO();
        actionDAO.saveAction(actie, this.getApplianceID());
        return "Thank you, we have registered your energy conservation method";
    }

    public String tipsAppliance(){
        String message = "";
        if(this.getIsEnergyConservationMode()) {
            message += "You could put the energy conservation mode on.";
        }
        if(this.getIsTempProportionate()){
            message += "\r\n Maybe you could lower the temperature of the appliance.";
        }
        if(this.getIsTempDisproportionate()){
            message += "\r\n Maybe you could increase the temperature of the appliance.";
        }
        if(message.equals("")){
            message += "We don't have predefined tips for your appliance.";
        }
        return message;
    }

    public String decreaseDegree(String date) {

        String message = "";

        if (this.getIsTempDisproportionate()){
            message += "this is not an energy-saving measure and therefore will not help you reduce your energy consumption.";
        }
        else{
            Action actie = new Action(date, "decrease a degree");
            ActionDAO actionDAO = new ActionDAO();
            actionDAO.saveAction(actie, this.getApplianceID());
            message += "Thank you, we have registered your energy conservation method.";
        }
        return message;
    }

    public String increaseDegree(String date) {

        String message = "";
        if (this.getIsTempProportionate()){
            message += "this is not an energy-saving measure and therefore will not help you reduce your energy consumption.";
        }
        else{
            Action actie = new Action(date, "increase a degree");
            ActionDAO actionDAO = new ActionDAO();
            actionDAO.saveAction(actie, this.getApplianceID());
            message += "Thank you, we have registered your energy conservation method.";
        }
        return message;
    }

    public static String getRandomTip() {
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

}
