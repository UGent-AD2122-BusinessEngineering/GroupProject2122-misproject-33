package application;

import java.time.LocalDate;

public class Action {
    private String date;
    private String name;
    private int Id;

    public Action() {
    }

    public Action(String date, String name) {
        this.date = date;
        this.name = name;
    }

    public Action(String date, String name, int id) {
        this.date = date;
        this.name = name;
        this.Id = id;
    }

    public String getMonth(){
        return date;
    }


    public int getId() {
        return Id;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }
}







