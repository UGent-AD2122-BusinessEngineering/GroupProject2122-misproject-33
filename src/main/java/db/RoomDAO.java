package db;

import application.Room;
import java.sql.*;
import java.util.ArrayList;

public class RoomDAO {

    public Room getRoom(int room_id) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT room_id, room_number, Location_location_id "
                    + "FROM room "
                    + "WHERE room_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, room_id);
            ResultSet srs = stmt.executeQuery();
            int room_number;
            int Location_location_id;
            int room_id1;

            LocationDAO locationDAO = new LocationDAO();
            if (srs.next()) {
                room_number = srs.getInt("room_number");
                Location_location_id = srs.getInt("Location_location_id");
                room_id1 = srs.getInt("room_id");
            }
            else {
                return null;
            }
            Room room = new Room(room_number, locationDAO.getLocation(Location_location_id), room_id1);
            return room;
        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public int save(Room room, String Landlord_email) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sqlSelect = "SELECT room_id "
                    + "FROM room "
                    + "WHERE room_id = ?";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setInt(1, room.getRoomID());
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {
                // UPDATE
                String sqlUpdate = "UPDATE room " +
                        "SET room_id = ?, " +
                        "room_number = ?, " +
                        "Location_location_id = ?, " +
                        "Landlord_email = ?, " +
                        "WHERE room_id = ?";
                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setInt(1, room.getRoomID());
                stmt2.setInt(2, room.getRoomnumber());
                stmt2.setInt(3, room.getLocation().getID());
                stmt2.setString(4, Landlord_email);
                stmt2.setInt(5, room.getRoomID());
                stmt2.executeUpdate();
                return room.getRoomID();
            } else {
                // INSERT
                String sqlInsert = "INSERT into room "
                        + "(room_number, Location_location_id, Landlord_email) "
                        + "VALUES (?,?,?)";
                PreparedStatement insertStm = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
                insertStm.setInt(1, room.getRoomnumber());
                insertStm.setInt(2, room.getLocation().getID());
                insertStm.setString(3, Landlord_email);
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

    public ArrayList<Room> getRooms(String Landlord_email) {
        ArrayList<Room> rooms = new ArrayList<Room>();
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT room_id, room_number, Location_location_id "
                    + "FROM room "
                    + "WHERE Landlord_email = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, Landlord_email);
            ResultSet srs = stmt.executeQuery();
            int room_id;
            int room_number;
            int Location_location_id;

            LocationDAO locationDAO = new LocationDAO();

            while (srs.next()) {
                room_id = srs.getInt("room_id");
                room_number = srs.getInt("room_number");
                Location_location_id = srs.getInt("Location_location_id");
                Room room = new Room(room_number, locationDAO.getLocation(Location_location_id), room_id);
                rooms.add(room);
            }
            return rooms;
        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public Room getRoom(String studentEmail) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sql1 = "SELECT Room_room_id FROM student WHERE email = ?";
            PreparedStatement stmt1 = con.prepareStatement(sql1);
            stmt1.setString(1, studentEmail);
            ResultSet srs1 = stmt1.executeQuery();
            int room_id = 0;
            if(srs1.next()) {
                room_id = srs1.getInt("Room_room_id");
            }
            Room room = getRoom(room_id);
            return room;
        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public void deleteRoom(int room_id) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            //obtain location_id from room
            String sql = "SELECT * FROM room "
                    + "WHERE room_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, room_id);
            ResultSet srs = stmt.executeQuery();
            int locationID = 0;
            if(srs.next()) {
                locationID = srs.getInt("Location_location_id");
            }

            //delete room
            String sql1 = "DELETE FROM room "
                    + "WHERE room_id = ?";
            PreparedStatement stmt1 = con.prepareStatement(sql1);
            stmt1.setInt(1, room_id);
            stmt1.executeUpdate();

            //delete location if there aren't any rooms left on that location
            String sql2 = "DELETE FROM location L " +
                    "WHERE NOT EXISTS(SELECT room_id " +
                    "FROM room R " +
                    "WHERE R.Location_location_id = L.location_id) " +
                    "AND L.location_id = ?";
            PreparedStatement stmt2 = con.prepareStatement(sql2);
            stmt2.setInt(1, locationID);
            stmt2.executeUpdate();
        } catch (Exception dbe) {
            dbe.printStackTrace();
            DBHandler.closeConnection(con);
        }
    }

    public ArrayList<Room> getAllRooms() {
        ArrayList<Room> rooms = new ArrayList<Room>();
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT * "
                    + "FROM room";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet srs = stmt.executeQuery();
            int room_id;
            int room_number;
            int Location_location_id;

            LocationDAO locationDAO = new LocationDAO();

            while (srs.next()) {
                room_id = srs.getInt("room_id");
                room_number = srs.getInt("room_number");
                Location_location_id = srs.getInt("Location_location_id");
                Room room = new Room(room_number, locationDAO.getLocation(Location_location_id), room_id);
                rooms.add(room);
            }
            return rooms;
        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }
}
