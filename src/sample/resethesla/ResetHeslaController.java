package sample.resethesla;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ResetHeslaController  {

    @FXML
    private TextField overovaciKod;
    @FXML
    private PasswordField passField;
    @FXML
    private PasswordField passField1;
    @FXML
    private AnchorPane resetHeslaPane;
    @FXML
    private Button zmenitHeslo;

    @FXML
    private void onOveritKod() {
        System.out.println("Overujem kod");
        resetHeslaPane.resize(362, 500);
        passField.setVisible(true);
        passField1.setVisible(true);
        zmenitHeslo.setVisible(true);
    }

    @FXML
    private void onZmenitHeslo() {

    }


}
