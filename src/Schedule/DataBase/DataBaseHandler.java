package Schedule.DataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseHandler {
    protected Connection getDbConnection()
            throws ClassNotFoundException,SQLException{
        String connectionString = "jdbc:mysql://" + Const.dbHost
                + ":" + Const.dbPort + "/" + Const.dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(connectionString, Const.dbUser, Const.dbPass);
    }
}
