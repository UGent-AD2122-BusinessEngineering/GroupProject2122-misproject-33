package db;

import application.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RoomDAO {

    //now returns AI generated PK, use for room_id
    public int save(Room room, int Location_location_id, String Landlord_email) { //moet wss nog een methode aan toegevoegd worden die een room toewijst aan een student.
        Connection con = null;
        try {
            con = DBHandler.getConnection();
//if room id != null
            String sqlSelect = "SELECT room_id "
                    + "FROM room "
                    + "WHERE room_id = ?";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setString(1, room.getRoomID()); //String of int???
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
                stmt2.setString(1, room.getRoomID());
                stmt2.setString(2, room.getRoomnumber());
                stmt2.setString(3, room.getLocation().getRoomID());
                stmt2.setString(4, room.getLandlord().getEmail());
                stmt2.setString(5, room.getRoomID());
                stmt2.executeUpdate();
            } else {
                // INSERT

                String sqlInsert = "INSERT into room "
                        + "(room_number, Location_location_id, Landlord_email) "
                        + "VALUES (?,?,?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);
                insertStm.setString(1, room.getRoomnumber());
                insertStm.setString(2, room.getLocation().getRoomID());
                insertStm.setString(3, room.getLandlord().getEmail());
                insertStm.executeUpdate();
                ResultSet generatedKeys = insertStm.getGeneratedKeys();
                if(generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return Location_location_id;
    }

    /* moeten eerst weten welke instantievariabelen room klasse gaat hebben, alleszins number en wss arraylist<Appliance>?
    public Room getRoom(Student student) {

        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sql1 = "SELECT Room_room_id FROM student WHERE email = ?";
            PreparedStatement stmt1 = con.prepareStatement(sql1);
            stmt1.setString(1, student.getEmail());
            ResultSet srs1 = stmt1.executeQuery();
            int room_id = srs1.getInt("Room_room_id");
        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    } */
}
