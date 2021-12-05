package application;

import java.time.LocalDate;
import java.util.Objects;

public class Person {

    public String email;
    public String firstname;
    public String lastname;
    public int idnumber;
    public int telephonenumber;
    public int age;
    public LocalDate dateofbirth;
    public enum Gender {MALE, FEMALE}

    public Person(String email, String firstname, String lastname, int idnumber, int telephonenumber, int age, LocalDate dateofbirth) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.idnumber = idnumber;
        this.telephonenumber = telephonenumber;
        this.age = age;
        this.dateofbirth = dateofbirth;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getFirstname() {return firstname;}

    public void setFirstname(String firstname) {this.firstname = firstname;}

    public String getLastname() {return lastname;}

    public void setLastname(String lastname) {this.lastname = lastname;}

    public int getIdnumber() {return idnumber;}

    public void setIdnumber(int idnumber) {this.idnumber = idnumber;}

    public int getTelephonenumber() {return telephonenumber;}

    public void setTelephonenumber(int telephonenumber) {this.telephonenumber = telephonenumber;}

    public int getAge() {return age;}

    public void setAge(int age) {this.age = age;}

    public LocalDate getDateofbirth() {return dateofbirth;}

    public void setDateofbirth(LocalDate dateofbirth) {this.dateofbirth = dateofbirth;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return idnumber == person.idnumber
                && telephonenumber == person.telephonenumber
                && age == person.age && Objects.equals(email, person.email)
                && Objects.equals(firstname, person.firstname)
                && Objects.equals(lastname, person.lastname)
                && Objects.equals(dateofbirth, person.dateofbirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstname, lastname, idnumber, telephonenumber, age, dateofbirth);
    }
}
