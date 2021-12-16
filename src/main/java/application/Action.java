package application;

import java.time.LocalDate;

public class Action {
    private LocalDate date;
    private String name;
    private int Id;

    public Action() {
    }

    public Action(LocalDate date, String name) {
        this.date = date;
        this.name = name;
    }

    public Action(LocalDate date, String name, int id) {
        this.date = date;
        this.name = name;
        this.Id = id;
    }

    public int getMonth(){
        return date.getMonthValue();
    }


    public int getId() {
        return Id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }
}



/*
    //detail
    public double energyLabelScore(int value){
        char A = 'A';
        char B = 'B';
        char C = 'C';
        char D = 'D';
        char E = 'E';
        char F = 'F';
        char G = 'G';

        int intA = (int) A - 40;
        int intB = (int) B - 40;
        int intC = (int) C - 40;
        int intD = (int) D - 40;
        int intE = (int) E - 40;
        int intF = (int) F - 40;
        int intG = (int) G - 40;

        return 0;
    }

    public static void main(String[] args) {
        //Location l = new Location("België", "gent", "9000", "volmolen", "20", 200, false, "wide", 10);
        //Room r = new Room(1, l, 8);
        //Landlord la = new Landlord("y.z@gmail.com", "mathias", "brabants", "ikbenmathias123", "0479052433", "02.11.2001");
        //Student st = new Student("r.b@gmail.com", "r", "b", "rb", "041", "02", true);
        //st.toRegister(true, "r.b@gmail.com", "r", "b", "rb", "041", "02" );
        Appliance a = new Appliance("A", "001", 20, "simon", "thalongi", false, true, true, 16 );
        //Action ac = new Action(LocalDate.parse("2001-11-02"), "power");
        //LocationDAO ldao = new LocationDAO();
        //ldao.saveLocation(l);
        //la.toRegister("y.z@gmail.com", "mathias", "brabants", "ikbenmathias123", "0479052433", "02.11.2001");
        //System.out.println(la.addRoom(la, l, 1));
        System.out.println(a.averageTemperature());
        //r.addAppliance("A", "001", 20, "simon", "thalongi", false, true, true, r );
        //System.out.println(a.energyConservationModeOn(a, LocalDate.parse("2001-11-02")));
        //System.out.println(la.getRoomsLandLord(la));
    }*/







