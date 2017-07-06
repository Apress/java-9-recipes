
package org.java9recipes.chapter13.recipe13_07;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.java9recipes.chapter13.recipe13_01.CreateConnection;

/**
 * Recipe 13-7:
 * 
 * Example of transaction management by committing everything after all
 * transactions have successfully completed...otherwise rolling back all transactions.
 * 
 * @author juneau
 */
public class TransactionExample {
    public static Connection conn = null;

    public static void main(String[] args) {
        boolean successFlag = false;
        try {
            
            CreateConnection createConn = new CreateConnection();
            conn = createConn.getConnection();
            conn.setAutoCommit(false);
            queryDbRecipes();
            successFlag = insertRecord(
                    "13-6",
                    "Simplifying and Adding Security with Prepared Statements",
                    "Working with Prepared Statements",
                    "Recipe Text");

            if (successFlag == true){

                successFlag = insertRecord(
                        null,
                        "Simplifying and Adding Security with Prepared Statements",
                        "Working with Prepared Statements",
                        "Recipe Text");
            }

            // Commit Transactions
            if (successFlag == true)
                conn.commit();  
            else
                conn.rollback();

            conn.setAutoCommit(true);
            queryDbRecipes();
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
    
    private static void queryDbRecipes(){
        String sql = "SELECT ID, RECIPE_NUMBER, RECIPE_NAME, DESCRIPTION " +
                     "FROM RECIPES";

        try(PreparedStatement pstmt = conn.prepareStatement(sql);) {
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString(2) + ": " + rs.getString(3) + 
                                " - " + rs.getString(4));
            }           
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        
    }
    
    private static boolean insertRecord(String recipeNumber,
                              String title,
                              String description,
                              String text){
        String sql = "INSERT INTO RECIPES VALUES(" +
                     "NEXT VALUE FOR RECIPES_SEQ, ?,?,?,?)";
        boolean success = false;
        try(PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, recipeNumber);
            pstmt.setString(2, title);
            pstmt.setString(3, description);
            pstmt.setString(4, text);
            pstmt.executeUpdate();
            System.out.println("Record successfully inserted.");
            success = true;
        } catch (SQLException ex){
            success = false;
            ex.printStackTrace();
        } 
        return success;
        
    }
    
}
