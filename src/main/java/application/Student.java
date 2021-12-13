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

    public String toRegister(Boolean student, String email, String firstname, String lastname, String password, String telephone_number, String date_of_birth) {
            StudentDAO studentDAO = new StudentDAO();
            ArrayList<Student> allStudents = studentDAO.getAllStudents();
            for (Student item : allStudents) {
                if (item.getEmail().equals(email)) {
                    return "An account already exists with this email address.";
                }
            }
            Student student1 = new Student(email, firstname, lastname, password, telephone_number, date_of_birth);
            studentDAO.save(student1);
        return "Your registration has been succesfull.";
    }

    public Object login(String email, String password) {
            StudentDAO studentDAO = new StudentDAO();
            ArrayList<Student> allStudents = studentDAO.getAllStudents();
            for (Student item : allStudents) {
                if (item.getEmail().equals(email)) {
                    if (item.password.equals(password)) {
                        return item;
                    }
                }
            }
            String message = "Your e-mail or password was incorrect.";
            return message;
    }

    public boolean loginSucces(String email, String password) {
        StudentDAO studentDAO = new StudentDAO();
        ArrayList<Student> allStudents = studentDAO.getAllStudents();
        for (Student item : allStudents) {
            if (item.getEmail().equals(email)) {
                if (item.password.equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    /*public ArrayList<String> getPerformedEnergyConservationActions() {
        return performedEnergyConservationActions;
    }*/



}
