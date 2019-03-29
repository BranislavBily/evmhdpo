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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    @FXML
    private PasswordField passField;
    @FXML
    private TextField textField;
    @FXML
    private Label alertInfo;

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


    @FXML
    private void login() {
        String name = textField.getText().toLowerCase();
        String pass = passField.getText();

        System.out.println(name);
        System.out.println(pass);
        if(name.contains("admin")&&pass.contains("password")){
        try {
            Stage stage = (Stage) textField.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../vozidla/Vozidla.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            stage.setTitle("Vozidlá");
            stage.setScene(scene);
            root.getStylesheets().addAll(this.getClass().getResource("../style.css").toExternalForm());
            stage.setMaximized(true);
            stage.setMinHeight(1080);
            stage.setMinWidth(1920);
            //stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    else
            System.out.println("Používateľ zadal nesprávne údaje!");
            alertInfo.setText("Zadali ste nesprávne údaje !");

    }
    }


