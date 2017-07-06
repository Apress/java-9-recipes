
package org.java9recipes.chapter13.recipe13_10;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.spi.SyncProviderException;
import org.java9recipes.chapter13.recipe13_01.CreateConnection;

/**
 * Recipe 13-10:  Example of using CachedRowSet
 * 
 * @author juneau
 */
public class CachedRowSetExample {

    public static Connection conn = null;
    public static CreateConnection createConn;
    public static CachedRowSet crs = null;

    public static void main(String[] args) {
       // boolean successFlag = false;
        try {
            createConn = new CreateConnection();
            conn = createConn.getConnection();
            // Perform Scrollable Query
            queryWithRowSet();
            
            // Update the CachedRowSet
            updateData();
            
            // Synchronize changes
            syncWithDatabase();
        } catch (java.sql.SQLException ex) {
            System.out.println(ex);
        } finally {

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

    /**
     * Call this method to synchronize the data that has been used in the
     * CachedRowSet with the database
     */
    public static void syncWithDatabase() {
        try {
            crs.acceptChanges(conn);
        } catch (SyncProviderException ex) {
            // If there is a conflict while synchronizing, this exception
            // will be thrown.
            ex.printStackTrace();
        } finally {
            // Clean up resources by closing CachedRowSet
            if (crs != null) {
                try {
                    crs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void queryWithRowSet() {
        RowSetFactory factory;

        try  {

            // Create a new RowSetFactory
            factory = RowSetProvider.newFactory();

            // Create a CachedRowSet object using the factory
            crs = factory.createCachedRowSet();

            // Alternatively opulate the CachedRowSet connection settings
            // crs.setUsername(createConn.getUsername());
            // crs.setPassword(createConn.getPassword());
            // crs.setUrl(createConn.getJdbcUrl());

            // Populate a query that will obtain the data that will be used
            crs.setCommand("select id, recipe_number, recipe_name, description from recipes");
            // Set key columns
            int[] keys = {1};
            crs.setKeyColumns(keys);
            crs.execute(conn);

            // You can now work with the object contents in a disconnected state
            while (crs.next()) {
                System.out.println(crs.getString(2) + ": " + crs.getString(3)
                        + " - " + crs.getString(4));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean updateData() {
        boolean returnValue = false;
        try {
            // Move to the position before the first row in the result set
            crs.beforeFirst();
            
            // traverse result set
            while (crs.next()) {
                // If the recipe_num equals 11-2 then update
                if (crs.getString("RECIPE_NUMBER").equals("13-2")) {
                    System.out.println("updating recipe 13-2");
                    crs.updateString("description", "Subject to change");
                    crs.updateRow();
                }

            }
          returnValue = true;
          
          // Move to the position before the first row in the result set
            crs.beforeFirst();
            
            // traverse result set to see changes
            while (crs.next()) {

                    System.out.println(crs.getString(2) + ": " + crs.getString(3)
                        + " - " + crs.getString(4));
    

            }
          
        } catch (SQLException ex) {
            returnValue = false;
            ex.printStackTrace();
        }
        return returnValue;
    }
}
