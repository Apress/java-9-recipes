package org.java9recipes.chapter13.recipe13_06;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.java9recipes.chapter13.recipe13_01.CreateConnection;

/**
 * Recipe 13-6: Using PreparedStatements
 *
 * @author juneau
 */
public class PreparedStatementExample {

    public static Connection conn = null;

    public static void main(String[] args) {
        try {
            CreateConnection createConn = new CreateConnection();
            conn = createConn.getConnection();
            String[] recipeArr = new String[1];
            recipeArr[0] ="13-1";
            queryDbRecipe(recipeArr);
            insertRecord(
                    "13-6",
                    "Simplifying and Adding Security with Prepared Statements",
                    "Working with Prepared Statements",
                    "Recipe Text");
            recipeArr[0] ="13-6";
            queryDbRecipe(recipeArr);
            deleteRecord("13-6");
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

    private static void queryDbRecipe(String[] recipeNumbers) {
        String sql = "SELECT ID, RECIPE_NUMBER, RECIPE_NAME, DESCRIPTION "
                + "FROM RECIPES "
                + "WHERE RECIPE_NUMBER = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (String recipeNumber : recipeNumbers) {
                pstmt.setString(1, recipeNumber);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getString(2) + ": " + rs.getString(3)
                            + " - " + rs.getString(4));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private static void insertRecord(String recipeNumber,
            String title,
            String description,
            String text) {
        String sql = "INSERT INTO RECIPES VALUES("
                + "NEXT VALUE FOR RECIPES_SEQ, ?,?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, recipeNumber);
            pstmt.setString(2, title);
            pstmt.setString(3, description);
            pstmt.setString(4, text);
            pstmt.executeUpdate();
            System.out.println("Record successfully inserted.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void deleteRecord(String recipeNumber) {
        String sql = "DELETE FROM RECIPES WHERE "
                + "RECIPE_NUMBER = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, recipeNumber);
            pstmt.executeUpdate();
            System.out.println("Recipe " + recipeNumber + " successfully deleted.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
