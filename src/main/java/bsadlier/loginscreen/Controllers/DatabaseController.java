package bsadlier.loginscreen.Controllers;

import java.sql.Connection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class DatabaseController {

    public static Connection connectToDatabase () {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(getLoginCredentials()[0], getLoginCredentials()[1], getLoginCredentials()[2]);
        } catch (Exception e) {e.printStackTrace();}

        return null;

    }

    private static String[] getLoginCredentials() {
        List<String> temp = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\BrendanSadlier\\Development\\JavaFX\\Login Screen\\src\\main\\resources\\bsadlier\\loginscreen\\credentials.txt"));
            String inputLine = bufferedReader.readLine();

            while (inputLine != null) {
                temp.add(inputLine);
                inputLine = bufferedReader.readLine();
            }

            bufferedReader.close();
        } catch (Exception e) {e.printStackTrace();}

        String[] credentials = temp.toArray(new String[0]);

        return credentials;
    }
}
