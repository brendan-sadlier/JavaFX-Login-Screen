module bsadlier.loginscreen {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires java.sql;


    opens bsadlier.loginscreen to javafx.fxml;
    exports bsadlier.loginscreen;
}