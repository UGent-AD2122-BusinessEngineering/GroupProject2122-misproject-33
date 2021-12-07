package db;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Deze klasse heb ik aangemaakt omdat het openen en sluiten van een connectie
 * niet enkel voor de DBStudent klasse zou zijn, maar voor alle klasse in de
 * persistence layer.
 *
 * @author stevmert
 */
public class DBHandler {

    private static final String DB_NAME = "db2021_33";//vul hier uw databank naam in
    private static final String DB_PASS = "618023915564c";//vul hier uw databank paswoord in

    private static Connection connection;

    public static Connection getConnection() throws DBException {
        if(connection != null)
            return connection;
        else
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                String URL2 = "jdbc:mysql://pdbmbook.com:3306/db2021_33";

                connection = DriverManager.getConnection(URL2, DB_NAME, DB_PASS);
                return connection;
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                closeConnection(connection);
                throw new DBException(sqle);
            } catch (ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
                closeConnection(connection);
                throw new DBException(cnfe);
            } catch (Exception ex) {
                ex.printStackTrace();
                closeConnection(connection);
                throw new DBException(ex);
            }
    }

    public static void closeConnection(Connection con) {
        try {
                if(con != null)
                    con.close();
        } catch (SQLException sqle) {
            //do nothing
        }
    }
}