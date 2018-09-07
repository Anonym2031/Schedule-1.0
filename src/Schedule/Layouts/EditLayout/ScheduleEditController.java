package Schedule.Layouts.EditLayout;

import Schedule.DataBase.GetUsers;
import Schedule.DataBase.LessonsData;
import Schedule.Layouts.LoginLayout.LoginController;
import Schedule.Layouts.TableLayout.TableViewer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.ResourceBundle;

public class ScheduleEditController {
    private String login = LoginController.getLoginText();
    private static String schoolNumber = "1";
    private static String schoolKey = "a";
    private static String semesterKey = "first_semester";
    private static String query;
    private LessonsData lessonsData = new LessonsData();


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
    private TableView<TableViewer> schoolTable1;
    @FXML
    private TableColumn<TableViewer, Integer> idColumn1;

    @FXML
    private TableColumn<TableViewer, String> mondayColumn1 = new TableColumn<TableViewer, String>();

    @FXML
    private TableColumn<TableViewer, String> tuesdayColumn1 = new TableColumn<TableViewer, String>();

    @FXML
    private TableColumn<TableViewer, String> wednesdayColumn1 = new TableColumn<TableViewer, String>();

    @FXML
    private TableColumn<TableViewer, String> thursdayColumn1 = new TableColumn<TableViewer, String>();

    @FXML
    private TableColumn<TableViewer, String> fridayColumn1 = new TableColumn<TableViewer, String>();

    @FXML
    private Label schoolName;

    @FXML
    private ComboBox<String> semesterSelect;

    @FXML
    private javafx.scene.image.ImageView back;

    @FXML
    void initialize() {
        schoolTable1.setEditable(true);

        lessonsData.intiDate(login, schoolNumber, schoolKey, semesterKey, query, tableViewers);
        GetUsers GetUsers = new GetUsers();
        GetUsers.getUserName(login);
        directorName.setText("Բարի գալուստ հարգելի " + GetUsers.getFirstName() + " " + GetUsers.getLastName() + " , դասացուցակը դիտելու համար ընտրեք դասարանը");
        schoolName.setText("Դպրոց - " + GetUsers.getSchoolNumber());
        schoolName.isWrapText();
        idColumn1.setCellValueFactory(new PropertyValueFactory<TableViewer, Integer>("id"));
        mondayColumn1.setCellValueFactory(new PropertyValueFactory<TableViewer, String>("monday"));
        mondayColumn1.setCellFactory(TextFieldTableCell.<TableViewer>forTableColumn());
        mondayColumn1.setEditable(true);

        tuesdayColumn1.setCellValueFactory(new PropertyValueFactory<TableViewer, String>("tuesday"));
        tuesdayColumn1.setCellFactory(TextFieldTableCell.<TableViewer>forTableColumn());
        tuesdayColumn1.setEditable(true);

        wednesdayColumn1.setCellValueFactory(new PropertyValueFactory<TableViewer, String>("wednesday"));
        wednesdayColumn1.setCellFactory(TextFieldTableCell.<TableViewer>forTableColumn());
        wednesdayColumn1.setEditable(true);

        thursdayColumn1.setCellValueFactory(new PropertyValueFactory<TableViewer, String>("thursday"));
        thursdayColumn1.setCellFactory(TextFieldTableCell.<TableViewer>forTableColumn());
        thursdayColumn1.setEditable(true);

        fridayColumn1.setCellValueFactory(new PropertyValueFactory<TableViewer, String>("friday"));
        fridayColumn1.setCellFactory(TextFieldTableCell.<TableViewer>forTableColumn());
        fridayColumn1.setEditable(true);
        schoolTable1.setItems(tableViewers);

        lessonsData.setBack(back);

        //Add class number and key , and replace that to english language
        schoolSelect.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9");
        schoolSelect1.getItems().addAll("ա", "բ", "գ", "դ");
        semesterSelect.getItems().addAll("1-ին Կիսամյակ","2րդ Կիսամյակ");
        LessonsEdit(mondayColumn1);
        LessonsEdit(tuesdayColumn1);
        LessonsEdit(wednesdayColumn1);
        LessonsEdit(thursdayColumn1);
        LessonsEdit(fridayColumn1);

    }

    private void LessonsEdit(TableColumn<TableViewer, String> columns) {
        columns.setOnEditCommit((TableColumn.CellEditEvent<TableViewer, String> event) -> {
            TablePosition<TableViewer, String> pos = event.getTablePosition();
            String newLesson = event.getNewValue();
            Integer row = pos.getRow();
            Integer colNumber = pos.getColumn();
            String schoolNumber1 = lessonsData.SwitchClass(schoolSelect);
            String schoolKey1 = lessonsData.SwitchClassKey(schoolSelect1,semesterSelect, schoolTable1 , tableViewers, login, query);
            String semesterKey = lessonsData.SwitchSemesterKey(semesterSelect);

            TableViewer column = event.getTableView().getItems().get(row);
            switch (colNumber) {
                case 1:
                    column.setMonday(newLesson);
                    lessonsData.SetLesson(login , schoolNumber1 , schoolKey1 , newLesson , query , row,"monday",semesterKey);
                    System.out.println(schoolNumber1 + " " + schoolKey1 + " " + semesterKey);
                    break;
                case 2:
                    column.setTuesday(newLesson);
                    lessonsData.SetLesson(login , schoolNumber1 , schoolKey1 , newLesson , query , row,"tuesday",semesterKey);
                    break;
                case 3:
                    column.setWednesday(newLesson);
                    lessonsData.SetLesson(login , schoolNumber1 , schoolKey1 , newLesson , query , row,"wednesday",semesterKey);
                    break;
                case 4:
                    column.setThursday(newLesson);
                    lessonsData.SetLesson(login , schoolNumber1 , schoolKey1 , newLesson , query , row,"thursday",semesterKey);
                    break;
                case 5:
                    column.setFriday(newLesson);
                    lessonsData.SetLesson(login , schoolNumber1 , schoolKey1 , newLesson , query , row,"friday",semesterKey);
                    break;
            }


        });
    }
}