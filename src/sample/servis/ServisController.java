package sample.servis;

import connectivity.ConnectionClass;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Main;
import sample.User;
import sample.login.LoginController;
import sample.vodici.TableModelVod;
import sample.vodici.UpdateControllerVodici;
import sample.vodici.VodiciController;
import sample.vozidla.VozidlaController;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ServisController extends User implements Initializable {


    public TableView<TableModelServ> tableView;
    public TableColumn<TableModelServ,String> vehicleID;
    public TableColumn<sample.servis.TableModelServ,String>odstavene;
    public TableColumn<sample.servis.TableModelServ, String>zavada;
    public TableColumn<sample.servis.TableModelServ,String>hala;
    public TableColumn<sample.servis.TableModelServ,String> stav;
    public TableColumn<sample.servis.TableModelServ,String> oprava;

    public TableColumn<sample.servis.TableModelServ, javafx.scene.control.Button> updateColumn;
    public TableColumn<sample.servis.TableModelServ, javafx.scene.control.Button> deleteColumn;
    public Label userLabel;


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

    public ServisController() {

    }

    @FXML
    public void vozidlaClick() {
        try {
            Stage stageVozidla = (Stage) VozidlaButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(ServisController.class.getResource("../vozidla/Vozidla.fxml"));
            Parent root = loader.load();



            Scene scene = new Scene(root);

            stageVozidla.setTitle("Vozidlá");
            stageVozidla.setScene(scene);
            //stageVozidla.setFullScreen(true);
            stageVozidla.setMaximized(true);
            stageVozidla.setMinHeight(1080);
            stageVozidla.setMinWidth(1920);

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
            FXMLLoader loader = new FXMLLoader(ServisController.class.getResource("../vodici/Vodici.fxml"));
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
            FXMLLoader loader = new FXMLLoader(ServisController.class.getResource("../servis/Servis.fxml"));
            Parent root = loader.load();


            Scene scene = new Scene(root);

            stageServis.setTitle("Servis");
            stageServis.setScene(scene);
            stageServis.setMaximized(true);
            stageServis.setMinHeight(1080);
            stageServis.setMinWidth(1920);
            //stageServis.setFullScreen(true);
            stageServis.show();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
    public void logOut(){
        try {
            Stage stageLogin = (Stage) ServisButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(ServisController.class.getResource("../login/Login.fxml"));
            Parent root = loader.load();

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


    public void addButton(){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(VozidlaController.class.getResource("../servis/Add.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            stage.setTitle("Pridanie vozidla na servisovanie");
            stage.setScene(scene);
            stage.setMinHeight(380);
            stage.setMinWidth(480);
            stage.setMaxHeight(380);
            stage.setMaxWidth(480);
            stage.showAndWait();

            servisClick();


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



        String sqlID="SELECT id FROM servis;";
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
                String delete="DELETE FROM servis WHERE id="+ finalId;
                System.out.println("finalId:" + finalId);
                try {
                    statement.executeUpdate(delete);
                    servisClick(); //refresh stránky
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

        String sqlID="SELECT id FROM servis;";
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
                FXMLLoader loader = new FXMLLoader(VozidlaController.class.getResource("../servis/Update.fxml"));
                Parent root = null;
                try {
                    root = (Parent) loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                UpdateControllerServis updateController = loader.getController();
                updateController.updateId(updateID);



                Scene scene = new Scene(root);


                updateController.updateStage(stageUpdate);
                stageUpdate.setTitle("Zmena údajov o vozidle v servise");
                stageUpdate.setScene(scene);
                stageUpdate.setMinHeight(420);
                stageUpdate.setMinWidth(500);
                stageUpdate.setMaxHeight(420);
                stageUpdate.setMaxWidth(500);
                stageUpdate.showAndWait();

                servisClick();
            }); {

            }
        }

    }

    public void callDatabase(){
        try {

            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection=connectionClass.getConnection();
            Statement statement=connection.createStatement();

            String sql2="SELECT * FROM servis;";
            ResultSet resultSet=statement.executeQuery(sql2);



            delete();
            updateWindow();

            int i=0;
            while (resultSet.next()){

                observableList.add(new TableModelServ(resultSet.getInt("evc_vehicle"),resultSet.getDate("odstavene"),resultSet.getString("zavada"),resultSet.getString("hala"),resultSet.getString("stav"),resultSet.getDate("oprava"),update[i],delete[i]));
                i++;
            }

            resultSet.close();
            connection.close();
            statement.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        vehicleID.setCellValueFactory(new PropertyValueFactory<>("evcVozidla"));
        odstavene.setCellValueFactory(new PropertyValueFactory<>("odstavene"));
        zavada.setCellValueFactory(new PropertyValueFactory<>("zavada"));
        hala.setCellValueFactory(new PropertyValueFactory<>("hala"));
        stav.setCellValueFactory(new PropertyValueFactory<>("stav"));
        oprava.setCellValueFactory(new PropertyValueFactory<>("odovzdanie"));
        updateColumn.setCellValueFactory(new PropertyValueFactory<>("update"));
        deleteColumn.setCellValueFactory(new PropertyValueFactory<>("delete"));


        tableView.setItems(observableList);




    }


    ObservableList<TableModelServ> observableList= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        callDatabase();
        userLabel.setText(LoginController.user.getMeno());



    }





}


