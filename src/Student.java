import java.util.Objects;
import java.util.Scanner;
public class Student {

    private Student student;
    private String firstname_student;
    private String lastname_student;
    private String email_student;
    private String phone_student;

    public Student(String firstname_student, String lastname_student, String email, String phone_student) {
        this.firstname_student = firstname_student;
        this.lastname_student = lastname_student;
        this.email_student = email;
        this.phone_student = phone_student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getFirstname_student() {
        return firstname_student;
    }

    public void setFirstname_student(String firstname_student) {
        this.firstname_student = firstname_student;
    }

    public String getLastname_student() {
        return lastname_student;
    }

    public void setLastname_student(String lastname_student) {
        this.lastname_student = lastname_student;
    }

    public String getEmail_student() {
        return email_student;
    }

    public void setEmail_student(String email_student) {
        this.email_student = email_student;
    }

    public String getTelefoonnummer_student() {
        return phone_student;
    }

    public void setTelefoonnummer_student(String telefoonnummer_student) {
        this.phone_student = telefoonnummer_student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student1 = (Student) o;
        return Objects.equals(student, student1.student)
                && Objects.equals(firstname_student, student1.firstname_student)
                && Objects.equals(lastname_student, student1.lastname_student)
                && Objects.equals(email_student, student1.email_student)
                && Objects.equals(phone_student, student1.phone_student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, firstname_student, lastname_student, email_student, phone_student);
    }

}
