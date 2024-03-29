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
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Main;
import sample.vodici.VodiciController;

import java.io.IOException;
import java.sql.*;

/*Ak sa odstráni extends VozidlaController a potom zakomentuje riadok , ktorý ukladá dáta do tabulky tak sa spustí další stage bez erroru*/
public class AddController {

    public TextField evcField;
    public TextField spzField;
    public ComboBox vodicField;
    public DatePicker stkField;
    public ComboBox typField;
    public ComboBox stavField;
    public TextField reklamaField;
    public Label invalidAlert;
    public Label invalidEVC;
    public Label invalidSPZ;
    public Label invalidSTK;
    public Label invalidStav;
    public Label invalidReklama;
    public Label invalidTyp;
    public Label invalidVodic;

    @FXML
    public Button pridatButton;



    public void insert() throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        try{
        String sql="INSERT INTO vozidla (evc, spz, vodic,stk, typVozidla, stavVozidla, reklama ) VALUES ('"+Integer.parseInt(evcField.getText())+"','"+spzField.getText()+"','"+vodicField.getValue().toString()+"','"+stkField.getValue()+"','"+typField.getValue().toString()+"','"+stavField.getValue().toString()+"','"+reklamaField.getText()+"')";
        Statement statement=connection.createStatement();
        statement.executeUpdate(sql);
        closeStage();}
        catch(Exception e){
            invalidAlert.setText("Vyplňte všetky polia správne !");
            invalidEVC.setText("");
            invalidSPZ.setText("");
            invalidSTK.setText("");
            invalidStav.setText("");
            invalidTyp.setText("");
            invalidVodic.setText("");
            validation();
        }


    }
    public void validation(){
        if(!evcField.getText().matches(".*\\d.*"))
            invalidEVC.setText("!");
        if(spzField.getText().isEmpty())
            invalidSPZ.setText("!");
        if(vodicField.getValue() == null)
            invalidVodic.setText("!");
        if(stavField.getValue() == null)
            invalidStav.setText("!");
        if(stkField.getValue()== null)
            invalidSTK.setText("!");
        if(typField.getValue() == null)
            invalidTyp.setText("!");



    }

    @FXML
    public void obsahComboBox(){
        ObservableList<String> options =
                FXCollections.observableArrayList("Pojazdné","Nepojazdné","V servise");
        stavField.setItems(options);

    }
    @FXML
    public void obsahComboBoxTyp(){
        ObservableList<String> options =
                FXCollections.observableArrayList("Karosa B741","Karosa B932E","Karosa B941",
                        "Karosa B941E","Karosa B961","Karosa B961E","Renault Karosa Citybus 12M",
                        "Irisbus Citybus 12M","Irisbus Citybus 18M","Irisbus Citelis 12M",
                        "Irisbus Citelis 18M", "SOR NB 18","Solaris Urbino 12M/II",
                        "Solaris Urbino 12M/III","Solaris Urbino 18M/III","IVECO First FCLLI",
                        "SOR BN 10.5","Škoda 14Tr/M","Škoda 15Tr/M","Škoda 24 Tr","Škoda 25 Tr","Škoda 30 Tr", "Škoda 31 Tr");
        typField.setItems(options);

    }

    public void obsahComboBoxVodic() throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();

        ObservableList<String> options = FXCollections.observableArrayList();

        String sql2="SELECT CONCAT(name, \" \", surname)AS name FROM vodici";
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql2);
        int i=0;
        while (resultSet.next()){

            options.add(resultSet.getString("name"));
            i++;
        }
        vodicField.setItems(options);


    }

    @FXML
    public void closeStage() {
        Stage stage = (Stage) pridatButton.getScene().getWindow();
        stage.close();
    }




}
