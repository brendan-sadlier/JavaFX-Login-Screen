module bsadlier.loginscreen {
    requires javafx.controls;
    requires javafx.fxml;


    opens bsadlier.loginscreen to javafx.fxml;
    exports bsadlier.loginscreen;
}