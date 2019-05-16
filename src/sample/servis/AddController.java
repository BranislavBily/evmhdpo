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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*Ak sa odstráni extends VozidlaController a potom zakomentuje riadok , ktorý ukladá dáta do tabulky tak sa spustí další stage bez erroru*/
public class AddController {


    public ComboBox evcField;
    public DatePicker odstaveneField;
    public TextField zavadaField;
    public ComboBox halaField;
    public DatePicker odovzdanieField;
    public Button pridatButton;
    public ComboBox stavField;

    @FXML
    


    public void insert() throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();

        String sql="INSERT INTO servis (evc_vehicle,odstavene,zavada,hala,stav,oprava) VALUES ('"+Integer.parseInt(evcField.getValue().toString())+"','"+odstaveneField.getValue().toString()+"','"+zavadaField.getText()+"','"+halaField.getValue().toString()+"','"+stavField.getValue().toString()+"','"+odovzdanieField.getValue().toString()+"')";
        Statement statement=connection.createStatement();
        statement.executeUpdate(sql);
        closeStage();


    }

    @FXML
    public void obsahComboBox(){
        ObservableList<String> options =
                FXCollections.observableArrayList("Čaká sa","Opravné","Opravuje sa");
        stavField.setItems(options);

    }

    public void obsahComboBoxEvc() throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();

        ObservableList<Integer> options = FXCollections.observableArrayList();

        String sql2="SELECT evc FROM vozidla";
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql2);
        int i=0;
        while (resultSet.next()){

            options.add(resultSet.getInt("evc"));
            i++;
        }
        evcField.setItems(options);


    }

    @FXML
    public void closeStage() {
        Stage stage = (Stage) pridatButton.getScene().getWindow();
        stage.close();
    }


    public void obsahComboBox2(MouseEvent mouseEvent) {
        ObservableList<String> options =
                FXCollections.observableArrayList("Ťažká udržba","Ľahká údržba");
        halaField.setItems(options);
    }


}
