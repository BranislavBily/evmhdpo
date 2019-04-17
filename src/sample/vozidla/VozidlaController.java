package sample.vozidla;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VozidlaController implements Initializable {


    /*Add controller*/

    public TextField evcField;
    public TextField spzField;
    public TextField vodicField;
    public DatePicker stkField;
    public TextField typField;
    public TextField stavField;
    public TextField reklamaField;

    /*Vozidla Controller*/

    public TableView<TableModel> tableView;
    public TableColumn<TableModel,String> evcColumn;
    public TableColumn<TableModel,String> vodiciColumn;
    public TableColumn<TableModel, String> stkColumn;
    public TableColumn<TableModel,String> typColumn;
    public TableColumn<TableModel,String> stavColumn;
    public TableColumn<TableModel,String> reklamaColumn;
    public TableColumn<TableModel,String> spzColumn;
    public TableColumn<TableModel,Button> updateColumn;
    public TableColumn<TableModel,Button> deleteColumn;


    Button[] delete;
    Button[] update;
    int updateID;
    Stage stageUpdate;
    int updateIdecko;



    @FXML
    private javafx.scene.control.Label VozidlaButton;
    @FXML
    private javafx.scene.control.Label ServisButton;
    @FXML
    private javafx.scene.control.Label VodiciButton;


    public VozidlaController() {
    }



    @FXML
    public void vozidlaClick() {
        try {
            Stage stageVozidla = (Stage) VozidlaButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(VozidlaController.class.getResource("../vozidla/Vozidla.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            stageVozidla.setTitle("Vozidlá");
            stageVozidla.setScene(scene);
            stageVozidla.setMaximized(true);
            stageVozidla.setMinHeight(1080);
            stageVozidla.setMinWidth(1920);
            //stageVozidla.setFullScreen(true);
            stageVozidla.show();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void vodiciClick() {
        try {
            Stage stageVodici = (Stage) VodiciButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(VozidlaController.class.getResource("../vodici/Vodici.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            stageVodici.setTitle("Vodiči");
            stageVodici.setScene(scene);
            stageVodici.setMaximized(true);
            stageVodici.setMinHeight(1080);
            stageVodici.setMinWidth(1920);
            //stageVodici.setFullScreen(true);
            stageVodici.show();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void servisClick() {
        try {
            Stage stageServis = (Stage) ServisButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(VozidlaController.class.getResource("../servis/Servis.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            stageServis.setTitle("Servis");
            stageServis.setScene(scene);
            //stageServis.setFullScreen(true);
            stageServis.setMaximized(true);
            stageServis.setMinHeight(1080);
            stageServis.setMinWidth(1920);
            stageServis.show();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void logOut(){
        try {
            Stage stageLogin = (Stage) ServisButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(VozidlaController.class.getResource("../login/Login.fxml"));
            Parent root = loader.load();
            root.getStylesheets().addAll(this.getClass().getResource("../style.css").toExternalForm());
            Scene scene = new Scene(root);

            stageLogin.setTitle("Login");
            stageLogin.setScene(scene);
            //stageLogin.setFullScreen(true);
            stageLogin.setMaximized(true);
            stageLogin.setMinHeight(1080);
            stageLogin.setMinWidth(1920);
            stageLogin.show();

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
    /*Pridanie Add buttna*/


    public void addButton(){
        try {
            Stage stageLogin = new Stage();
            FXMLLoader loader = new FXMLLoader(VozidlaController.class.getResource("../vozidla/Add.fxml"));
            Parent root = loader.load();
            root.getStylesheets().addAll(this.getClass().getResource("../style.css").toExternalForm());
            Scene scene = new Scene(root);

            stageLogin.setTitle("Login");
            stageLogin.setScene(scene);
            stageLogin.setMinHeight(720);
            stageLogin.setMinWidth(480);
            stageLogin.show();

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }


    /*Funkcia ktorá zaobstaráva vymazanie údaju*/

    public void delete() throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        Statement statement=connection.createStatement();



        String sqlID="SELECT id FROM VOZIDLA;";
        ResultSet results=statement.executeQuery(sqlID);
        ArrayList<Integer> ids = new ArrayList<>();
        while(results.next()){
            ids.add(results.getInt("id"));
        }
        delete = new Button[ids.size()];

        for (int i=0;i<ids.size();i++){
            delete[i] = new Button();
            int finalId = ids.get(i);
            delete[i].setOnAction(e -> {
                String delete="DELETE FROM VOZIDLA WHERE id="+ finalId;
                System.out.println("finalId:" + finalId);
                try {
                    statement.executeUpdate(delete);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    System.out.println("Vymazanie údajov sa nepodarilo");
                }
            });
        }


    }

    public void updateWindow() throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        Statement statement=connection.createStatement();

        String sqlID="SELECT id FROM VOZIDLA;";
        ResultSet results=statement.executeQuery(sqlID);
        ArrayList<Integer> ids = new ArrayList<>();
        while(results.next()){
            ids.add(results.getInt("id"));
        }
        update = new Button[ids.size()];

        for (int i=0;i<ids.size();i++){
            update[i] = new Button();
            int finalI = i;
            update[i].setOnAction(p -> {
                updateID = ids.get(finalI);
                stageUpdate = new Stage();
                FXMLLoader loader = new FXMLLoader(VozidlaController.class.getResource("../vozidla/update.fxml"));
                Parent root = null;
                try {
                    root = (Parent) loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                UpdateController updateController = loader.getController();
                updateController.updateId(updateID);


                root.getStylesheets().addAll(this.getClass().getResource("../style.css").toExternalForm());
                Scene scene = new Scene(root);


                updateController.updateStage(stageUpdate);
                stageUpdate.setTitle("update");
                stageUpdate.setScene(scene);
                stageUpdate.setMinHeight(720);
                stageUpdate.setMinWidth(480);
                stageUpdate.show();
            }); {

        }
    }

    }








    ObservableList<TableModel> observableList= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection=connectionClass.getConnection();
            Statement statement=connection.createStatement();

            String sql2="SELECT * FROM VOZIDLA;";
            ResultSet resultSet=statement.executeQuery(sql2);



           delete();
           updateWindow();

                int i=0;
            while (resultSet.next()){

                observableList.add(new TableModel(resultSet.getInt("evc"),resultSet.getString("spz"),resultSet.getString("vodic"),resultSet.getDate("stk"),resultSet.getString("typVozidla"),resultSet.getString("stavVozidla"),resultSet.getString("reklama"),delete[i],update[i]));
                i++;
            }

            resultSet.close();
            connection.close();
            statement.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        evcColumn.setCellValueFactory(new PropertyValueFactory<>("tableEvc"));
        spzColumn.setCellValueFactory(new PropertyValueFactory<>("tableSpz"));
        vodiciColumn.setCellValueFactory(new PropertyValueFactory<>("tableVodici"));
        stkColumn.setCellValueFactory(new PropertyValueFactory<>("tableStk"));
        typColumn.setCellValueFactory(new PropertyValueFactory<>("tableTyp"));
        stavColumn.setCellValueFactory(new PropertyValueFactory<>("tableStav"));
        reklamaColumn.setCellValueFactory(new PropertyValueFactory<>("tableReklama"));
        updateColumn.setCellValueFactory(new PropertyValueFactory<>("update"));
        deleteColumn.setCellValueFactory(new PropertyValueFactory<>("delete"));


        tableView.setItems(observableList);



    }


}








