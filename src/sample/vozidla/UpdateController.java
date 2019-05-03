package sample.vozidla;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

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
public class UpdateController implements Initializable {


    public TextField evcField;
    public TextField spzField;
    public TextField vodicField;
    public DatePicker stkField;
    public TextField typField;
    public ComboBox stavField;
    public TextField reklamaField;

    private Stage stage;
    VozidlaController vozidlaController = new VozidlaController();
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
                FXCollections.observableArrayList("Pojazdné","Nepojazdné","V servise");
        stavField.setItems(options);

    }
    public void insert() throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();


        String sql="UPDATE `vozidla` SET `evc`='"+Integer.parseInt(evcField.getText())+"',`spz`='"+spzField.getText()+"',`vodic`='"+vodicField.getText()+"',`stk`='"+stkField.getValue()+"',`typVozidla`='"+typField.getText()+"',`stavVozidla`='"+stavField.getValue().toString()+"',`reklama`='"+reklamaField.getText()+"' WHERE `id`='"+fID+"'";

        Statement statement=connection.createStatement();
        statement.executeUpdate(sql);
        stage.close();


    }



    public void updateParameters() throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();

        String sql="SELECT * FROM `vozidla` WHERE `id`='"+fID+"'";

        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql);

        while (resultSet.next()){

            evcField.setText(String.valueOf(resultSet.getInt("evc")));
            spzField.setText(resultSet.getString("spz"));
            vodicField.setText(resultSet.getString("vodic"));
            stkField.setValue(LocalDate.parse((resultSet.getDate("stk")).toString()));
            typField.setText(resultSet.getString("typVozidla"));
            stavField.setValue(resultSet.getString("stavVozidla"));
            reklamaField.setText(resultSet.getString("reklama"));
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
