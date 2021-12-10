package db;

import application.Action;
import application.Room;
import application.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class ActionDAO {

    public ArrayList<Action> getActions(int appliance_id) {
        ArrayList<Action> actions = new ArrayList<Action>();
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT A.action_id, A.`name`, I.Appliance_appliance_id, I.`date` " +
                    "FROM action AS A INNER JOIN interacts as I " +
                    "ON (A.action_id = I.Action_action_id) " +
                    "WHERE I.Appliance_appliance_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, appliance_id);
            ResultSet srs = stmt.executeQuery();
            int Action_action_id;
            String name;
            int Appliance_appliance_id;
            LocalDate date;

            while (srs.next()) {
                Action_action_id = srs.getInt("Action_action_id");
                name = srs.getString("name");
                Appliance_appliance_id = srs.getInt("Appliance_appliance_id");
                date = srs.getDate("date").toLocalDate();

                Action action = new Action(Appliance_appliance_id, date, name); //?
                actions.add(action);
            }
            return actions;

        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }

    }

    //returns -1 if something went wrong (no action saved)
    public int saveAction(Action action, int appliance_id) {

        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT action_id "
                    + "FROM action "
                    + "WHERE action_id = ? ";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setString(1, Action.getId);
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {

                // UPDATE
                String sqlUpdate = "UPDATE action " +
                        "SET name = ?, " +
                        "WHERE action_id = ?";

                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setString(1, action.getName);
                stmt2.setInt(2, action.getId);
                stmt2.executeUpdate();
                return action.getId;
            } else {
                // INSERT

                String sqlInsert = "INSERT into action "
                        + "(name) "
                        + "VALUES (?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);
                insertStm.setString(1, action.getName());
                insertStm.executeUpdate();
                InteractsDAO interactsDAO = new InteractsDAO();
                interactsDAO.saveInteracts(Action.getId, appliance_id, Action.getDate);
                ResultSet generatedKeys = insertStm.getGeneratedKeys();
                if(generatedKeys.next()) {
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
}
