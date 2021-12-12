package db;

import application.Appliance;

import java.sql.*;
import java.util.ArrayList;

public class ApplianceDAO {
    public Appliance getAppliance(int appliance_id) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT appliance_id, name, supplier_name, model_identifier, energy_efficiency_class, annual_energy_consumption, is_temp_proportionate, is_temp_disproportionate, is_energy_conservation_mode "
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
            Boolean is_temp_proportionate;
            Boolean is_temp_disproportionate;
            Boolean is_energy_conservation_mode;

            if (srs.next()) {
                appliance_id1 = srs.getInt("appliance_id");
                name = srs.getString("name");
                supplier_name = srs.getString("supplier_name");
                model_identifier = srs.getString("model_identifier");
                energy_efficiency_class = srs.getString("energy_efficiency_class");
                annual_energy_consumption = srs.getInt("annual_energy_consumption");
                is_temp_proportionate = srs.getBoolean("is_temp_proportionate");
                is_temp_disproportionate = srs.getBoolean("is_temp_disproportionate");
                is_energy_conservation_mode = srs.getBoolean("is_energy_conservation_mode");
            } else {
                return null;
            }
            Appliance appliance = new Appliance(energy_efficiency_class, model_identifier, annual_energy_consumption, supplier_name , name, is_temp_proportionate, is_temp_disproportionate, is_energy_conservation_mode, appliance_id);
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
            String sql = "SELECT appliance_id, name, supplier_name, model_identifier, energy_efficiency_class, annual_energy_consumption, is_temp_proportionate, is_temp_disproportionate, is_energy_conservation_mode "
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
            Boolean is_temp_proportionate;
            Boolean is_temp_disproportionate;
            Boolean is_energy_conservation_mode;

            while (srs.next()) {
                appliance_id = srs.getInt("appliance_id");
                name = srs.getString("name");
                supplier_name = srs.getString("supplier_name");
                model_identifier = srs.getString("model_identifier");
                energy_efficiency_class = srs.getString("energy_efficiency_class");
                annual_energy_consumption = srs.getInt("annual_energy_consumption");
                is_temp_proportionate = srs.getBoolean("is_temp_proportionate");
                is_temp_disproportionate = srs.getBoolean("is_temp_disproportionate");
                is_energy_conservation_mode = srs.getBoolean("is_energy_conservation_mode");
                Appliance appliance = new Appliance(energy_efficiency_class, model_identifier, annual_energy_consumption, supplier_name, name, is_temp_proportionate, is_temp_disproportionate, is_energy_conservation_mode);
                appliances.add(appliance);
            }
            return appliances;

        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public ArrayList<Appliance> getAllAppliances() {
        ArrayList<Appliance> appliances = new ArrayList<Appliance>();
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "SELECT * "
                    + "FROM appliance ";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet srs = stmt.executeQuery();
            int appliance_id;
            String name;
            String supplier_name;
            String model_identifier;
            String energy_efficiency_class;
            int annual_energy_consumption;
            Boolean is_temp_proportionate;
            Boolean is_temp_disproportionate;
            Boolean is_energy_conservation_mode;

            while (srs.next()) {
                appliance_id = srs.getInt("appliance_id");
                name = srs.getString("name");
                supplier_name = srs.getString("supplier_name");
                model_identifier = srs.getString("model_identifier");
                energy_efficiency_class = srs.getString("energy_efficiency_class");
                annual_energy_consumption = srs.getInt("annual_energy_consumption");
                is_temp_proportionate = srs.getBoolean("is_temp_proportionate");
                is_temp_disproportionate = srs.getBoolean("is_temp_disproportionate");
                is_energy_conservation_mode = srs.getBoolean("is_energy_conservation_mode");
                Appliance appliance = new Appliance(energy_efficiency_class, model_identifier, annual_energy_consumption, supplier_name, name, is_temp_proportionate, is_temp_disproportionate, is_energy_conservation_mode, appliance_id);
                appliances.add(appliance);
            }
            return appliances;

        } catch (DBException | SQLException e) {
            e.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public int save(Appliance a, int room_id) {
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
                        "annual_energy_consumption = ?, " +
                        "is_temp_proportionate = ?, " +
                        "is_temp_disproportionate = ?, " +
                        "is_energy_conservation_mode = ?, " +
                        "Room_room_id = ? " +
                        "WHERE appliance_id = ?";
                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setInt(10, a.getApplianceID());
                stmt2.setString(1, a.getName());
                stmt2.setString(2, a.getSupplierName());
                stmt2.setString(3, a.getModelIdentifier());
                stmt2.setString(4, a.getEnergyEfficiencyClass());
                stmt2.setInt(5, a.getAnnualEnergyConsumption());
                stmt2.setBoolean(6, a.getIsTempProportionate());
                stmt2.setBoolean(7, a.getIsTempDisproportionate());
                stmt2.setBoolean(8, a.isEnergyConservationMode());
                stmt2.setInt(9, room_id);
                stmt2.executeUpdate();
                return a.getApplianceID();
            } else {
                // INSERT

                String sqlInsert = "INSERT into appliance "
                        + "(appliance_id, name, supplier_name, model_identifier, energy_efficiency_class, annual_energy_consumption, is_temp_proportionate, is_temp_disproportionate, is_energy_conservation_mode, Room_room_id) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
                insertStm.setInt(1, a.getApplianceID());
                insertStm.setString(2, a.getName());
                insertStm.setString(3, a.getSupplierName());
                insertStm.setString(4, a.getModelIdentifier());
                insertStm.setString(5, a.getEnergyEfficiencyClass());
                insertStm.setInt(6, a.getAnnualEnergyConsumption());
                insertStm.setBoolean(7, a.getIsTempProportionate());
                insertStm.setBoolean(8, a.getIsTempDisproportionate());
                insertStm.setBoolean(9, a.isEnergyConservationMode() );
                insertStm.setInt(10, room_id);
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

    public static void main(String[] args) {
        ApplianceDAO applianceDAO = new ApplianceDAO();
        Appliance appliance = new Appliance("B", "modelnr", 550, "zanussi", "koelkast", true, false, false, 3);
        System.out.println(applianceDAO.getAllAppliances());
    }
}
