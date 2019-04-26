package sample;

import com.sun.javafx.css.StyleManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("login/Login.fxml"));
        root.setId("pane");
        primaryStage.setTitle("Evidencia vozidiel MHD");
        primaryStage.setScene(new Scene(root));
       // root.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.setMaximized(true);
        primaryStage.setMinHeight(1080);
        primaryStage.setMinWidth(1920);
        //primaryStage.setFullScreen(true);
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
        StyleManager.getInstance().addUserAgentStylesheet(getClass().getResource("style.css").toString());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
