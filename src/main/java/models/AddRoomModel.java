package models;

public class AddRoomModel {
    private int locationID;
    private int roomnumber;

    private String landlordEmail;

    private int roomid;

    private String energyEfficiencyClass; //energy label of the appliance {A, B, C, D, E, F, G}
    private String modelIdentifier;
    private int annualEnergyConsumption;
    private String supplierName;
    private String name;


    private boolean isTempProportionate;
    private boolean isTempDisproportionate;
    private boolean isEnergyConservationMode;

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public int getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(int roomnumber) {
        this.roomnumber = roomnumber;
    }

    public String getLandlordEmail() {
        return landlordEmail;
    }

    public void setLandlordEmail(String landlordEmail) {
        this.landlordEmail = landlordEmail;
    }

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

    public void setTempProportionate(boolean isTempProportionate) {
        this.isTempProportionate = isTempProportionate;
    }



    public boolean getIsTempDisproportionate() {
        return isTempDisproportionate;
    }

    public void setTempDisproportionate(boolean isTempDisproportionate) {
        this.isTempDisproportionate = isTempDisproportionate;
    }



    public boolean getIsEnergyConservationMode() {
        return isEnergyConservationMode;
    }

    public void setEnergyConservationMode(boolean isEnergyConservationMode) {
        this.isEnergyConservationMode = isEnergyConservationMode;
    }


}
