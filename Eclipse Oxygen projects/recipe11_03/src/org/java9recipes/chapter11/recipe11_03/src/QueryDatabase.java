
package org.java9recipes.chapter11.recipe11_03.src;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.java9recipes.chapter13.recipe13_01.CreateConnection;

/**
 * Recipe 11-3:  Querying the database
 * 
 * @author juneau
 */
public class QueryDatabase {

    public static Connection conn = null;

    public static void main(String[] args) {
        try {
            CreateConnection createConn = new CreateConnection();
            conn = createConn.getConnection();
            queryDatabase();
        } catch (java.sql.SQLException ex) {
            System.out.println(ex);
        }

    }

    public static void queryDatabase() {
        String qry = "select recipe_num, name, description from recipes";
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(qry);
            while (rs.next()) {
                String recipe = rs.getString("RECIPE_NUM");
                String name = rs.getString("NAME");
                String desc = rs.getString("DESCRIPTION");

                System.out.println(recipe + "\t" + name + "\t" + desc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
