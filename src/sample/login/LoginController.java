package sample.login;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    @FXML
    private PasswordField passField;
    @FXML
    private TextField textField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textField.textProperty().addListener((observable, oldVal, newVal) -> {
            if (newVal.length() > 16) {
                textField.setText(oldVal);
            }
        });
        passField.textProperty().addListener((observable, oldVal, newVal) ->{
            if(newVal.length() > 16){
                passField.setText(oldVal);
            }
        });
    }
}

