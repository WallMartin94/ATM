package Controller;

import Models.DBMethods;
import Models.Database;
import Models.User;
import Models.UserSingleton;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class logInController implements Initializable {
    private PreparedStatement preparedStatement = null;
    private Connection connection = null;
    private ResultSet resultSet = null;
    private User currentUser;

    @FXML
    private Button SignInButton;

    @FXML
    private Label lbError;

    @FXML
    private TextField userIdTextField, passwordTextField;


    @FXML
    void SignInButton(ActionEvent event) {
        loginMethod(event);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void loginMethod(ActionEvent event) {

        String SSN = userIdTextField.getText();
        String password = passwordTextField.getText();

        try {
            if (!userIdTextField.getText().isEmpty() && !passwordTextField.getText().isEmpty()) {
                Database database = Database.getInstance();
                if (database.isConnected()) {
                    DBMethods dbMethods = new DBMethods();
                    currentUser = dbMethods.getUser(SSN, password);

                    if (currentUser == null) {
                        lbError.setText("Please Enter a valid User name and Password");
                        database.disconnect();
                    } else {
                        //    alert(AlertType.CONFIRMATION, "Login Successful", null, "Successful"); not needed here
                        UserSingleton.getInstance().setUser(currentUser);
                        viewWindow(event);
                    }
                }

            } else {
                alert(AlertType.WARNING, "Please fill all fields", null, "Error");
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }


    private void alert(AlertType alertType, String message, String header, String title) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.setHeaderText(header);
        alert.showAndWait();
    }

    void viewWindow(Event event) {
        try {
            Parent parent1 = FXMLLoader.load(getClass().getResource("/View/logInUI.fxml"));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(parent1));
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
