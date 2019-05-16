package sample.servis;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.vodici.VodiciController;

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
public class UpdateControllerServis implements Initializable {


    public ComboBox evcField;
    public DatePicker odstaveneField;
    public TextField zavadaField;
    public ComboBox halaField;
    public DatePicker odovzdanieField;
    public Button pridatButton;
    public ComboBox stavField;
    private Stage stage;
    public Label invalidAlert;
    public Label invalidEVC;
    public Label invalidZavada;
    public Label invalidOd;
    public Label invalidDo;
    public Label invalidStav;
    public Label invalidHala;



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
                FXCollections.observableArrayList("Čaká sa","Opravené","Opravuje sa");
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
    public void insert() throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();

        try{
        String sql="UPDATE `servis` SET `evc_vehicle`='"+Integer.parseInt(evcField.getValue().toString())+"',`odstavene`='"+odstaveneField.getValue().toString()+"',`zavada`='"+zavadaField.getText()+"',`hala`='"+halaField.getValue().toString()+"',`stav`='"+stavField.getValue().toString()+"',`oprava`='"+odovzdanieField.getValue().toString()+"' WHERE `id`='"+fID+"'";

        Statement statement=connection.createStatement();
        statement.executeUpdate(sql);
        stage.close();
        invalidAlert.setText("Vyplňte všetky polia správne !");
        invalidEVC.setText("");
        invalidStav.setText("");
        invalidDo.setText("");
        invalidHala.setText("");
        invalidOd.setText("");
        invalidZavada.setText("");
        validation();
    }
        catch(Exception e){
        invalidAlert.setText("Vyplňte všetky polia správne !");
        invalidEVC.setText("");
        invalidStav.setText("");
        invalidDo.setText("");
        invalidHala.setText("");
        invalidOd.setText("");
        invalidZavada.setText("");
        validation();
    }



}

    public void validation(){
        if(evcField.getValue() == null)
            invalidEVC.setText("!");
        if(zavadaField.getText().isEmpty())
            invalidZavada.setText("!");
        if(halaField.getValue() == null)
            invalidHala.setText("!");
        if(stavField.getValue() == null)
            invalidStav.setText("!");
        if(odovzdanieField.getValue()== null)
            invalidDo.setText("!");
        if(odstaveneField.getValue() == null)
            invalidOd.setText("!");



    }


    public void obsahComboBox2(MouseEvent mouseEvent) {

        ObservableList<String> options =
                FXCollections.observableArrayList("Ľahká údržba","Ťažká údržba");
        halaField.setItems(options);
    }

    public void updateParameters() throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();

        String sql="SELECT * FROM `servis` WHERE `id`='"+fID+"'";

        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql);

        while (resultSet.next()){

            evcField.setValue(String.valueOf(resultSet.getInt("evc_vehicle")));
            odstaveneField.setValue(LocalDate.parse((resultSet.getDate("odstavene")).toString()));
            zavadaField.setText(resultSet.getString("zavada"));
            halaField.setValue(resultSet.getString("hala"));
            odovzdanieField.setValue(LocalDate.parse((resultSet.getDate("oprava")).toString()));
            stavField.setValue(resultSet.getString("stav"));
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
