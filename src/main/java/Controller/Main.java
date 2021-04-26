package Controller;


import Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Main implements Initializable {
    private final DBMethods dbMethods = new DBMethods();
    private final User currentUser = UserSingleton.getInstance().getUser();
    private final Account account = dbMethods.getAccount(currentUser.getAccountNumber());


    @FXML
    private TextField accountNumberTextField;

    @FXML
    private TextField accountBalanceTextField;

    @FXML
    void depositButton(ActionEvent event) {
        Transaction transaction = new Transaction(account);
        transaction.deposit(50);
        dbMethods.addTransaction(transaction);
        accountBalanceTextField.setText(String.valueOf(account.getAccountBalance()));
    }

    @FXML
    void withdrawButton(ActionEvent event) {
        Transaction transaction = new Transaction(account);
        transaction.withdraw(50);
        dbMethods.addTransaction(transaction);
        accountBalanceTextField.setText(String.valueOf(account.getAccountBalance()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accountBalanceTextField.setText(String.valueOf(account.getAccountBalance()));
        accountNumberTextField.setText(String.valueOf(account.getAccountNumber()));

    }


}
