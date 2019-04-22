package sample.vozidla;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Project: evmhd
 * @Author: Kristian Kičinka
 * @Date: 5. 4. 2019, Pi
 **/
public class UpdateController {


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


}
