package org.java9recipes.chapter13.recipe13_14;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import org.java9recipes.chapter13.recipe13_01.CreateConnection;

/**
 * Recipe 13-14:  Obtaining Date Instances
 * @author Juneau
 */
public class DatesTimes {

    static CreateConnection createConn = new CreateConnection();

    public static void main(String[] args) {
        insertRecord(
                "Java 9 Recipes",
                "APRESS");
    }

    private static void insertRecord(
            String title,
            String publisher) {
        String sql = "INSERT INTO PUBLICATION VALUES("
                + "NEXT VALUE FOR PUBLICATION_SEQ, ?,?,?,?)";
        LocalDate pubDate = LocalDate.now();
        try (Connection conn = createConn.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, 100);
            pstmt.setString(2, title);
            pstmt.setDate(3,  java.sql.Date.valueOf(pubDate));
            pstmt.setString(4, publisher);
            pstmt.executeUpdate();
            System.out.println("Record successfully inserted.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
