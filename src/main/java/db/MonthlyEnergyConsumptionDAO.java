package db;

import application.MonthlyEnergyConsumption;
import application.Room;

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

}
