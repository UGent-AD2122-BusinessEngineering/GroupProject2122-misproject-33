package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class InteractsDAO {
    public void saveInteracts(int action_id, int appliance_id, LocalDate date) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sqlInsert = "INSERT into interacts "
                    + "(Action_action_id, Appliance_appliance_id, date) "
                    + "VALUES (?,?,?)";
            //System.out.println(sql);
            PreparedStatement insertStm = con.prepareStatement(sqlInsert);
            insertStm.setInt(1, action_id);
            insertStm.setInt(2, action_id);
            insertStm.setDate(3, java.sql.Date.valueOf(date));
            insertStm.executeUpdate();

        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
        }
    }

    //gebruik deze om een in het verleden uitgevoerde actie bij een appliance te verwijderen
    //NOG NIET GEBRUIKEN!
    public void deleteInteracts(int action_id, int appliance_id, LocalDate date) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "DELETE FROM interacts "
                    + "WHERE Action_action_id = ? AND Appliance_appliance_id = ? AND date = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, action_id);
            stmt.setInt(2, appliance_id);
            stmt.setDate(3, java.sql.Date.valueOf(date));
            stmt.executeUpdate();

            //het gedeelte dat aactions verwijdert als ze niet meer in de interacts table voorkomen
            //komt ook bij deleteAppliance wss
            String sql2 = "DELETE FROM action " +
                    "WHERE NOT EXISTS(SELECT Action_action_id " +
                    "FROM interacts a " +
                    "WHERE a.Action_action_id = ?)"; //AND >4 ofzo als we in de eerste 4 rijen standaard acties steken die niet verwijderd mogen worden
            PreparedStatement stmt2 = con.prepareStatement(sql2);
            stmt2.setInt(1, action_id);
            stmt2.executeUpdate();
        } catch (Exception dbe) {
            dbe.printStackTrace();
            DBHandler.closeConnection(con);
        }
    }

    /* public static void main(String[] args) {
        InteractsDAO interactsDAO = new InteractsDAO();
        interactsDAO.deleteInteracts(1, 1, LocalDate.now());
    } */
}
