package Schedule.Layouts.WelcomeLayout;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import Schedule.DataBase.LessonsData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LogOutController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button noButton;

    @FXML
    private Button yesButton;

    @FXML
    void initialize() {
        LessonsData lessonsData = new LessonsData();

        yesButton.setOnMouseMoved(event ->
                yesButton.setStyle("-fx-background-color: green ; -fx-background-radius: 20 ;-fx-border-radius: 20 ; -fx-border-color: silver "));
        yesButton.setOnMouseExited(event ->
                yesButton.setStyle("-fx-background-color: orange  ; -fx-background-radius: 20 ;-fx-border-radius: 20 ; -fx-border-color: silver "));

        noButton.setOnMouseMoved(event ->
                noButton.setStyle("-fx-background-color: green ; -fx-background-radius: 20 ;-fx-border-radius: 20 ; -fx-border-color: silver "));
        noButton.setOnMouseExited(event ->
                noButton.setStyle("-fx-background-color: orange  ; -fx-background-radius: 20 ;-fx-border-radius: 20 ; -fx-border-color: silver "));


       yesButton.setOnMouseClicked(event -> {
           lessonsData.Music();
           try {
               TimeUnit.SECONDS.sleep((long) 0.7);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           yesButton.getScene().getWindow().hide();
           Parent root;
           try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/Schedule/Layouts/LoginLayout/Login.fxml"));

               root = loader.load();
               Stage stage = new Stage();
               Scene scene = new Scene(root);
               stage.setScene(scene);
               stage.initOwner(yesButton.getScene().getWindow());
               stage.getIcons().add(new Image("assets/school.png"));
               stage.setTitle("Schedule 1.0");
               stage.show();
           } catch (Exception exc) {
               exc.printStackTrace();
           }
       });


       noButton.setOnMouseClicked(event -> {
           lessonsData.Music();
           try {
               TimeUnit.SECONDS.sleep((long) 0.7);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           noButton.getScene().getWindow().hide();
           lessonsData.lastLayout1(noButton);

       });

    }
}
