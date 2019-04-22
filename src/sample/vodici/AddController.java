package sample.vodici;


import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/*Ak sa odstráni extends VozidlaController a potom zakomentuje riadok , ktorý ukladá dáta do tabulky tak sa spustí další stage bez erroru*/
public class AddController {



    @FXML
    public Button pridatButton;
    public TextField nameField;
    public TextField evcField;
    public TextField surnameField;
    public DatePicker prehliadkaField;
    public TextField evcVehicleField;
    public ComboBox stavField;


    public void insert() throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();

        String sql="INSERT INTO VODICI (evc_vodica,name,surname,evc_vehicle,stav,prehliadka ) VALUES ('"+Integer.parseInt(evcField.getText())+"','"+nameField.getText()+"','"+surnameField.getText()+"','"+Integer.parseInt(evcVehicleField.getText())+"','"+stavField.getValue().toString()+"','"+prehliadkaField.getValue().toString()+"')";
        Statement statement=connection.createStatement();
        statement.executeUpdate(sql);
        closeStage();


    }

    @FXML
    public void obsahComboBox(){
        ObservableList<String> options =
                FXCollections.observableArrayList("Pracuje","Dovolenka","PN");
        stavField.setItems(options);

    }

    @FXML
    public void closeStage() {
        Stage stage = (Stage) pridatButton.getScene().getWindow();
        stage.close();
    }




}
