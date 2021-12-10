package db;

import application.MonthlyEnergyConsumption;
import application.Room;
import application.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


//testen
public class MonthlyEnergyConsumptionDAO {
    public MonthlyEnergyConsumption getMonthlyEnergyConsumption(int room_id, LocalDate date) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT water_consmuption, electricity_consumption, gas_consumption"
                    + "FROM `monthly energy consumption` "
                    + "WHERE room_id = ?" +
                    "AND month = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, room_id);
            stmt.setDate(2, java.sql.Date.valueOf(date));
            ResultSet srs = stmt.executeQuery();

            if (srs.next()) {
                double water = srs.getDouble("water_consmuption");
                double electricity = srs.getDouble("electricity_consumption");
                double gas = srs.getDouble("gas_consumption");
                return new MonthlyEnergyConsumption(electricity, gas, water, date);
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
            stmt.setString(1, m.getMonthlyEnergyConsumptionID());
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
                stmt2.setInt(6, m.getMonthlyEnergyConsumptionID());
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
                insertStm.setInt(1, m.getMonthlyEnergyConsumption);
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

    public void deleteMonthlyEnergyConsumption(String monthly_energy_consumption_id) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "DELETE FROM monthly_energy_consumption "
                    + "WHERE monthly_energy_consumption_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, monthly_energy_consumption_id);

            stmt.executeUpdate();
        } catch (Exception dbe) {
            dbe.printStackTrace();
            DBHandler.closeConnection(con);
        }
    }


}
