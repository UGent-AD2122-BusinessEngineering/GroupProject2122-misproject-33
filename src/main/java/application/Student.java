package application;

import db.DBException;
import db.DBHandler;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.StudentDAO;
import java.util.ArrayList;
import java.util.HashMap;

public class Student extends Person {
    private Location location;
    private ArrayList<Action> energyConservationActions;
    private ArrayList<Student> studentlist;
    private int room_id;


    public Student(String email, String firstname, String lastname, String password, String telephone_number, String date_of_birth, boolean aangemeld) {
        super(email, firstname, lastname, password, telephone_number, date_of_birth, aangemeld);
        this.room_id = room_id;
        this.energyConservationActions = new ArrayList<>();
        this.studentlist = new ArrayList<Student>();
        this.energyConservationActions = new ArrayList<Action>();
    }

    public Student() {
        super();
    }

    //returns true if given (not yet encoded) password equals the password of the user with the given email address
    public static boolean checkPassword(String email, String password) {
        StudentDAO studentDAO = new StudentDAO();
        if(studentDAO.getStudent(email).getPassword().equals(PasswordHashing.doHashing(password))) {
            return true;
        }
        else return false;
    }

    //get student with given room_id (db)
    public ArrayList<Student> getStudents(int room_id) {
        StudentDAO studentDAO = new StudentDAO();
        return studentDAO.getStudents(room_id);
    }

    //add student with given object and room_id (db)
    public boolean addStudent(Student student, int room_id){
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.save(student, room_id);
        if (studentlist == null || studentlist.contains(student)) {
            return false;
        }
        studentlist.add(student);
        return true;
    }

    //delete student
    public void deleteStudent(Student student){
        System.out.println("Delete student " + student.getFirstname() + student.getLastname());
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.deleteStudent(student);
        for(Student stu: student.getStudents(room_id)) {
            if (stu.equals(student)) {
                student.getStudents(room_id).remove(stu);
                return;
            }
        }
        return;
    }

    //prints students
    public void listUsers() {
        if(studentlist.isEmpty())
        {
            System.out.println("List is empty!");
        }
        else
        {
            for (Student student : this.studentlist)
            {
                System.out.println("Student id: " + student.getFirstname() + student.getLastname());
                StudentDAO studentDAO = new StudentDAO();
            }
        }
    }

    public void addEnergyConservationAction (Action energyConservationAction){
        //communiceren met db (zie vb)
    }

    public void getEnergyConservationActions (){
        //communiceren met db (zie vb)
        //is geen void maar gwn om geen error te krijgen geeft arraylist terug
    }

    /*public ArrayList<String> getPerformedEnergyConservationActions() {
        return performedEnergyConservationActions;
    }*/



}
