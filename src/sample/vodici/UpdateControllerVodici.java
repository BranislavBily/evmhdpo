package sample.vodici;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.vozidla.VozidlaController;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Project: evmhd
 * @Author: Kristian Kičinka
 * @Date: 5. 4. 2019, Pi
 **/
public class UpdateControllerVodici {


    public TextField evcField;
    public TextField nameField;
    public TextField surnameField;
    public DatePicker prehliadkaField;
    public TextField evcVehicleField;
    public ComboBox stavField;
    private Stage stage;


    VodiciController vodiciController = new VodiciController();
    int fID;

    public void updateId(int cislo){
        fID=cislo;
    }
    public void updateStage(Stage st){
        stage=st;
    }

    @FXML
    public void obsahComboBox(){
        ObservableList<String> options =
                FXCollections.observableArrayList("Pracuje","Dovolenka","PN");
        stavField.setItems(options);

    }
    public void insert() throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();


        String sql="UPDATE `vodici` SET `evc_vodica`='"+Integer.parseInt(evcField.getText())+"',`name`='"+nameField.getText()+"',`surname`='"+surnameField.getText()+"',`prehliadka`='"+prehliadkaField.getValue()+"',`evc_vehicle`='"+evcVehicleField.getText()+"',`stav`='"+stavField.getValue().toString()+"' WHERE `id`='"+fID+"'";

        Statement statement=connection.createStatement();
        statement.executeUpdate(sql);
        stage.close();


    }


}
