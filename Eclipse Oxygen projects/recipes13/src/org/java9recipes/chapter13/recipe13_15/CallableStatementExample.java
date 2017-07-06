
package org.java9recipes.chapter13.recipe13_15;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import org.java9recipes.chapter13.recipe13_01.CreateConnection;

/**
 * Recipe 13-15
 * 
 * Example of using a CallableStatement to execute a stored procedure.
 * 
 * @author juneau
 */
public class CallableStatementExample {
    
    public static Connection conn = null;
    public static CreateConnection createConn;

    public static void main(String[] args) {
     //   boolean successFlag = false;
        try {
            createConn = new CreateConnection();
            conn = createConn.getConnection();
            callableStatementEx();
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
    
    public static void callableStatementEx(){
     
        try(CallableStatement cs = conn.prepareCall("{call DUMMY_PROC(?,?)}");) {
       
        cs.setString(1, "This is a test");
        cs.registerOutParameter(2, Types.VARCHAR);
        cs.executeQuery();

        System.out.println(cs.getString(2));

        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
