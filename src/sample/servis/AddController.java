package sample.servis;


import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    public Label invalidAlert;
    public Label invalidEVC;
    public Label invalidZavada;
    public Label invalidOd;
    public Label invalidDo;
    public Label invalidStav;
    public Label invalidHala;

    @FXML
    


    public void insert() throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();

        try {
            String sql = "INSERT INTO servis (evc_vehicle,odstavene,zavada,hala,stav,oprava) VALUES ('" + Integer.parseInt(evcField.getValue().toString()) + "','" + odstaveneField.getValue().toString() + "','" + zavadaField.getText() + "','" + halaField.getValue().toString() + "','" + stavField.getValue().toString() + "','" + odovzdanieField.getValue().toString() + "')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            closeStage();
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
    @FXML
    public void obsahComboBox(){
        ObservableList<String> options =
                FXCollections.observableArrayList("Čaká sa ","Opravené","Opravuje sa");
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
