package application;


import java.util.Objects;

public class Person {

    public String email;
    public String firstname;
    public String lastname;
    public String telephoneNumber;
    public String dateOfBirth;
    public String password;

    public Person() {
    }

    public Person(String email, String firstname, String lastname, String password, String telephoneNumber, String dateOfBirth) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.telephoneNumber = telephoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
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

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return telephoneNumber == person.telephoneNumber
                && Objects.equals(email, person.email)
                && Objects.equals(firstname, person.firstname)
                && Objects.equals(lastname, person.lastname)
                && Objects.equals(dateOfBirth, person.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstname, lastname, telephoneNumber, dateOfBirth);
    }
}



