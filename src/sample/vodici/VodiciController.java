package sample.vodici;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.User;
import sample.login.LoginController;
import sample.vozidla.VozidlaController;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VodiciController extends User implements Initializable {


    public TableView<sample.vodici.TableModelVod> tableView;
    public TableColumn<sample.vodici.TableModelVod, String> vodicID;
    public TableColumn<sample.vodici.TableModelVod, String> vodicName;
    public TableColumn<sample.vodici.TableModelVod, String> vodicSurname;
    public TableColumn<sample.vodici.TableModelVod, String> vozidlo;
    public TableColumn<sample.vodici.TableModelVod, String> stav;
    public TableColumn<sample.vodici.TableModelVod, String> prehliadka;

    public TableColumn<sample.vodici.TableModelVod, Button> updateColumn;
    public TableColumn<sample.vodici.TableModelVod, Button> deleteColumn;
    @FXML
    private TableColumn<sample.vodici.TableModelVod, Button> contactColumn;
    public Label userLabel;


    Button[] delete;
    Button[] update;
    Button[] contact;
    int updateID;
    Stage stageUpdate;
    int updateIdecko;
    String userName = "";

    @FXML
    private javafx.scene.control.Label VozidlaButton;
    @FXML
    private javafx.scene.control.Label ServisButton;
    @FXML
    private javafx.scene.control.Label VodiciButton;

    public VodiciController() {

    }


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

    public void logOut() {
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

            stageLogin.show();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void addButton() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(VozidlaController.class.getResource("../vodici/Add.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            stage.setTitle("Pridanie vodiča do evidencie");
            stage.setScene(scene);
            stage.setMinHeight(380);
            stage.setMinWidth(500);
            stage.setMaxHeight(380);
            stage.setMaxWidth(500);
            stage.showAndWait();

            vodiciClick();


        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    /*Funkcia ktorá zaobstaráva vymazanie údaju*/

    public void delete() throws SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement statement = connection.createStatement();


        String sqlID = "SELECT id FROM vodici;";
        ResultSet results = statement.executeQuery(sqlID);
        ArrayList<Integer> ids = new ArrayList<>();
        while (results.next()) {
            ids.add(results.getInt("id"));
        }
        delete = new Button[ids.size()];

        for (int i = 0; i < ids.size(); i++) {
            delete[i] = new Button();
            int finalId = ids.get(i);
            delete[i].setOnAction(e -> {
                String delete = "DELETE FROM vodici WHERE id=" + finalId;
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
        Connection connection = connectionClass.getConnection();
        Statement statement = connection.createStatement();

        String sqlID = "SELECT id FROM vodici;";
        ResultSet results = statement.executeQuery(sqlID);
        ArrayList<Integer> ids = new ArrayList<>();
        while (results.next()) {
            ids.add(results.getInt("id"));
        }
        update = new Button[ids.size()];

        for (int i = 0; i < ids.size(); i++) {
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


                Scene scene = new Scene(root);


                updateController.updateStage(stageUpdate);
                stageUpdate.setTitle("Zmena údajov o vodičovi");
                stageUpdate.setScene(scene);
                stageUpdate.setMinHeight(420);
                stageUpdate.setMinWidth(520);
                stageUpdate.setMaxHeight(420);
                stageUpdate.setMaxWidth(520);
                stageUpdate.showAndWait();

                vodiciClick();
            });
            {

            }
        }

    }

    public void callDatabase() {
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            Statement statement = connection.createStatement();
            String sql2 = "SELECT * FROM vodici;";
            ResultSet resultSet = statement.executeQuery(sql2);
            delete();
            updateWindow();
            createContactButtonsForAllVodici();
            int i = 0;
            while (resultSet.next()) {

                observableList.add(new TableModelVod(resultSet.getInt("evc_vodica"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("evc_vehicle"),
                        resultSet.getString("stav"),
                        resultSet.getDate("prehliadka"),
                        update[i],
                        delete[i],
                        contact[i],
                        resultSet.getString("email")));
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
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));


        tableView.setItems(observableList);
    }

    private void createContactButtonsForAllVodici() {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement statement;
        try {
            statement = connection.createStatement();
            String sqlID = "SELECT email FROM vodici;";
            ResultSet results = statement.executeQuery(sqlID);
            ArrayList<String> emails = new ArrayList<>();
            while (results.next()) {
                emails.add(results.getString("email"));
            }
            contact = new Button[emails.size()];
            for (int i = 0; i < emails.size(); i++) {
                contact[i] = new Button();
                int finalI = i;
                contact[i].setOnAction(p -> {
                    Stage stage =  new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("sendEmail.fxml"));
                    try {
                        Parent root = loader.load();
                        SendEmailController updateWorkoutController = loader.getController();
                        updateWorkoutController.onCreate(emails.get(finalI));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setTitle("Poslať email");
                        stage.setResizable(false);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.showAndWait();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    ObservableList<sample.vodici.TableModelVod> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        callDatabase();
        userLabel.setText(LoginController.user.getMeno());
    }

}

