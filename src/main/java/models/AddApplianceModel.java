package models;

public class AddApplianceModel {

    private int roomid;

    private String energyEfficiencyClass;
    private String modelIdentifier;
    private int annualEnergyConsumption;
    private String supplierName;
    private String name;

    private boolean isTempProportionate;
    private boolean isTempDisproportionate;
    private boolean isEnergyConservationMode;
    private int applianceID;
    private int applianceid;
    private int locationID;



    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
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



    public boolean getIsTempProportionate() {
        return isTempProportionate;
    }

    public void setIsTempProportionate(boolean isTempProportionate) {
        this.isTempProportionate = isTempProportionate;
    }



    public boolean getIsTempDisproportionate() {
        return isTempDisproportionate;
    }

    public void setIsTempDisproportionate(boolean isTempDisproportionate) {
        this.isTempDisproportionate = isTempDisproportionate;
    }



    public boolean getIsEnergyConservationMode() {
        return isEnergyConservationMode;
    }

    public void setIsEnergyConservationMode(boolean isEnergyConservationMode) {
        this.isEnergyConservationMode = isEnergyConservationMode;
    }

    public int getApplianceid() {
        return applianceid;
    }

    public void setApplianceid(int applianceid) {
        this.applianceid = applianceid;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public int getApplianceID() {
        return applianceID;
    }

    public void setApplianceID(int applianceID) {
        this.applianceID = applianceID;
    }
}
