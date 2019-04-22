package sample.servis;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Main;

import java.awt.*;
import java.io.IOException;

public class ServisController {

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
            root.getStylesheets().addAll(this.getClass().getResource("../style.css").toExternalForm());
            stageLogin.show();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }






}


