package application;

import java.time.LocalDate;
import java.util.Objects;

public class Person {

    public String email;
    public String firstname;
    public String lastname;
    public int telephone_number;
    public int age;
    public LocalDate date_of_birth;

    public Person(String email, String firstname, String lastname, int telephone_number, int age, LocalDate date_of_birth) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.telephone_number = telephone_number;
        this.age = age;
        this.date_of_birth = date_of_birth;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getFirstname() {return firstname;}

    public void setFirstname(String firstname) {this.firstname = firstname;}

    public String getLastname() {return lastname;}

    public void setLastname(String lastname) {this.lastname = lastname;}

    public int getTelephonenumber() {return telephone_number;}

    public void setTelephonenumber(int telephonenumber) {this.telephone_number = telephonenumber;}

    public int getAge() {return age;}

    public void setAge(int age) {this.age = age;}

    public LocalDate getDateofbirth() {return date_of_birth;}

    public void setDateofbirth(LocalDate dateofbirth) {this.date_of_birth = dateofbirth;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return telephone_number == person.telephone_number
                && age == person.age && Objects.equals(email, person.email)
                && Objects.equals(firstname, person.firstname)
                && Objects.equals(lastname, person.lastname)
                && Objects.equals(date_of_birth, person.date_of_birth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstname, lastname, telephone_number, age, date_of_birth);
    }
}
