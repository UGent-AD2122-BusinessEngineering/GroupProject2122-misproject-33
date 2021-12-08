package db;

import application.Landlord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LandlordDAO {

    public static Landlord getLandlord(String email) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT email, first_name, last_name, password, telephone_number, date_of_birth "
                    + "FROM landlord "
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
            Landlord landlord = new Landlord(email, first_name, last_name, password, telephone_number, date_of_birth);
            return landlord;

        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public static void save(Landlord l) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT email "
                    + "FROM landlord "
                    + "WHERE email = ? ";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setString(1, l.getEmail());
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {

                // UPDATE


                //deze geeft nog een error, weet niet of dit bij StudentDAO ook het geval is


                String sqlUpdate = "UPDATE landlord " +
                        "SET first_name = ?, " +
                        "last_name = ?, " +
                        "password = ?, " +
                        "telephone_number = ?, " +
                        "date_of_birth = ? " +
                        "WHERE email = ?";
                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setString(1, l.getFirstname());
                stmt2.setString(2, l.getLastname());
                stmt2.setString(3, l.getPassword());
                stmt2.setString(4, l.getTelephonenumber());
                stmt2.setString(5, l.getDateofbirth());
                stmt2.setString(6, l.getEmail());
                stmt2.executeUpdate();
            } else {
                // INSERT

                String sqlInsert = "INSERT into landlord "
                        + "(email, first_name, last_name, password, telephone_number, date_of_birth) "
                        + "VALUES (?,?,?,?,?,?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);
                insertStm.setString(1, l.getEmail());
                insertStm.setString(2, l.getFirstname());
                insertStm.setString(3, l.getLastname());
                insertStm.setString(4, l.getPassword());
                insertStm.setString(5, l.getTelephonenumber());
                insertStm.setString(6, l.getDateofbirth());
                insertStm.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public static void deleteLandlord(Landlord l){

        /*als landlord wordt verwijderd worden ook alle rooms in bezit verwijderd en ik zal nog es kijken voor die
        trigger te maken die eventueel een location verwijdert als er zich daar geen rooms meer bevinden

        Misschien kunnen business logic en presentation ervoor zorgen dat er hier een waarschuwing komt "zeker dat u uw account
        Wilt verwijderen? Al uw kamers gaan hierdoor verloren..."
        */


        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "DELETE FROM landlord "
                    + "WHERE email = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, l.getEmail());

            stmt.executeUpdate();
        } catch (DBException dbe) {
            dbe.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Landlord l = new Landlord("s.delange@gmail.be", "simon", "delange", "IKBENDIK", "0479052422", "04.12.1985");
        //LandlordDAO.save(l);
        LandlordDAO.deleteLandlord(l);
    }
}