package application;

import java.util.Objects;

public class Person {

    public String email;
    public String firstname;
    public String lastname;
    public String telephone_number;
    public String date_of_birth;
    public String password;

    public Person(String email, String firstname, String lastname, String password, String telephone_number, String date_of_birth) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.telephone_number = telephone_number;
        this.date_of_birth = date_of_birth;
        this.password = password;
    }

    public Person() {

    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getTelephonenumber() {
        return telephone_number;
    }

    public String getDateofbirth() {
        return date_of_birth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return telephone_number == person.telephone_number
                && Objects.equals(email, person.email)
                && Objects.equals(firstname, person.firstname)
                && Objects.equals(lastname, person.lastname)
                && Objects.equals(date_of_birth, person.date_of_birth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstname, lastname, telephone_number, date_of_birth);
    }
}



