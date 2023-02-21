package bsadlier.loginscreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {

    @FXML
    private Button exitButton;

    @FXML
    private JFXButton loginButton;


    @FXML
    private Label messageLabel;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXTextField usernameField;

    public void setLoginButton (ActionEvent event){
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            messageLabel.setText("Please Enter Details");
        } else {
            validateLogin();
        }
    }

    private void validateLogin() {
        Connection connection = DatabaseController.connectToDatabase();
        String sqlQuery = "SELECT count(1) FROM users WHERE username = '" + usernameField.getText() + "' AND password = '" + passwordField.getText() + "'";

        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(sqlQuery);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    showDashboard();
                } else {
                    messageLabel.setText("Incorrect Username or Password");
                }
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    @FXML
    private void close() {
        System.exit(0);
    }

    private void showDashboard () throws IOException {
        loginButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("dashboard-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
    }

}
