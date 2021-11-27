package application;

import java.time.LocalDate;
import java.util.Objects;

public class Landlord extends Person {

    public Landlord(String email, String firstname, String lastname, int idnumber, int telephonenumber, int age, LocalDate dateofbirth) {
        super(email, firstname, lastname, idnumber, telephonenumber, age, dateofbirth);
    }
}
