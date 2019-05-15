package connectivity;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ConnectionClass {
    public Connection connection;

    public Connection getConnection(){

        String dbName="cABvXIaCLM";
        String userName="cABvXIaCLM";
        String password="g18bDXSg4s";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/"+dbName,userName,password);



        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}