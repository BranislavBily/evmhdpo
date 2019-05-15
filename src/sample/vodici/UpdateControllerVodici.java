package sample.vodici;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.vozidla.VozidlaController;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * @Project: evmhd
 * @Author: Kristian Kičinka
 * @Date: 5. 4. 2019, Pi
 **/
public class UpdateControllerVodici implements Initializable {


    public TextField evcField;
    public TextField nameField;
    public TextField surnameField;
    public DatePicker prehliadkaField;
    public TextField evcVehicleField;
    public ComboBox stavField;
    private Stage stage;



    int fID;

    public void updateId(int cislo){
        fID=cislo;

        try {
            updateParameters();
        } catch (SQLException e) {
            e.printStackTrace();
        }


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

    public void updateParameters() throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();

        String sql="SELECT * FROM `vodici` WHERE `id`='"+fID+"'";

        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql);

        while (resultSet.next()){

            evcField.setText(String.valueOf(resultSet.getInt("evc_vodica")));
            nameField.setText(resultSet.getString("name"));
            surnameField.setText(resultSet.getString("surname"));
            prehliadkaField.setValue(LocalDate.parse((resultSet.getDate("prehliadka")).toString()));
            evcVehicleField.setText(String.valueOf(resultSet.getInt("evc_vehicle")));
            stavField.setValue(resultSet.getString("stav"));
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
