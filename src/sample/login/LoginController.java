package sample.login;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    @FXML
    private PasswordField passField;
    @FXML
    private TextField textField;
    @FXML
    private Label message;


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
        /*Funkcia sluziaca na kontrolu zadaných údajov a vytvorenie nevej Stage*/
    public void login () throws IOException {
        if (passField.getText().equals("Admin") && textField.getText().equals("Admin")){

            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../vozidla/Vozidla.fxml"));
            root.setId("pane");
            primaryStage.setTitle("Evidencia vozidiel MHD");
            primaryStage.setScene(new Scene(root));
           // root.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
            primaryStage.setMaximized(true);
            primaryStage.setMinHeight(1080);
            primaryStage.setMinWidth(1920);
            primaryStage.setFullScreen(true);
            primaryStage.show();

        }else {
            message.setText("Prihlasenie Zlyhalo");
        }

    }
}

