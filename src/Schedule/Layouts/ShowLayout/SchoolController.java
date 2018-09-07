package Schedule.Layouts.ShowLayout;

import Schedule.DataBase.GetUsers;
import Schedule.DataBase.LessonsData;
import Schedule.Layouts.LoginLayout.LoginController;
import Schedule.Layouts.TableLayout.TableViewer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class SchoolController {
    private String login = LoginController.getLoginText();
    private static String schoolNumber = "1";
    private static String schoolKey = "a";
    private static String query = null;



    private ObservableList<TableViewer> tableViewers = FXCollections.observableArrayList();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> schoolSelect;

    @FXML
    private ComboBox<String> schoolSelect1;

    @FXML
    private Label directorName;

    @FXML
    private TableView<TableViewer> schoolTable;
    @FXML
    private TableColumn<TableViewer, Integer> idColumn;

    @FXML
    private TableColumn<TableViewer, String> mondayColumn;

    @FXML
    private TableColumn<TableViewer, String> tuesdayColumn;

    @FXML
    private TableColumn<TableViewer, String> wednesdayColumn;

    @FXML
    private TableColumn<TableViewer, String> thursdayColumn;

    @FXML
    private TableColumn<TableViewer, String> fridayColumn;

    @FXML
    private Label schoolName;

    @FXML
    private ComboBox<String> semesterSelect;

    @FXML
    private javafx.scene.image.ImageView back;

    @FXML
    void initialize() {
        String semesterKey = "first_semester";
        LessonsData lessonsData = new LessonsData();
        lessonsData.intiDate(login , schoolNumber , schoolKey  , semesterKey , query,tableViewers);
        GetUsers GetUsers = new GetUsers();
        GetUsers.getUserName(login);
        directorName.setText("Բարի գալուստ հարգելի " + GetUsers.getFirstName() + " " + GetUsers.getLastName() + " , դասացուցակը դիտելու համար ընտրեք դասարանը");
        schoolName.setText("Դպրոց - " + GetUsers.getSchoolNumber());
        schoolName.isWrapText();
        schoolTable.setEditable(false);
        idColumn.setCellValueFactory(new PropertyValueFactory<TableViewer, Integer>("id"));
        mondayColumn.setCellValueFactory(new PropertyValueFactory<TableViewer, String>("monday"));
        tuesdayColumn.setCellValueFactory(new PropertyValueFactory<TableViewer, String>("tuesday"));
        wednesdayColumn.setCellValueFactory(new PropertyValueFactory<TableViewer, String>("wednesday"));
        thursdayColumn.setCellValueFactory(new PropertyValueFactory<TableViewer, String>("thursday"));
        fridayColumn.setCellValueFactory(new PropertyValueFactory<TableViewer, String>("friday"));
        schoolTable.setItems(tableViewers);
        //Table columns select


        schoolTable.setItems(tableViewers);

        //Add class number and key , and replace that to english language
        schoolSelect.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9");
        schoolSelect1.getItems().addAll("ա", "բ", "գ", "դ");
        semesterSelect.getItems().addAll("1-ին Կիսամյակ","2րդ Կիսամյակ");
        lessonsData.SwitchClass(schoolSelect);
        lessonsData.SwitchClassKey(schoolSelect1,semesterSelect ,schoolTable,tableViewers,login,query);
        lessonsData.setBack(back);
    }






    private static String getSchoolNumber() {
        return schoolNumber;
    }

    private static String getSchoolKey() {
        return schoolKey;
    }
}