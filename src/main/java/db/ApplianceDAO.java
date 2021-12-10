package db;

import application.Appliance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ApplianceDAO {
    public Appliance getAppliance(int appliance_id) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT appliance_id, name, supplier_name, model_indetifier, energy_efficiency_class, annual_energy_consumption  "
                    + "FROM appliance "
                    + "WHERE appliance_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, appliance_id);
            ResultSet srs = stmt.executeQuery();
            int appliance_id1;
            String name;
            String supplier_name;
            String model_identifier;
            String energy_efficiency_class;
            int annual_energy_consumption;

            if (srs.next()) {
                appliance_id1 = srs.getInt("appliance_id");
                name = srs.getString("name");
                supplier_name = srs.getString("supplier_name");
                model_identifier = srs.getString("model_identifier");
                energy_efficiency_class = srs.getString("energy_efficiency_class");
                annual_energy_consumption = srs.getInt("annual_energy_consumption");
            } else {
                return null;
            }
            Appliance appliance = new Appliance(energy_efficiency_class, model_identifier, annual_energy_consumption, supplier_name , name);
            return appliance;

        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public ArrayList<Appliance> getAppliances(int room_id) {
        ArrayList<Appliance> appliances = new ArrayList<Appliance>();
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT appliance_id, name, supplier_name, model_indetifier, energy_efficiency_class, annual_energy_consumption  "
                    + "FROM appliance "
                    + "WHERE Room_room_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, room_id);
            ResultSet srs = stmt.executeQuery();
            int appliance_id;
            String name;
            String supplier_name;
            String model_identifier;
            String energy_efficiency_class;
            int annual_energy_consumption;

            while (srs.next()) {
                appliance_id = srs.getInt("appliance_id");
                name = srs.getString("name");
                supplier_name = srs.getString("supplier_name");
                model_identifier = srs.getString("model_identifier");
                energy_efficiency_class = srs.getString("energy_efficiency_class");
                annual_energy_consumption = srs.getInt("annual_energy_consumption");
                Appliance appliance = new Appliance(energy_efficiency_class, model_identifier, annual_energy_consumption, supplier_name, name);
                appliances.add(appliance);
            }
            return appliances;

        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public void save(Appliance a, int room_id) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT appliance_id "
                    + "FROM appliance "
                    + "WHERE appliance_id = ? ";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setInt(1, a.getApplianceID());
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {

                // UPDATE
                String sqlUpdate = "UPDATE appliance " +
                        "SET name = ?, " +
                        "supplier_name = ?, " +
                        "model_identifier = ?, " +
                        "energy_efficiency_class = ?, " +
                        "annual_energy_consumption = ? " +
                        "Room_room_id = ?, " +
                        "WHERE appliance_id = ?";
                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setInt(7, a.getApplianceID());
                stmt2.setString(1, a.getName());
                stmt2.setString(2, a.getSupplierName());
                stmt2.setString(3, a.getModelIdentifier());
                stmt2.setString(4, a.getEnergyEfficiencyClass());
                stmt2.setInt(5, a.getAnnualEnergyConsumption());
                stmt2.setInt(6, room_id);
                stmt2.executeUpdate();
            } else {
                // INSERT

                String sqlInsert = "INSERT into appliance "
                        + "(appliance_id, name, supplier_name, model_identifier, energy_efficiciency_class, annual_energy_consumption, Room_room_id) "
                        + "VALUES (?,?,?,?,?,?,?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);
                insertStm.setInt(1, a.getApplianceID());
                insertStm.setString(2, a.getName());
                insertStm.setString(3, a.getSupplierName());
                insertStm.setString(4, a.getModelIdentifier());
                insertStm.setString(5, a.getEnergyEfficiencyClass());
                insertStm.setInt(6, a.getAnnualEnergyConsumption());
                insertStm.setInt(7, room_id);
                insertStm.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            DBHandler.closeConnection(con);
        }
    }
    public void deleteAppliance(int appliance_id) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "DELETE FROM appliance "
                    + "WHERE appliance_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, appliance_id);

            stmt.executeUpdate();
        } catch (Exception dbe) {
            dbe.printStackTrace();
            DBHandler.closeConnection(con);

        }


    }

}
