package application;
import db.StudentDAO;
import java.util.ArrayList;

public class Student extends Person {

    private Location location;
    private ArrayList<Action> energyConservationActions;
    private ArrayList<Student> studentlist;
    private boolean isContactPerson;
    private int room_id;

    public Student(String email, String firstname, String lastname, String password, String telephone_number, String date_of_birth) {
        super(email, firstname, lastname, password, telephone_number, date_of_birth);
        this.room_id = room_id;
        this.isContactPerson = false;
    }

    public Student(String email, String firstname, String lastname, String password, String telephone_number, String date_of_birth, boolean isContactPerson) {
        super(email, firstname, lastname, password, telephone_number, date_of_birth);
        this.isContactPerson = isContactPerson;
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

    public boolean getIsContactPerson() {
        return isContactPerson;
    }



    //get student with given room_id (db)
    public ArrayList<Student> getStudents(int room_id) {
        StudentDAO studentDAO = new StudentDAO();
        return studentDAO.getStudents(room_id);
    }

    //moet nog ergens kunnen toevoegen of het contactperson is van de room
    //add student with given object and room_id (db)
    //moet ook nog met room en dan van room de room_id nemen




    public String deleteStudent(Student student){
        String message = " ";
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.deleteStudent(student.getEmail());
        return message = "the student " + student.getFirstname() + " " + student.getLastname() + " was succesfully deleted.";
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


    /*public ArrayList<String> getPerformedEnergyConservationActions() {
        return performedEnergyConservationActions;
    }*/



}
