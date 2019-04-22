package sample.servis;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.vodici.VodiciController;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Project: evmhd
 * @Author: Kristian Kičinka
 * @Date: 5. 4. 2019, Pi
 **/
public class UpdateControllerServis {


    public TextField evcField;
    public DatePicker odstaveneField;
    public TextField zavadaField;
    public ComboBox halaField;
    public DatePicker odovzdanieField;
    public Button pridatButton;
    public ComboBox stavField;
    private Stage stage;


    ServisController servisController = new ServisController();
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
                FXCollections.observableArrayList("Čaká sa","Opravené","Opravuje sa");
        stavField.setItems(options);

    }
    public void insert() throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();


        String sql="UPDATE `SERVIS` SET `evc_vehicle`='"+Integer.parseInt(evcField.getText())+"',`odstavene`='"+odstaveneField.getValue().toString()+"',`zavada`='"+zavadaField.getText()+"',`hala`='"+halaField.getValue().toString()+"',`stav`='"+stavField.getValue().toString()+"',`oprava`='"+odovzdanieField.getValue().toString()+"' WHERE `id`='"+fID+"'";

        Statement statement=connection.createStatement();
        statement.executeUpdate(sql);
        stage.close();


    }


    public void obsahComboBox2(MouseEvent mouseEvent) {

        ObservableList<String> options =
                FXCollections.observableArrayList("Ľahká údržba","Ťažká údržba");
        halaField.setItems(options);
    }
}
