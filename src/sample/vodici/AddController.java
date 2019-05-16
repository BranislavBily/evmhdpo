package sample.vodici;


import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
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
    public ComboBox evcVehicleField;
    public ComboBox stavField;
    public Label invalidAlert;
    public Label invalidEVCVozidla;
    public Label invalidEVCVodica;
    public Label invalidMeno;
    public Label invalidStav;
    public Label invalidPrehliadka;
    public Label invalidPriezvisko;


    public void insert() throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        try {
            String sql = "INSERT INTO `vodici` (evc_vodica,name,surname,evc_vehicle,stav,prehliadka ) VALUES ('" + Integer.parseInt(evcField.getText()) + "','" + nameField.getText() + "','" + surnameField.getText() + "','" + Integer.parseInt(evcVehicleField.getValue().toString()) + "','" + stavField.getValue().toString() + "','" + prehliadkaField.getValue().toString() + "')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            closeStage();
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

    @FXML
    public void obsahComboBox(){
        ObservableList<String> options =
                FXCollections.observableArrayList("Pracuje","Dovolenka","PN");
        stavField.setItems(options);

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

    @FXML
    public void closeStage() {
        Stage stage = (Stage) pridatButton.getScene().getWindow();
        stage.close();
    }




}
