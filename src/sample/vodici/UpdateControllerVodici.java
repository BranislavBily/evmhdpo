package sample.vodici;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
    public ComboBox evcVehicleField;
    public ComboBox stavField;
    private Stage stage;
    public Label invalidAlert;
    public Label invalidEVCVozidla;
    public Label invalidEVCVodica;
    public Label invalidMeno;
    public Label invalidStav;
    public Label invalidPrehliadka;
    public Label invalidPriezvisko;



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
        evcVehicleField.setItems(options);


    }

    public void insert() throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();

        try{
        String sql="UPDATE `vodici` SET `evc_vodica`='"+Integer.parseInt(evcField.getText())+"',`name`='"+nameField.getText()+"',`surname`='"+surnameField.getText()+"',`prehliadka`='"+prehliadkaField.getValue()+"',`evc_vehicle`='"+evcVehicleField.getValue().toString()+"',`stav`='"+stavField.getValue().toString()+"' WHERE `id`='"+fID+"'";

        Statement statement=connection.createStatement();
        statement.executeUpdate(sql);
        stage.close();
        invalidAlert.setText("Vyplňte všetky polia správne !");
        invalidEVCVodica.setText("");
        invalidEVCVozidla.setText("");
        invalidStav.setText("");
        invalidPrehliadka.setText("");
        invalidMeno.setText("");
        invalidPriezvisko.setText("");
        validation();
    }
        catch(Exception e){
        invalidAlert.setText("Vyplňte všetky polia správne !");
        invalidEVCVodica.setText("");
        invalidEVCVozidla.setText("");
        invalidStav.setText("");
        invalidPrehliadka.setText("");
        invalidMeno.setText("");
        invalidPriezvisko.setText("");
        validation();
    }




    }

    public void validation(){
        if(!evcField.getText().matches(".*\\d.*"))
            invalidEVCVodica.setText("!");
        if(nameField.getText().isEmpty())
            invalidMeno.setText("!");
        if(prehliadkaField.getValue() == null)
            invalidPrehliadka.setText("!");
        if(stavField.getValue() == null)
            invalidStav.setText("!");
        if(surnameField.getText().isEmpty())
            invalidPriezvisko.setText("!");
        if(evcVehicleField.getValue() == null)
            invalidEVCVozidla.setText("!");

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
            evcVehicleField.setValue(String.valueOf(resultSet.getInt("evc_vehicle")));
            stavField.setValue(resultSet.getString("stav"));
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
