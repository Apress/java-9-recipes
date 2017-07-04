
package org.java9recipes.chapter13.recipe13_02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.java9recipes.chapter13.recipe13_01.CreateConnection;

/**
 *
 * Recipe 13-2:  Handling SQL Exceptions
 * 
 * @author Juneau
 */
public class Recipe13_2 {

    private static CreateConnection createConn;

    public static void main(String[] args) {

        createConn = new CreateConnection();

        queryDatabase();

    }

    public static void queryDatabase() {
        String qry = "select * from recipes";
        try (Connection conn = createConn.getConnection();
                Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(qry);
            while (rs.next()) {
                // PERFORM SOME WORK
            	System.out.println(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
