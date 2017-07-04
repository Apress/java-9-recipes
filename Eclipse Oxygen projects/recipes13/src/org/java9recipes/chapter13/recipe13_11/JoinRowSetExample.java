
package org.java9recipes.chapter13.recipe13_11;

import com.sun.rowset.JoinRowSetImpl;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import org.java9recipes.chapter13.recipe13_01.CreateConnection;

/**
 * Recipe 13-11:  Example of using a JoinRowSet.
 * 
 * @author juneau
 */
public class JoinRowSetExample {

    public static Connection conn = null;
    public static CreateConnection createConn;
    public static CachedRowSet bookAuthors = null;
    public static CachedRowSet authorWork = null;
    public static JoinRowSet jrs = null;

    public static void main(String[] args) {
      //  boolean successFlag = false;
        try {
            createConn = new CreateConnection();
            conn = createConn.getConnection();
            // Perform Scrollable Query
            queryBookAuthor();
            queryAuthorWork();
            
            joinRowQuery();
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
            if (bookAuthors != null) {
                try {
                    bookAuthors.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (authorWork != null) {
                try {
                    authorWork.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (jrs != null) {
                try {
                    jrs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

    public static void queryBookAuthor() {
        RowSetFactory factory;

        try {
            // Create a new RowSetFactory
            factory = RowSetProvider.newFactory();

            // Create a CachedRowSet object using the factory
            bookAuthors = factory.createCachedRowSet();

            // Alternatively populate the CachedRowSet connection settings
            // crs.setUsername(createConn.getUsername());
            // crs.setPassword(createConn.getPassword());
            // crs.setUrl(createConn.getJdbcUrl());

            // Populate a query that will obtain the data that will be used
            bookAuthors.setCommand("SELECT ID, LAST_NAME, FIRST_NAME FROM BOOK_AUTHOR");

            bookAuthors.execute(conn);

            // You can now work with the object contents in a disconnected state
            while (bookAuthors.next()) {
                System.out.println(bookAuthors.getString(1) + ": " + bookAuthors.getString(2)
                        + ", " + bookAuthors.getString(3));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void queryAuthorWork() {
        RowSetFactory factory;

        try {
            // Create a new RowSetFactory
            factory = RowSetProvider.newFactory();

            // Create a CachedRowSet object using the factory
            authorWork = factory.createCachedRowSet();

            // Alternatively populate the CachedRowSet connection settings
            // crs.setUsername(createConn.getUsername());
            // crs.setPassword(createConn.getPassword());
            // crs.setUrl(createConn.getJdbcUrl());

            // Populate a query that will obtain the data that will be used
            authorWork.setCommand("SELECT AUTHOR_WORK.ID, AUTHOR_ID, B.TITLE FROM AUTHOR_WORK, " +
                    "BOOK B " +
                    "WHERE B.ID = AUTHOR_WORK.ID");

            authorWork.execute(conn);

            // You can now work with the object contents in a disconnected state
            while (authorWork.next()) {
                System.out.println(authorWork.getString(1) + ": " + authorWork.getString(2)
                        + " - " + authorWork.getString(3));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void joinRowQuery() {
        try {
            // Create JoinRowSet
            jrs = new JoinRowSetImpl();
            
            // Add RowSet & Corresponding Keys
            jrs.addRowSet(bookAuthors, 1);
            jrs.addRowSet(authorWork, 2);
            
            // Traverse Results
            while(jrs.next()){
                System.out.println(jrs.getInt("ID") + ": " + 
                                   jrs.getString("TITLE") + " - " +
                                   jrs.getString("FIRST_NAME") + " " + 
                                   jrs.getString("LAST_NAME"));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            
    }
}
