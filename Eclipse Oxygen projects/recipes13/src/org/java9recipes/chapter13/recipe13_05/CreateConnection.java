package org.java9recipes.chapter13.recipe13_05;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Recipe 13-5: Simplifying Connection Management
 *
 * @author juneau
 */
public class CreateConnection {

    static Properties props = new Properties();

    String hostname = null;
    String port = null;
    String database = null;
    String username = null;
    String password = null;
    String driver = null;
    String jndi = null;
   
    public CreateConnection() {
        // Looks for properties file in the root of the src directory in Netbeans project
        try (InputStream in = Files.newInputStream(FileSystems.getDefault().
                getPath(System.getProperty("user.dir") + File.separator + "db_props.properties"));) {
            props.load(in);
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        loadProperties();
    }

    public final void loadProperties() {
        hostname = props.getProperty("host_name");
        port = props.getProperty("port_number");
        database = props.getProperty("db_name");
        username = props.getProperty("username");
        password = props.getProperty("password");
        driver = props.getProperty("driver");
        jndi = props.getProperty("jndi");

    }

    /**
     * Demonstrates obtaining a connection via DriverManager
     *
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        Connection conn = null;
        String jdbcUrl;
        if (driver.equals("derby")) {
            jdbcUrl = "jdbc:derby://" + this.hostname + ":"
                    + this.port + "/" + this.database;
        } else {
            jdbcUrl = "jdbc:oracle:thin:@" + this.hostname + ":"
                    + this.port + ":" + this.database;
        }
        conn = DriverManager.getConnection(jdbcUrl, username, password);
        System.out.println("Successfully connected");
        return conn;
    }

    /**
     * Demonstrates obtaining a connection via a DataSource object
     *
     * @return
     */
    public Connection getDSConnection() {
        Connection conn = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(this.jndi);
            conn = ds.getConnection();
        } catch (NamingException | SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    /**
     * Main method is used to test the connection...
     *
     * @param args
     */
    public static void main(String[] args) {
        CreateConnection createConnection = new CreateConnection();
        try {
            Connection conn = createConnection.getConnection();
            if (conn != null) {
                System.out.println("Closing Connection...");
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
