package sample.vozidla;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Main;

import javax.swing.*;
import java.io.DataInput;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class VozidlaController implements Initializable {



    /*Vozidla Controller*/

    public TableView<TableModel> tableView;
    public TableColumn<TableModel,String> evcColumn;
    public TableColumn<TableModel,String> vodiciColumn;
    public TableColumn<TableModel, String> stkColumn;
    public TableColumn<TableModel,String> typColumn;
    public TableColumn<TableModel,String> stavColumn;
    public TableColumn<TableModel,String> reklamaColumn;
    public TableColumn<TableModel,String> spzColumn;

    @FXML
    private javafx.scene.control.Label VozidlaButton;
    @FXML
    private javafx.scene.control.Label ServisButton;
    @FXML
    private javafx.scene.control.Label VodiciButton;










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
            //stageLogin.setFullScreen(true)
            stageLogin.setMinHeight(720);
            stageLogin.setMinWidth(480);
            stageLogin.show();

        } catch (
                IOException e) {
            e.printStackTrace();
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

            while (resultSet.next()){
                observableList.add(new TableModel(resultSet.getString("evc"),resultSet.getString("spz"),resultSet.getString("vodic"),resultSet.getString("stk"),resultSet.getString("typVozidla"),resultSet.getString("stavVozidla"),resultSet.getString("reklama")));
            }


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

        tableView.setItems(observableList);


    }


    /*Pridanie Zaznamu ADD.fxml*/







}


