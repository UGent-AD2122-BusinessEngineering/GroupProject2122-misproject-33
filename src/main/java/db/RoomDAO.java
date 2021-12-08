package db;

import application.Room;
import application.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDAO {
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
