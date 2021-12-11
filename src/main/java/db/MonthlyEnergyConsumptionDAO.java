package db;

import application.Action;
import application.MonthlyEnergyConsumption;
import application.Room;
import application.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;


//testen
public class MonthlyEnergyConsumptionDAO {
    public MonthlyEnergyConsumption getMonthlyEnergyConsumption(int room_id, LocalDate date) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT water_consumption, electricity_consumption, gas_consumption, monthly_energy_consumption_id"
                    + "FROM `monthly_energy_consumption` "
                    + "WHERE room_id = ?" +
                    "AND month = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, room_id);
            stmt.setDate(2, java.sql.Date.valueOf(date));
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

    public void save(MonthlyEnergyConsumption m, int room_id) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT monthly_energy_consumption_id "
                    + "FROM monthly_energy_consumption "
                    + "WHERE monthly_energy_consumption_id = ? ";

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
                        "Room_room_id = ?, " +
                        "WHERE monthly_energy_consumption_id = ?";
                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setInt(6, m.getMonthlyEnergyConsumptionId());
                stmt2.setDate(1, java.sql.Date.valueOf(m.getMonth()));
                stmt2.setDouble(2, m.getWater());
                stmt2.setDouble(3, m.getElectricity());
                stmt2.setDouble(4, m.getGas());
                stmt2.setInt(5, room_id);
                stmt2.executeUpdate();
            } else {
                // INSERT

                String sqlInsert = "INSERT into monthly_energy_consumption "
                        + "(monthly_energy_consumption_id, month, water_consumption, electricity_consumption, gas_consumption, Room_room_id) "
                        + "VALUES (?,?,?,?,?,?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);
                insertStm.setInt(1, m.getMonthlyEnergyConsumptionId());
                insertStm.setDate(2, java.sql.Date.valueOf(m.getMonth()));
                insertStm.setDouble(3, m.getWater());
                insertStm.setDouble(4, m.getElectricity());
                insertStm.setDouble(5, m.getGas());
                insertStm.setInt(6, room_id);
                insertStm.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            DBHandler.closeConnection(con);
        }
    }

    public void deleteMonthlyEnergyConsumption(String monthlyEnergyConsumptionId) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "DELETE FROM monthly_energy_consumption "
                    + "WHERE monthly_energy_consumption_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, monthlyEnergyConsumptionId);

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
                LocalDate month = srs.getDate("month").toLocalDate();
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
                    "FROM monthly_energy_consumption" +
                    "WHERE Room_room_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, room_id);
            ResultSet srs = stmt.executeQuery();

            while (srs.next()) {
                double water = srs.getDouble("water_consumption");
                double electricity = srs.getDouble("electricity_consumption");
                double gas = srs.getDouble("gas_consumption");
                LocalDate month = srs.getDate("month").toLocalDate();
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

}
