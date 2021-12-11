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
        this.studentlist = new ArrayList<Student>();
        this.energyConservationActions = new ArrayList<Action>();
        this.isContactPerson = false;
    }

    public Student(String email, String firstname, String lastname, String password, String telephone_number, String date_of_birth, boolean isContactPerson) {
        super(email, firstname, lastname, password, telephone_number, date_of_birth);
        this.isContactPerson = isContactPerson;
        this.studentlist = new ArrayList<Student>();
        this.energyConservationActions = new ArrayList<Action>();

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
    public void addStudent(Student student, int room_id){
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.save(student);
        studentDAO.update(student, room_id);
        if (studentlist == null || studentlist.contains(student)) {
            return;
        }
        studentlist.add(student);
    }

    //delete student
    public void deleteStudent(Student student, String roomnumber, int room_id){
        System.out.println("Delete student " + student.getFirstname() + student.getLastname());
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.deleteStudent(student.getEmail());
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


    /*public ArrayList<String> getPerformedEnergyConservationActions() {
        return performedEnergyConservationActions;
    }*/



}
