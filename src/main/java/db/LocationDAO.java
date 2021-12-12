package db;

import application.Location;
import java.sql.*;
import java.util.ArrayList;

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
                        "area = ?" +
                        "insulated = ?" +
                        "characteristics = ?" +
                        "WHERE location_id = ?";
                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setInt(1, location.getID());
                stmt2.setString(2, location.getCountry());
                stmt2.setString(3, location.getCity());
                stmt2.setString(4, location.getZIP());
                stmt2.setString(5, location.getStreet());
                stmt2.setString(6, location.getNumber());
                stmt2.setDouble(7, location.getArea());
                stmt2.setBoolean(8, location.isInsulated());
                stmt2.setString(9, location.getCharacteristics());
                stmt2.setInt(10, location.getID());
                stmt2.executeUpdate();
                return location.getID();
            } else {
                // INSERT

                String sqlInsert = "INSERT into location "
                        + "(country, city, ZIP, street, number, area, insulated, characteristics) "
                        + "VALUES (?,?,?,?,?,?,?,?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
                insertStm.setString(1, location.getCountry());
                insertStm.setString(2, location.getCity());
                insertStm.setString(3, location.getZIP());
                insertStm.setString(4, location.getStreet());
                insertStm.setString(5, location.getNumber());
                insertStm.setDouble(6, location.getArea());
                insertStm.setBoolean(7, location.isInsulated());
                insertStm.setString(8, location.getCharacteristics());
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
            String sql = "SELECT * "
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
                boolean insulated = srs.getBoolean("insulated");
                String characteristics = srs.getString("characteristics");
                int location_id1 = srs.getInt("location_id");
                return new Location(country, city, ZIP, street, number, area, insulated, characteristics, location_id1);
            } else {
                return null;
            }
        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public ArrayList<Location> getAllLocations() {
        ArrayList<Location> locations = new ArrayList<Location>();
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT * "
                    + "FROM location ";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet srs = stmt.executeQuery();

            while (srs.next()) {
                String country = srs.getString("country");
                String city = srs.getString("city");
                String ZIP = srs.getString("ZIP");
                String street = srs.getString("street");
                String number = srs.getString("number");
                double area = srs.getDouble("area");
                boolean insulated = srs.getBoolean("insulated");
                String characteristics = srs.getString("characteristics");
                int location_id = srs.getInt("location_id");
                locations.add(new Location(country, city, ZIP, street, number, area, insulated, characteristics, location_id));
            }
            return locations;
        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public static void main(String[] args) {
        LocationDAO locationDAO = new LocationDAO();
        Location location = new Location("France", "Paris", "baguette", "fromage", "69", 420, false, "EKIP");
        System.out.println(locationDAO.getAllLocations());
    }

}
