package application;

import db.StudentDAO;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

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

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getFirstname() {return firstname;}

    public void setFirstname(String firstname) {this.firstname = firstname;}

    public String getLastname() {return lastname;}

    public void setLastname(String lastname) {this.lastname = lastname;}

    public String getTelephonenumber() {return telephone_number;}

    public void setTelephonenumber(String telephonenumber) {this.telephone_number = telephonenumber;}

    public String getDateofbirth() {return date_of_birth;}

    public void setDateofbirth(String dateofbirth) {this.date_of_birth = dateofbirth;}

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

    //niet af
    public String toRegister(Boolean student, String email, String firstname, String lastname, String password, String telephone_number, String date_of_birth){
        if (student) {
            StudentDAO studentDAO = new StudentDAO();
            ArrayList<Student> allStudents = studentDAO.getAllStudents();
            for (Student item : allStudents){
                if (item.getEmail().equals(email)){
                    return "Het account dat u probeerde te maken bestaat al.";
                }
            }
            Student student1 = new Student(email, firstname, lastname, password, telephone_number, date_of_birth);
        }
        else {
            Landlord landlord = new Landlord(email, firstname, lastname, password, telephone_number, date_of_birth);
        }
            return "Registratie is succesvol verlopen.";
        }
    }



    /*public void registreren (String password){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Bent u student?");//kunnen misschien met knoppen werken ofzo kwn of dat haalbaar is
        boolean student = keyboard.nextBoolean();
        if (student){
            System.out.println("Wat is uw e-mailadres?");
            String email = keyboard.nextLine();
            System.out.println("Wat is uw voornaam?");
            String voornaam = keyboard.nextLine();
            System.out.println("Wat is uw familienaam?");
            String familienaam = keyboard.nextLine();
            System.out.println("Wat is uw telefoonnummer?");
            String telefoonnummer = keyboard.nextLine();
            System.out.println("Wat is uw geboortedatum?");
            String geboortedatum = keyboard.nextLine();
            System.out.println("Geef uw wachtwoord in alstublieft");
            String wachtwoord = keyboard.nextLine();
            boolean flag = false;
            while (!flag) {
                System.out.println("Herhaal uw wachtwoord alstublieft");
                String wachtoordherh = keyboard.nextLine();
                if (!(wachtwoord.equals(wachtoordherh)))
                    System.out.println("De twee wachtwoorden komen niet overeen.");
                else
                    flag = true;
            }
            System.out.println("Uw registratie is succesvol verlopen");
            boolean aangemeld = true;
            Student student1 = new Student(email, voornaam, familienaam, wachtwoord, telefoonnummer, geboortedatum, aangemeld);
        }

        if(!student){
            System.out.println("Wat is uw e-mailadres?");
            String email = keyboard.nextLine();
            System.out.println("Wat is uw voornaam?");
            String voornaam = keyboard.nextLine();
            System.out.println("Wat is uw familienaam?");
            String familienaam = keyboard.nextLine();
            System.out.println("Wat is uw telefoonnummer?");
            String telefoonnummer = keyboard.nextLine();
            System.out.println("Wat is uw geboortedatum?");
            String geboortedatum = keyboard.nextLine();
            System.out.println("Geef uw wachtwoord in alstublieft");
            String wachtwoord = keyboard.nextLine();
            boolean flag = false;
            while (!flag) {
                System.out.println("Herhaal uw wachtwoord alstublieft");
                String wachtoordherh = keyboard.nextLine();
                if (!(wachtwoord.equals(wachtoordherh)))
                    System.out.println("De twee wachtwoorden komen niet overeen.");
                else
                    flag = true;
            }
            System.out.println("Uw registratie is succesvol verlopen.");
            boolean aangemeld = true;
            Landlord landlord = new Landlord(email, voornaam, familienaam, telefoonnummer, geboortedatum, wachtwoord, aangemeld);
        }
    }

    public void afmelden (Person person){
        person.aangemeld = false;
    }*/



