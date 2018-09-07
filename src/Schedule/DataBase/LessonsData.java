package Schedule.DataBase;

import Schedule.Layouts.TableLayout.TableViewer;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

public class LessonsData {

    public  String getSchoolNumber() {
        return schoolNumber;
    }

    private static String schoolNumber = "1";

    public  String getSchoolKey() {
        return schoolKey;
    }

    private static String schoolKey = "a";

    public  String getSemesterKey() {
        return semesterKey;
    }

    private static String semesterKey = "first_semester";

    public void intiDate(String login, String classNumber, String classKey,String semesterKey , String query ,ObservableList<TableViewer> tableViewers ) {
        GetUsers get_user = new GetUsers();
        get_user.getUserName(login);
        String schid = get_user.getSchID();
        query = "SELECT * FROM dasacucak." + schid +"_" + classNumber +"_" + classKey +"_" + semesterKey + ";";
        String newQuery = "CREATE TABLE IF NOT EXISTS dasacucak." + schid + "_" + classNumber +"_" + classKey +"_" + semesterKey + "(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                "monday TINYTEXT ,tuesday TINYTEXT ,wednesday TINYTEXT ," +
                "thursday TINYTEXT ,friday TINYTEXT );";
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        try {
            Statement statement = null;
            statement = dataBaseHandler.getDbConnection().createStatement();
            statement.execute(newQuery);
            ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    tableViewers.add(new TableViewer(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6)));
                }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String SwitchClass(ComboBox<String> schoolSelect) {
        schoolSelect.setOnHidden(event -> schoolNumber = schoolSelect.getValue());
        return schoolNumber;
    }
    public String SwitchSemesterKey(ComboBox<String> schoolSelect3) {
        schoolSelect3.setOnHidden(event -> semesterKey = schoolSelect3.getValue());
        if(semesterKey.equals("1-ին Կիսամյակ"))
            return "first_semester";
        else {
            return "second_semester";
        }
    }

   public String SwitchClassKey(ComboBox<String> schoolSelect1 ,ComboBox<String> semesterSelect ,TableView<TableViewer> schoolTable, ObservableList<TableViewer> tableViewers,
                              String login , String query){
        schoolSelect1.setOnHidden(event -> {
            switch (schoolSelect1.getValue()) {
                case "ա":
                    schoolKey = "a";
                    break;
                case "բ":
                    schoolKey = "b";
                    break;
                case "գ":
                    schoolKey = "g";
                    break;
                case "դ":
                    schoolKey = "d";
                    break;
                default:
                    break;
            }
            semesterSelect.setOnHidden(event1 -> {
                if(semesterSelect.getValue().equals("1-ին Կիսամյակ")) {
                    semesterKey = "first_semester";
                    schoolTable.getItems().removeAll(tableViewers);
                    intiDate(login, schoolNumber, schoolKey,semesterKey , query,tableViewers);
                }
                else  {
                    semesterKey = "second_semester";
                    schoolTable.getItems().removeAll(tableViewers);
                    intiDate(login, schoolNumber, schoolKey,semesterKey , query,tableViewers);
                }

            });

        });
        return schoolKey;
   }
    public void setBack(javafx.scene.image.ImageView back){
        back.setOnMouseClicked(event -> {
            back.setStyle("-fx-background-color: GREEN");
            try {
                TimeUnit.SECONDS.sleep((long) 0.2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            back.getScene().getWindow().hide();
            Parent root;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Schedule/Layouts/WelcomeLayout/SetOrView.fxml"));
                root = loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.initOwner(back.getScene().getWindow());
                stage.getIcons().add(new Image("assets/school.png"));
                stage.setTitle("Schedule 1.0");
                stage.show();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        });
    }
    public void SetLesson(String login , String schoolSelect2, String schoolSelect3 , String newLesson,
                          String query, Integer row , String day, String semesterKey){
        GetUsers get_user = new GetUsers();
        get_user.getUserName(login);
        String schid = get_user.getSchID();
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        Statement statement = null;
        row = row + 1;
        query = "UPDATE dasacucak." + schid + "_" + schoolSelect2 +"_" + schoolSelect3 +"_" + semesterKey + " SET "  + day + " ='" + newLesson + "' WHERE  id=" +
                row +";";
        System.out.println(row+1 + " " + day + " " + newLesson + "     " + query);

        try {
            statement = dataBaseHandler.getDbConnection().createStatement();
            Integer rs = statement.executeUpdate(query);
            System.out.println(rs);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
