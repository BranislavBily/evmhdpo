package sample.vozidla;


import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.vodici.VodiciController;

import java.io.IOException;
import java.sql.*;

/*Ak sa odstráni extends VozidlaController a potom zakomentuje riadok , ktorý ukladá dáta do tabulky tak sa spustí další stage bez erroru*/
public class AddController {

    public TextField evcField;
    public TextField spzField;
    public TextField vodicField;
    public DatePicker stkField;
    public TextField typField;
    public ComboBox stavField;
    public TextField reklamaField;

    @FXML
    public Button pridatButton;



    public void insert() throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();

        String sql="INSERT INTO VOZIDLA (evc, spz, vodic,stk, typVozidla, stavVozidla, reklama ) VALUES ('"+Integer.parseInt(evcField.getText())+"','"+spzField.getText()+"','"+vodicField.getText()+"','"+stkField.getValue()+"','"+typField.getText()+"','"+stavField.getValue().toString()+"','"+reklamaField.getText()+"')";
        Statement statement=connection.createStatement();
        statement.executeUpdate(sql);
        closeStage();


    }

    @FXML
    public void obsahComboBox(){
        ObservableList<String> options =
                FXCollections.observableArrayList("Pojazdné","Nepojazdné","V servise");
        stavField.setItems(options);

    }

    @FXML
    public void closeStage() {
        Stage stage = (Stage) pridatButton.getScene().getWindow();
        stage.close();
    }




}
