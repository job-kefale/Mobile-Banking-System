import java.sql.*;

public class DbConnection {

    Connection con = null;

    public Connection Connection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        // System.out.println("Connecting to a selected database...");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mobilebanking", "beki",
                "berekett21");
        return con;
    }

}
