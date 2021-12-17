package db;

import application.MonthlyEnergyConsumption;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MonthlyEnergyConsumptionDAO {

    public MonthlyEnergyConsumption getMonthlyEnergyConsumption(int monthly_energy_consumption_id) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT * "
                    + "FROM monthly_energy_consumption "
                    + "WHERE monthly_energy_consumption_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, monthly_energy_consumption_id);
            ResultSet srs = stmt.executeQuery();

            if (srs.next()) {
                double water = srs.getDouble("water_consumption");
                double electricity = srs.getDouble("electricity_consumption");
                double gas = srs.getDouble("gas_consumption");
                int monthlyEnergyConsumptionId = srs.getInt("monthly_energy_consumption_id");
                String month = srs.getString("month");
                return new MonthlyEnergyConsumption(electricity, gas, water, month, monthlyEnergyConsumptionId);
            } else {
                return null;
            }
        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public MonthlyEnergyConsumption getMonthlyEnergyConsumption(int room_id, String date) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT * "
                    + "FROM monthly_energy_consumption "
                    + "WHERE Room_room_id = ? " +
                    "AND month = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, room_id);
            stmt.setString(2, date);
            ResultSet srs = stmt.executeQuery();

            if (srs.next()) {
                double water = srs.getDouble("water_consumption");
                double electricity = srs.getDouble("electricity_consumption");
                double gas = srs.getDouble("gas_consumption");
                int monthlyEnergyConsumptionId = srs.getInt("monthly_energy_consumption_id");
                return new MonthlyEnergyConsumption(electricity, gas, water, date, monthlyEnergyConsumptionId);
            } else {
                return null;
            }
        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public int save(MonthlyEnergyConsumption m, int room_id) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT monthly_energy_consumption_id "
                    + "FROM monthly_energy_consumption "
                    + "WHERE monthly_energy_consumption_id = ?";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setInt(1, m.getMonthlyEnergyConsumptionId());
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {
                // UPDATE
                String sqlUpdate = "UPDATE monthly_energy_consumption " +
                        "SET month = ?, " +
                        "water_consumption = ?, " +
                        "electricity_consumption = ?, " +
                        "gas_consumption = ?, " +
                        "Room_room_id = ? " +
                        "WHERE monthly_energy_consumption_id = ?";
                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setInt(6, m.getMonthlyEnergyConsumptionId());
                stmt2.setString(1, m.getMonth());
                stmt2.setDouble(2, m.getWater());
                stmt2.setDouble(3, m.getElectricity());
                stmt2.setDouble(4, m.getGas());
                stmt2.setInt(5, room_id);
                stmt2.executeUpdate();
                return m.getMonthlyEnergyConsumptionId();
            } else {
                // INSERT
                String sqlInsert = "INSERT into monthly_energy_consumption "
                        + "(month, water_consumption, electricity_consumption, gas_consumption, Room_room_id) "
                        + "VALUES (?,?,?,?,?)";
                PreparedStatement insertStm = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
                insertStm.setString(1, m.getMonth());
                insertStm.setDouble(2, m.getWater());
                insertStm.setDouble(3, m.getElectricity());
                insertStm.setDouble(4, m.getGas());
                insertStm.setInt(5, room_id);
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

    public void deleteMonthlyEnergyConsumption(int monthlyEnergyConsumptionId) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "DELETE FROM monthly_energy_consumption "
                    + "WHERE monthly_energy_consumption_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, monthlyEnergyConsumptionId);
            stmt.executeUpdate();
        } catch (Exception dbe) {
            dbe.printStackTrace();
            DBHandler.closeConnection(con);
        }
    }

    public ArrayList<MonthlyEnergyConsumption> getAllMonthlyEnergyConsumptions() {
        ArrayList<MonthlyEnergyConsumption> monthlyEnergyConsumptions = new ArrayList<MonthlyEnergyConsumption>();
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT * " +
                    "FROM monthly_energy_consumption";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet srs = stmt.executeQuery();

            while (srs.next()) {
                double water = srs.getDouble("water_consumption");
                double electricity = srs.getDouble("electricity_consumption");
                double gas = srs.getDouble("gas_consumption");
                String month = srs.getString("month");
                int monthlyEnergyConsumptionId = srs.getInt("monthly_energy_consumption_id");
                MonthlyEnergyConsumption monthlyEnergyConsumption = new MonthlyEnergyConsumption(electricity, gas, water, month, monthlyEnergyConsumptionId);
                monthlyEnergyConsumptions.add(monthlyEnergyConsumption);
            }
            return monthlyEnergyConsumptions;
        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public ArrayList<MonthlyEnergyConsumption> getAllMonthlyEnergyConsumptions(int room_id) {
        ArrayList<MonthlyEnergyConsumption> monthlyEnergyConsumptions = new ArrayList<MonthlyEnergyConsumption>();
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT * " +
                    "FROM monthly_energy_consumption " +
                    "WHERE Room_room_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, room_id);
            ResultSet srs = stmt.executeQuery();

            while (srs.next()) {
                double water = srs.getDouble("water_consumption");
                double electricity = srs.getDouble("electricity_consumption");
                double gas = srs.getDouble("gas_consumption");
                String month = srs.getString("month");
                int monthlyEnergyConsumptionId = srs.getInt("monthly_energy_consumption_id");
                MonthlyEnergyConsumption monthlyEnergyConsumption = new MonthlyEnergyConsumption(electricity, gas, water, month, monthlyEnergyConsumptionId);
                monthlyEnergyConsumptions.add(monthlyEnergyConsumption);
            }
            return monthlyEnergyConsumptions;
        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public static void main(String[] args) {
        MonthlyEnergyConsumptionDAO monthlyEnergyConsumptionDAO = new MonthlyEnergyConsumptionDAO();
        //LocalDate date = LocalDate.parse("2021-12-01");
        //MonthlyEnergyConsumption monthlyEnergyConsumption = new MonthlyEnergyConsumption(100, 0, 100, date, 3);
        //System.out.println(monthlyEnergyConsumptionDAO.getAllMonthlyEnergyConsumptions(4));
        monthlyEnergyConsumptionDAO.deleteMonthlyEnergyConsumption(2);
    }
}
