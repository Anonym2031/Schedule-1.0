package Schedule.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetUsers {
    private String firstName;
    private String lastName;
    private String schoolNumber;
    private String userName;
    private static String schID;

    String getSchID() {
        return schID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSchoolNumber() {
        return schoolNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void getUserName(String login) {
        String query = "SELECT * FROM dasacucak.users WHERE username = '" + login + "';";
        Statement statement;
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        try {
            statement = dataBaseHandler.getDbConnection().createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                firstName = rs.getString(Const.USERS_FIRSTNAME);
                lastName = rs.getString(Const.USERS_LASTNAME);
                schoolNumber = rs.getString(Const.USERS_SCHOOL);
                schID = rs.getString(Const.USERS_SCHID);
                userName = rs.getString(Const.USERS_USERNAME);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
