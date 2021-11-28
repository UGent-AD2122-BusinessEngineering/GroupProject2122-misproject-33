package application;

import java.time.LocalDate;
import java.util.Objects;

public class Student extends Person {

    public Location dormRoom;

    public enum Gender {MALE, FEMALE}

    public Student(String email, String firstname, String lastname, int idnumber, int telephonenumber, int age, LocalDate dateofbirth) {
        super(email, firstname, lastname, idnumber, telephonenumber, age, dateofbirth);
    }

    //assign student to a new dorm room
    public void setRoom(Location room, Gender gender){

        if (dormRoom != null) {
            dormRoom.removeStudent(this);
        }
        dormRoom = room;
        dormRoom.addStudent(this);

        if (room == null) {
            switch (gender){
                case MALE:
                    System.out.println("Student " + lastname + " " + firstname + " with ID-number " + idnumber + " leaves his room.");
                    break;
                case FEMALE:
                    System.out.println("Student " + lastname + " " + firstname + " with ID-number " + idnumber + " leaves her room.");
                    break;
            }
        }
    }

    //test
    public void test(){
        dormRoom = new Location("Belgium","Gent",9000,250, "Krijgslaan", 526,10);
    }

}
