package dbutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtility implements DBUtilityInter {

    @Override
    public Connection connect() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");//static{//massive at}

        Connection connection = DriverManager.getConnection(
                                    DBConfig.getConnectionName(),
                                    DBConfig.getUsername(),
                                    DBConfig.getPassword()
                );

        return connection;
    }

    @Override
    public void close(Connection connection) {
        if(connection==null) return;
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
