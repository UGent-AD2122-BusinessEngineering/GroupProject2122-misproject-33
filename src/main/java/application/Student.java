package application;
import db.StudentDAO;
import java.util.ArrayList;

public class Student extends Person {

    private boolean isContactPerson;

    public Student(String email, String firstname, String lastname, String password, String telephone_number, String date_of_birth) {
        super(email, firstname, lastname, password, telephone_number, date_of_birth);
        this.isContactPerson = false;
    }

    public Student(String email, String firstname, String lastname, String password, String telephone_number, String date_of_birth, boolean isContactPerson) {
        super(email, firstname, lastname, password, telephone_number, date_of_birth);
        this.isContactPerson = isContactPerson;
    }

    public void setContactPerson(boolean contactPerson) {
        isContactPerson = contactPerson;
    }

    public Student() {
        super();
    }

    public boolean getIsContactPerson() {
        return isContactPerson;
    }

    //get student with given room_id (db)
    public ArrayList<Student> getStudents(int room_id) {
        StudentDAO studentDAO = new StudentDAO();
        return studentDAO.getStudents(room_id);
    }

    public String toRegister(String email, String firstname, String lastname, String password, String telephone_number, String date_of_birth) {
            StudentDAO studentDAO = new StudentDAO();
            ArrayList<Student> allStudents = studentDAO.getAllStudents();
            for (Student item : allStudents) {
                if (item.getEmail().equals(email)) {
                    return "An account already exists with this email address.";
                }
            }
            Student student1 = new Student(email, firstname, lastname,PasswordHashing.doHashing(password), telephone_number, date_of_birth);
            studentDAO.save(student1);
        return "Your registration has been succesfull.";
    }

    public Object login(String email, String password) {
            StudentDAO studentDAO = new StudentDAO();
            ArrayList<Student> allStudents = studentDAO.getAllStudents();
            for (Student item : allStudents) {
                if (item.getEmail().equals(email)) {
                    if (item.password.equals(PasswordHashing.doHashing(password))) {
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
}
