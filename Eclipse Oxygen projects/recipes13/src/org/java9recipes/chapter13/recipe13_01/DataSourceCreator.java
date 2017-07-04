
package org.java9recipes.chapter13.recipe13_01;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Recipe 13-1:  Example for creating a datasource
 * 
 * @author juneau
 */
public class DataSourceCreator {

    public void createDataSource() {
        org.java9recipes.chapter13.recipe13_01.FakeDataSourceDriver ds =
                new org.java9recipes.chapter13.recipe13_01.FakeDataSourceDriver();
        ds.setServerName("localhost");
        ds.setDatabaseName("db1");
        ds.setDescription("Database connection for Java 7 Recipes");

    }

    public void registerDS() {
        try {
            Context ctx = new InitialContext();
            DataSource ds =
                    (DataSource) ctx.lookup("jdbc/db1");
            CreateConnection createConnection = new CreateConnection();
                        
            try {
				Connection connection = ds.getConnection(createConnection.getUsername(), createConnection.getPassword());
				
				System.out.println("Successfully connected");
				connection.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
    	DataSourceCreator creator = new DataSourceCreator();
    	creator.createDataSource();
    	creator.registerDS();
    	
    	
    	
	}
}
