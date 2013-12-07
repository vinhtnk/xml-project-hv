package Utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author Quaikiet
 */
public class DBUtil {

    public static Connection getDBConnection() {
        Connection dbConnection = null;
        Properties prop = new Properties();
        try {
            InputStream is = DBUtil.class.getResourceAsStream("/Utils/Connection.properties");
            prop.load(is);
            is.close();
        } catch (IOException e) {
            return null;
        }
        try {
            Class.forName(prop.getProperty("driverName").toString());
            dbConnection = DriverManager.getConnection(
                   prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));

        } catch (Exception e) {
            System.out.println("Caused asd:" + e.getCause());
        }
        return dbConnection;
    }
}
