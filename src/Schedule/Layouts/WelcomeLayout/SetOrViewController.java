package Schedule.Layouts.WelcomeLayout;

import Schedule.DataBase.GetUsers;
import Schedule.Layouts.LoginLayout.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class SetOrViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL         location;

    @FXML
    private Label directorName;

    @FXML
    private Button EditText;

    @FXML
    private Button ViewText;

    @FXML
    private Label schoolName;

    @FXML
    private ImageView back;


    @FXML
    void initialize() {
        GetUsers GetUsers = new GetUsers();
        String login = LoginController.getLoginText();
        GetUsers.getUserName(login);
        directorName.setText("Բարի գալուստ հարգելի " + GetUsers.getFirstName() + " " + GetUsers.getLastName() + "  , դասացուցակը փոփոխելու համար  ընտրեք  Փոփոխել տարբերակը");
        schoolName.setText("Դպրոց - " + GetUsers.getSchoolNumber());
        schoolName.isWrapText();

        //Schedule edit button event
        EditText.setOnMouseClicked(event -> {
            EditText.setStyle("-fx-background-color: GREEN");
            try {
                TimeUnit.SECONDS.sleep((long) 0.2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            EditText.getScene().getWindow().hide();
            Parent root;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Schedule/Layouts/EditLayout/ScheduleEdit.fxml"));
                root = loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.initOwner(EditText.getScene().getWindow());
                stage.getIcons().add(new Image("assets/school.png"));
                stage.setTitle("Schedule 1.0");
                stage.show();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        });
        EditText.setOnMouseMoved(event ->
                EditText.setStyle("-fx-background-color: green ; -fx-background-radius: 20 ;-fx-border-radius: 20 ; -fx-border-color: silver "));
        EditText.setOnMouseExited(event ->
                EditText.setStyle("-fx-background-color: orange  ; -fx-background-radius: 20 ;-fx-border-radius: 20 ; -fx-border-color: silver "));

        //Schedule view button event
        ViewText.setOnMouseClicked(event -> {
            ViewText.setStyle("-fx-background-color: GREEN");
            try {
                TimeUnit.SECONDS.sleep((long) 0.2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ViewText.getScene().getWindow().hide();
            Parent root;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Schedule/Layouts/ShowLayout/Schedule.fxml"));
                root = loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.initOwner(ViewText.getScene().getWindow());
                stage.getIcons().add(new Image("assets/school.png"));
                stage.setTitle("Schedule 1.0");
                stage.show();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        });
        ViewText.setOnMouseMoved(event ->
                ViewText.setStyle("-fx-background-color: green ; -fx-background-radius: 20 ;-fx-border-radius: 20 ; -fx-border-color: silver "));
        ViewText.setOnMouseExited(event ->
                ViewText.setStyle("-fx-background-color: orange  ; -fx-background-radius: 20 ;-fx-border-radius: 20 ; -fx-border-color: silver "));

        //back page button event
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Schedule/Layouts/LoginLayout/Login.fxml"));
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
}
