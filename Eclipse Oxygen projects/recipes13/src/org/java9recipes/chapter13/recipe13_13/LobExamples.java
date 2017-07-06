package org.java9recipes.chapter13.recipe13_13;


import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.java9recipes.chapter13.recipe13_01.CreateConnection;

/**
 * Recipe 13-13:  Example of using LOB
 * 
 * @author juneau
 */
public class LobExamples {

    public static Connection conn = null;
    public static CreateConnection createConn;

    public static void main(String[] args) {
    //    boolean successFlag = false;
        try {
            createConn = new CreateConnection();
            conn = createConn.getConnection();
            loadClob();
            readClob();
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

    public static void loadClob() {
        Clob textClob = null;
        String sql = "INSERT INTO RECIPE_TEXT VALUES("
                    + "next value for recipe_text_seq, "
                    + "(select id from recipes where recipe_number = '13-1'), "
                    + "?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
            textClob = conn.createClob();
            textClob.setString(1, "This will be the recipe text in clob format");
            // obtain the sequence number in real world
            // set the clob value
            pstmt.setClob(1, textClob);
            pstmt.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
    }

    public static void readClob() {
        String qry = "select text from recipe_text";
        Clob theClob = null;
        try(PreparedStatement pstmt = conn.prepareStatement(qry);
                ResultSet rs = pstmt.executeQuery();) {

            while (rs.next()) {
                theClob = rs.getClob(1);
                System.out.println("Clob length: " + theClob.length());
                System.out.println(theClob.toString());
            }
            System.out.println(theClob.toString());
            

        } catch (SQLException ex) {

            ex.printStackTrace();
        } 
    }
}
