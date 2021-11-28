package application;

import java.time.LocalDate;

public class Student extends Person {

    public Student(String email, String firstname, String lastname, int idnumber, int telephonenumber, int age, LocalDate dateofbirth) {
        super(email, firstname, lastname, idnumber, telephonenumber, age, dateofbirth);
    }

}
