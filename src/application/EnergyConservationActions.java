package application;

import java.time.LocalDate;

public class EnergyConservationActions {
    private String beschrijving;
    private Appliances appliance;
    private LocalDate when;

    public EnergyConservationActions(LocalDate when, Appliances appliance, String beschrijving) {
        this.when = when;
        this.appliance = appliance;
        this.beschrijving = beschrijving;
    }
}
