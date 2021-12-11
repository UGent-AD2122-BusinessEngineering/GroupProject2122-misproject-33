package db;

import application.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAO {

    public Student getStudent(String email) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT email, first_name, last_name, password, telephone_number, date_of_birth, is_contact_person "
                    + "FROM student "
                    + "WHERE email = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet srs = stmt.executeQuery();
            String email1;
            String first_name;
            String last_name;
            String password;
            String telephone_number;
            String date_of_birth;
            boolean is_contact_person;

            if (srs.next()) {
                email1 = srs.getString("email");
                first_name = srs.getString("first_name");
                last_name = srs.getString("last_name");
                password = srs.getString("password");
                telephone_number = srs.getString("telephone_number");
                date_of_birth = srs.getString("date_of_birth");
                is_contact_person = srs.getBoolean("is_contact_person");
            } else {
                return null;
            }
            Student student = new Student(email, first_name, last_name, password, telephone_number, date_of_birth, is_contact_person);
            return student;

        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    //returns ArrayList<Student> met alle studenten die in de gegeven kamer wonen
    public ArrayList<Student> getStudents(int room_id) {
        ArrayList<Student> students = new ArrayList<Student>();
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT email, first_name, last_name, password, telephone_number, date_of_birth "
                    + "FROM student "
                    + "WHERE Room_room_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, room_id);
            ResultSet srs = stmt.executeQuery();
            String email;
            String first_name;
            String last_name;
            String password;
            String telephone_number;
            String date_of_birth;
            boolean is_contact_person;

            while (srs.next()) {
                email = srs.getString("email");
                first_name = srs.getString("first_name");
                last_name = srs.getString("last_name");
                password = srs.getString("password");
                telephone_number = srs.getString("telephone_number");
                date_of_birth = srs.getString("date_of_birth");
                is_contact_person = srs.getBoolean("is_contact_person");
                Student student = new Student(email, first_name, last_name, password, telephone_number, date_of_birth, is_contact_person);
                students.add(student);
            }
            return students;

        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }

    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<Student>();
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT * "
                    + "FROM student ";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet srs = stmt.executeQuery();
            String email;
            String first_name;
            String last_name;
            String password;
            String telephone_number;
            String date_of_birth;
            boolean is_contact_person;

            while (srs.next()) {
                email = srs.getString("email");
                first_name = srs.getString("first_name");
                last_name = srs.getString("last_name");
                password = srs.getString("password");
                telephone_number = srs.getString("telephone_number");
                date_of_birth = srs.getString("date_of_birth");
                is_contact_person = srs.getBoolean("is_contact_person");
                Student student = new Student(email, first_name, last_name, password, telephone_number, date_of_birth, is_contact_person);
                students.add(student);
            }
            return students;

        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }

    }

    //throws SQLIntegrityConstraintViolationException indien we proberen een Student toe te voegen aan een niet bestaande room
    public void save(Student s) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT email "
                    + "FROM student "
                    + "WHERE email = ? ";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setString(1, s.getEmail());
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {
                System.out.println("Student already saved, use update method");
                DBHandler.closeConnection(con);
                return;
            } else {
                // INSERT

                String sqlInsert = "INSERT into student "
                        + "(email, first_name, last_name, password, telephone_number, date_of_birth, is_contact_person) "
                        + "VALUES (?,?,?,?,?,?,?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);
                insertStm.setString(1, s.getEmail());
                insertStm.setString(2, s.getFirstname());
                insertStm.setString(3, s.getLastname());
                insertStm.setString(4, s.getPassword());
                insertStm.setString(5, s.getTelephonenumber());
                insertStm.setString(6, s.getDateofbirth());
                insertStm.setBoolean(7, s.getIsContactPerson());
                insertStm.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            DBHandler.closeConnection(con);
        }
    }

    public void update(Student s, int room_id) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT email "
                    + "FROM student "
                    + "WHERE email = ? ";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setString(1, s.getEmail());
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {

                // UPDATE
                String sqlUpdate = "UPDATE student " +
                        "SET first_name = ?, " +
                        "last_name = ?, " +
                        "password = ?, " +
                        "telephone_number = ?, " +
                        "date_of_birth = ?, " +
                        "is_contact_person = ? " +
                        "Room_room_id = ?, " +
                        "WHERE email = ?";
                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setString(8, s.getEmail());
                stmt2.setString(1, s.getFirstname());
                stmt2.setString(2, s.getLastname());
                stmt2.setString(3, s.getPassword());
                stmt2.setString(4, s.getTelephonenumber());
                stmt2.setString(5, s.getDateofbirth());
                stmt2.setBoolean(6, s.getIsContactPerson());
                stmt2.setInt(7, room_id);
                stmt2.executeUpdate();
            } else {
                System.out.println("student nog niet toegevoegd in database, gebruik save methode en daarna update om room aan te maken");
                DBHandler.closeConnection(con);
                return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            DBHandler.closeConnection(con);
        }
    }

    public void deleteStudent(String email) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "DELETE FROM student "
                    + "WHERE email = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.executeUpdate();
        } catch (Exception dbe) {
            dbe.printStackTrace();
            DBHandler.closeConnection(con);
        }
    }

    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        //System.out.println(studentDAO.getStudent("mbtopper@gmail.com").getFirstname());
        Student a = new Student("mathibrab@gm.co", "mathi", "brab", "test1", "0479052448", "02.11.2001");
        Student b = new Student("bob", "simon", "ffg", "IKBsdfENDIK", "fgh", "bert");
        Student c = new Student("tyhd", "sisdfgsdmon", "delange", "IKBENDyjjIK", "0479052422", "04.12.1985");
        Student d = new Student("rhdfgs", "sdftgtr", "ffdth", "ttsrs", "erhed", "04.12.1985");


        studentDAO.save(a);
        studentDAO.save(b);
        studentDAO.save(c);
        studentDAO.save(d);
        //studentDAO.deleteStudent(s);

        /* ArrayList<Student> students = studentDAO.getStudents(1);
        for(Student student : students) {
            System.out.println(student.getTelephonenumber());
        } */
    }
}