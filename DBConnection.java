package Database;

import java.sql.*;

public class DBConnection {

    public static Connection conn = null;

    public static Connection getConnection(){
        try{
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:~/test","sa","");
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return conn;
    }

}
