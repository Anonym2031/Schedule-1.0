package Schedule.Layouts.LoginLayout;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import Schedule.DataBase.Const;
import Schedule.DataBase.DataBaseHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class LoginController extends DataBaseHandler {
    private static String loginText;

    public static String getLoginText() {
        return loginText;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField password;

    @FXML
    private TextField user;


    @FXML
    private Button sign;

    @FXML
    void initialize() {
    //login button event
        sign.setOnMouseClicked(event -> {
                    sign.setStyle("-fx-background-color: GREEN");
                    try {
                        TimeUnit.SECONDS.sleep((long) 0.5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    loginText = user.getText().trim();
                    String passwordText = password.getText().trim();
                    if (!loginText.equals("") && !passwordText.equals("")) {
                        boolean rc = loginUser(user.getText().trim(), password.getText().trim());
                        if (rc) {

                            sign.getScene().getWindow().hide();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/Schedule/Layouts/WelcomeLayout/SetOrView.fxml"));
                            try {
                                loader.load();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Parent root = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setResizable(false);
                            stage.setMaximized(false);
                            stage.setScene(new Scene(root));
                            stage.getIcons().add(new Image("assets/school.png"));
                            stage.setTitle("Dasacucak 1.0");
                            stage.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Մուտք");
                            alert.setHeaderText(null);
                            alert.setContentText("Սխալ մուտքանուն կամ գաղտնաբառ");
                            alert.showAndWait();
                            try {
                                TimeUnit.SECONDS.sleep((long) 0.2);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            sign.setStyle("-fx-background-color: ORANGE");
                        }
                    }


                }

        );

    }

    //Connection to DataBase and cheking  user
    private boolean loginUser(String loginText, String passwordText) {
        boolean rc = false;
        Statement st = null;
        ResultSet rs = null;
        String query = ("SELECT " + Const.USERS_ID + " FROM " + Const.USER_TABLE + " WHERE " + Const.USERS_USERNAME + "='"
                + loginText + "' AND " + Const.USERS_PASSWORD + " ='" + passwordText + "';");
        try {
            st = getDbConnection().createStatement();
            rs = st.executeQuery(query);
            rc = rs.next();


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                if (getDbConnection() != null) {
                    getDbConnection().close();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return rc;
    }


}

