package sample.DBHandler;

import java.net.InetAddress;
import java.sql.*;

public class DatabaseHandler {
    public Connection conn;

    public DatabaseHandler() {
        try {
            // Create a named constant for the URL.
            // NOTE: This value is specific for Java DB.
            final String DB_URL = "jdbc:derby:ServerDB;create=true";
            //final String DB_URL = "jdbc:derby:ServerDB;";
            // Create a connection to the database.
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn =
                    DriverManager.getConnection(DB_URL);

            System.out.println("Database Connected");
            // If the DB already exists, drop the tables.
            //dropTables(conn);

            //if doesn't exist a Registerd user table,write code to create it under that condition
            // Build the Registered user table table.
            //buildRegisteredUserTable(conn);

            // Close the connection.
            //conn.close();
        } catch (Exception e) {
            System.out.println("Error Connecting database");
            System.out.println(e.getMessage());
        }

    }

    /**
     * The dropTables method drops any existing
     * in case the database already exists.
     */
    public static void dropTables(Connection conn) {
        System.out.println("Checking for existing tables.");

        try {
            // Get a Statement object.
            Statement stmt = conn.createStatement();

            try {
                // Drop the UnpaidOrder table.
                stmt.execute("DROP TABLE REG_USERS");
                System.out.println("REG_USERS table dropped.");
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
            }
        } catch (SQLException ex) {
            System.out.println("ERROR1: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * The buildRegisteredUserTable method creates the
     * REG_USERS table and adds some rows to it.
     */
    public static void buildRegisteredUserTable(Connection conn) {
        try {
            // Get a Statement object.
            Statement stmt = conn.createStatement();
            //before executing this check if the table alrady exist.
            // Create the table.
            stmt.execute("CREATE TABLE REG_USERS (" +
                    "USERNAME VARCHAR(30) NOT NULL PRIMARY KEY, " +
                    "IP VARCHAR(20) , " +
                    "PORT INT " +
                    ")");

            // Insert row #1.
            stmt.execute("INSERT INTO REG_USERS VALUES ( " +
                    "'dinushi123', " +
                    "'192.12.34.5', " +
                    "1034 )");

            System.out.println("REG_USER Table created.");
        } catch (SQLException ex) {
            System.out.println("ERROR2: " + ex.getMessage());
        }
    }

    public void selectFromRegisteredUserTable(String s) {
        try {
            // Get a Statement object.
            Statement stmt = conn.createStatement();
            stmt.execute(s);
            //method is not completly written
            System.out.println("Statement has been successfully  executed.");
        } catch (SQLException ex) {
            System.out.println("ERROR3: " + ex.getMessage());
        }

        }

        public boolean updateRegUserTable(String username, InetAddress ip, int port) {
            try {

                String template = "INSERT INTO REG_USERS (USERNAME,IP,PORT) values (?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(template);
                stmt.setString(1,username);
                stmt.setString(2, String.valueOf(ip));
                stmt.setInt(3,port);
                stmt.executeUpdate();

                System.out.println("Statement has been successfully  executed.");
                return true;
            } catch (SQLException ex) {
                System.out.println("ERROR4: " + ex.getMessage());
                return false;
            }
        }
        public void select(){
            try {
                // Get a Statement object.
                Statement stmt = conn.createStatement();
                ResultSet rs =stmt.executeQuery("SELECT * FROM REG_USERS");
                while (rs.next()){
                    String username=rs.getString(1);

                    System.out.println("usename "+username);
                }
            } catch (SQLException ex) {
                System.out.println("ERROR3: " + ex.getMessage());
            }
        }
        //this method validate the ip and port of the new user
        public  boolean checkForUniqueness(String username,InetAddress ip, int port){
            try {
                PreparedStatement statement = conn.prepareStatement("select * from REG_USERS where IP = ? and port=?");
                statement.setString(1,String.valueOf(ip));
                statement.setInt(2,port);
                ResultSet resultSet = statement.executeQuery();
                statement.clearParameters();
                if(resultSet.next()){
                    System.out.println("The given ip and port combination is already registered.");
                    return false;
                }else{
                    PreparedStatement stmt = conn.prepareStatement("select * from REG_USERS where USERNAME = ?");
                    statement.setString(1,username);
                    ResultSet rs = statement.executeQuery();
                    stmt.clearParameters();//may be this cause trouble######################
                    if(rs.next()) {
                        System.out.println("The given username is already aquired.");
                        return  false;
                    }else{
                        System.out.println("A new unique peer request has found.");
                        return  true;
                    }
                }
            } catch (SQLException ex) {
                System.out.println("ERROR3: " + ex.getMessage());
            }
            return  true;
        }

        public void disconnect() throws SQLException {
            conn.close();
        }
    }





