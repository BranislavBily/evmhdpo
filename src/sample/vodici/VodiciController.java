package sample.vodici;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.vozidla.TableModel;
import sample.vozidla.UpdateController;
import sample.vozidla.VozidlaController;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VodiciController implements Initializable {


    public TableView<sample.vodici.TableModelVod> tableView;
    public TableColumn<sample.vodici.TableModelVod,String> vodicID;
    public TableColumn<sample.vodici.TableModelVod,String> vodicName;
    public TableColumn<sample.vodici.TableModelVod, String> vodicSurname;
    public TableColumn<sample.vodici.TableModelVod,String> vozidlo;
    public TableColumn<sample.vodici.TableModelVod,String> stav;
    public TableColumn<sample.vodici.TableModelVod,String> prehliadka;
   
    public TableColumn<sample.vodici.TableModelVod, Button> updateColumn;
    public TableColumn<sample.vodici.TableModelVod,Button> deleteColumn;


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

    @FXML
    public void vozidlaClick() {
        try {
            Stage stageVozidla = (Stage) VozidlaButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(VodiciController.class.getResource("../vozidla/Vozidla.fxml"));
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
            FXMLLoader loader = new FXMLLoader(VodiciController.class.getResource("../vodici/Vodici.fxml"));
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
            FXMLLoader loader = new FXMLLoader(VodiciController.class.getResource("../servis/Servis.fxml"));
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
            FXMLLoader loader = new FXMLLoader(VodiciController.class.getResource("../login/Login.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            stageLogin.setTitle("Login");
            stageLogin.setScene(scene);
            //stageLogin.setFullScreen(true);
            stageLogin.setMaximized(true);
            stageLogin.setMinHeight(1080);
            stageLogin.setMinWidth(1920);
            root.getStylesheets().addAll(this.getClass().getResource("../style.css").toExternalForm());
            stageLogin.show();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void addButton(){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(VozidlaController.class.getResource("../vodici/Add.fxml"));
            Parent root = loader.load();
            root.getStylesheets().addAll(this.getClass().getResource("../style.css").toExternalForm());
            Scene scene = new Scene(root);

            stage.setTitle("Pridanie vodiča");
            stage.setScene(scene);
            stage.setMinHeight(720);
            stage.setMinWidth(480);
            stage.show();


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



        String sqlID="SELECT id FROM VODICI;";
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
                String delete="DELETE FROM VODICI WHERE id="+ finalId;
                System.out.println("finalId:" + finalId);
                try {
                    statement.executeUpdate(delete);
                    vodiciClick(); //refresh stránky
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

        String sqlID="SELECT id FROM VODICI;";
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
                FXMLLoader loader = new FXMLLoader(VozidlaController.class.getResource("../vodici/Update.fxml"));
                Parent root = null;
                try {
                    root = (Parent) loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                UpdateControllerVodici updateController = loader.getController();
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


    ObservableList<sample.vodici.TableModelVod> observableList= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection=connectionClass.getConnection();
            Statement statement=connection.createStatement();

            String sql2="SELECT * FROM VODICI;";
            ResultSet resultSet=statement.executeQuery(sql2);



            delete();
            updateWindow();

            int i=0;
            while (resultSet.next()){

                observableList.add(new TableModelVod(resultSet.getInt("evc_vodica"),resultSet.getString("name"),resultSet.getString("surname"),resultSet.getInt("evc_vehicle"),resultSet.getString("stav"),resultSet.getDate("prehliadka"),update[i],delete[i]));
                i++;
            }

            resultSet.close();
            connection.close();
            statement.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        vodicID.setCellValueFactory(new PropertyValueFactory<>("idVodica"));
        vodicName.setCellValueFactory(new PropertyValueFactory<>("menoVodica"));
        vodicSurname.setCellValueFactory(new PropertyValueFactory<>("priezviskoVodica"));
        vozidlo.setCellValueFactory(new PropertyValueFactory<>("vozidlo"));
        stav.setCellValueFactory(new PropertyValueFactory<>("stavVodica"));
        prehliadka.setCellValueFactory(new PropertyValueFactory<>("prehliadka"));
        updateColumn.setCellValueFactory(new PropertyValueFactory<>("update"));
        deleteColumn.setCellValueFactory(new PropertyValueFactory<>("delete"));


        tableView.setItems(observableList);



    }

}

