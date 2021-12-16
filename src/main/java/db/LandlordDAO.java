package db;

import application.Landlord;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LandlordDAO {

    public Landlord getLandlord(String email) {
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
            Landlord landlord = new Landlord(email1, first_name, last_name, password, telephone_number, date_of_birth);
            return landlord;

        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public ArrayList<Landlord> getAllLandlords() {
        ArrayList<Landlord> landlords = new ArrayList<Landlord>();
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT * "
                    + "FROM landlord";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet srs = stmt.executeQuery();
            String email;
            String first_name;
            String last_name;
            String password;
            String telephone_number;
            String date_of_birth;

            while (srs.next()) {
                email = srs.getString("email");
                first_name = srs.getString("first_name");
                last_name = srs.getString("last_name");
                password = srs.getString("password");
                telephone_number = srs.getString("telephone_number");
                date_of_birth = srs.getString("date_of_birth");
                Landlord landlord = new Landlord(email, first_name, last_name, password, telephone_number, date_of_birth);
                landlords.add(landlord);
            }
            return landlords;

        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }

    }

    //returns email van de landlord van een bepaalde room, kan dan via getLandlord omgezet worden in Landlord object
    public String getLandlordEmail(int room_id) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sql = "SELECT Landlord_email "
                    + "FROM room "
                    + "WHERE room_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, room_id);
            ResultSet srs = stmt.executeQuery();
            if(srs.next()) {
                return srs.getString("Landlord_email");
            }
            else return null; //room bestaat dus niet
        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public void save(Landlord l) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT email "
                    + "FROM landlord "
                    + "WHERE email = ?";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setString(1, l.getEmail());
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {

                // UPDATE
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
                stmt2.setString(4, l.getTelephoneNumber());
                stmt2.setString(5, l.getDateOfBirth());
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
                insertStm.setString(5, l.getTelephoneNumber());
                insertStm.setString(6, l.getDateOfBirth());
                insertStm.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            DBHandler.closeConnection(con);
        }
    }

    public void deleteLandlord(String email){

        /*Misschien kunnen business logic en presentation ervoor zorgen dat er hier een waarschuwing komt "zeker dat u uw account
        wilt verwijderen? Al uw kamers gaan hierdoor verloren..."*/

        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "DELETE FROM landlord "
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
        LandlordDAO landlordDAO = new LandlordDAO();
        //System.out.println(landlordDAO.getLandlord("a.b@gmail.com").getFirstname());
        //Landlord l = new Landlord("s.delange@gmail.be", "simon", "delange", "IKBENDIK", "0479052422", "04.12.1985");
        //System.out.println(l.getFirstname());
        //landlordDAO.save(l);
        //landlordDAO.deleteLandlord(l);
        System.out.println(landlordDAO.getAllLandlords());
        //.deleteLandlord("c.d@gmail.com");
    }
}