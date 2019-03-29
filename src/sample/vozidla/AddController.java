package sample.vozidla;


import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*Ak sa odstráni extends VozidlaController a potom zakomentuje riadok , ktorý ukladá dáta do tabulky tak sa spustí další stage bez erroru*/
public class AddController extends VozidlaController {


    public TextField evcField;
    public TextField spzField;
    public TextField vodicField;
    public DatePicker stkField;
    public TextField typField;
    public TextField stavField;
    public TextField reklamaField;



    public void button() throws SQLException {
        ObservableList<TableModel> observableList= FXCollections.observableArrayList();
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();


        String sql="INSERT INTO VOZIDLA (evc, spz, vodic, stk, typVozidla, stavVozidla, reklama ) VALUES ('"+evcField.getText()+"','"+spzField.getText()+"','"+vodicField.getText()+"','"+stkField.getDayCellFactory()+"','"+typField.getText()+"','"+stavField.getText()+"','"+reklamaField.getText()+"')";
        Statement statement=connection.createStatement();
        statement.executeUpdate(sql);


        String sql2="SELECT * FROM VOZIDLA;";
        ResultSet resultSet=statement.executeQuery(sql2);
        while (resultSet.next()){
            observableList.add(new TableModel(resultSet.getString("evc"),resultSet.getString("spz"),resultSet.getString("vodic"),resultSet.getString("stk"),resultSet.getString("typVozidla"),resultSet.getString("stavVozidla"),resultSet.getString("reklama")));
        }

            tableView.setItems(observableList);



    }
}
