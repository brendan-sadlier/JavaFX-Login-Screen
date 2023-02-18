package bsadlier.loginscreen.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {
    @FXML
    private Button exitButton;

    @FXML
    private Button loginButton;

    @FXML
    private Label messageLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    public void setLoginButton (ActionEvent event){
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            messageLabel.setText("Please Enter Details");
        } else {
            validateLogin();
        }
    }

    private void validateLogin() {
        Connection connection = DatabaseController.connectToDatabase();
        String sqlQuery = "SELECT count(1) FROM logins WHERE username = '" + usernameField.getText() + "' AND password = '" + passwordField.getText() + "'";

        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(sqlQuery);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    messageLabel.setText("Successfully Logged In");
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

}
