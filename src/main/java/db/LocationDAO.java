package db;

import application.Location;
import application.MonthlyEnergyConsumption;
import application.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationDAO {
    public int saveLocation(Location location) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sqlSelect = "SELECT location_id "
                    + "FROM location "
                    + "WHERE location_id = ?";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setInt(1, location.getID());
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {

                // UPDATE
                String sqlUpdate = "UPDATE location " +
                        "SET location_id = ?, " +
                        "country = ?, " +
                        "city = ?, " +
                        "ZIP = ?, " +
                        "street = ?" +
                        "number = ? " +
                        "WHERE location_id = ?";
                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setInt(1, location.getID());
                stmt2.setString(2, location.getCountry());
                stmt2.setString(3, location.getCity());
                stmt2.setString(4, location.getZIP());
                stmt2.setString(5, location.getStreet());
                stmt2.setString(6, location.getNumber());
                stmt2.setInt(7, location.getID());
                stmt2.executeUpdate();
                return location.getID();
            } else {
                // INSERT

                String sqlInsert = "INSERT into location "
                        + "(country, city, ZIP, street, number) "
                        + "VALUES (?,?,?,?,?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);
                insertStm.setString(1, location.getCountry());
                insertStm.setString(2, location.getCity());
                insertStm.setString(3, location.getZIP());
                insertStm.setString(4, location.getStreet());
                insertStm.setString(5, location.getNumber());
                insertStm.executeUpdate();
                ResultSet generatedKeys = insertStm.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            DBHandler.closeConnection(con);
            return -1;
        }
        return -1;
    }

    public Location getLocation(int location_id) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT country, city, ZIP, street, number, area, insulated, characteristics"
                    + "FROM location "
                    + "WHERE location_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, location_id);
            ResultSet srs = stmt.executeQuery();

            if (srs.next()) {
                String country = srs.getString("country");
                String city = srs.getString("city");
                String ZIP = srs.getString("ZIP");
                String street = srs.getString("street");
                String number = srs.getString("number");
                double area = srs.getDouble("area");
                boolean insulated = srs.getBoolean("boolean");
                String characteristics = srs.getString("characteristics");
                return new Location(country, city, ZIP, street, number, area, insulated, characteristics);
            } else {
                return null;
            }

        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }


    }

}
