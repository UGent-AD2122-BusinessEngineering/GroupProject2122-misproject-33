package db;

import application.Action;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ActionDAO {
    public Action getAction(int action_id) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT * FROM action WHERE action_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, action_id);
            ResultSet srs = stmt.executeQuery();
            int action_id1;
            String name;
            String date;

            if (srs.next()) {
                action_id1 = srs.getInt("action_id");
                name = srs.getString("name");
                date = srs.getString("date");
            } else {
                return null;
            }
            Action action = new Action(date, name, action_id1);
            return action;

        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public ArrayList<Action> getActions(int appliance_id) {
        ArrayList<Action> actions = new ArrayList<Action>();
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT * " +
                    "FROM action " +
                    "WHERE Appliance_appliance_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, appliance_id);
            ResultSet srs = stmt.executeQuery();
            int action_id;
            String name;
            String date;

            while (srs.next()) {
                action_id = srs.getInt("action_id");
                name = srs.getString("name");
                date = srs.getString("date");

                Action action = new Action(date, name, action_id);
                actions.add(action);
            }
            return actions;
        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public ArrayList<Action> getAllActions() {
        ArrayList<Action> actions = new ArrayList<Action>();
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT * " +
                    "FROM action";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet srs = stmt.executeQuery();
            int action_id;
            String name;
            String date;

            while (srs.next()) {
                action_id = srs.getInt("action_id");
                name = srs.getString("name");
                date = srs.getString("date");
                Action action = new Action(date, name, action_id);
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
                    + "WHERE action_id = ?";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setInt(1, action.getId());
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {

                // UPDATE
                String sqlUpdate = "UPDATE action " +
                        "SET name = ? " +
                        "WHERE action_id = ?";

                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setString(1, action.getName());
                stmt2.setInt(2, action.getId());
                stmt2.executeUpdate();
                return action.getId();
            } else {
                // INSERT

                String sqlInsert = "INSERT into action "
                        + "(name, date, Appliance_appliance_id) "
                        + "VALUES (?,?,?)";
                PreparedStatement insertStm = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
                insertStm.setString(1, action.getName());
                insertStm.setString(2, action.getDate());
                insertStm.setInt(3, appliance_id);

                insertStm.executeUpdate();
                ResultSet generatedKeys = insertStm.getGeneratedKeys();
                int generatedKey = -1;
                if(generatedKeys.next()) {
                    generatedKey = generatedKeys.getInt(1);
                }
                return generatedKey;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            DBHandler.closeConnection(con);
            return -1;
        }
    }

    public void deleteAction(int action_id) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "DELETE FROM action "
                    + "WHERE action_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, action_id);
            stmt.executeUpdate();
        } catch (Exception dbe) {
            dbe.printStackTrace();
            DBHandler.closeConnection(con);
        }
    }

    public static void main(String[] args) {
        ActionDAO actionDAO = new ActionDAO();
        //LocalDate localDate = LocalDate.parse("2021-12-22");
        //Action action = new Action(localDate,"test");
        //System.out.println(actionDAO.getAllActions());
        actionDAO.deleteAction(9);
    }
}
