package db;

import application.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    //returns true if given password equals the password of the user with the given email_adress
    public static boolean checkPassword(String email, String password) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT password "
                    + "FROM student "
                    + "WHERE email = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet userPassword = statement.executeQuery();

            if (userPassword.getString(1).equals(password)) return true;
            else return false;
        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return false;
        }
    }

    public static Student getStudent(String email) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT email, first_name, last_name, password, telephone_number, date_of_birth "
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

            if (srs.next()) {
                email1 = srs.getString("email");
                first_name = srs.getString("first_name");
                last_name = srs.getString("last_name");
                password = srs.getString("password");
                telephone_number = srs.getString("telephone_number");
                date_of_birth = srs.getString("date_of_birth");
            } else {
                return null;
            }
            Student student = new Student(email, first_name, last_name, telephone_number, date_of_birth, password);
            return student;

        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null; }
    }

    public static void main(String[] args) {
        System.out.println(StudentDAO.getStudent("mbtopper@gmail.com").getFirstname());
    }
}