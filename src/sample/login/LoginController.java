package sample.login;


import connectivity.ConnectionClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.User;
import sample.vozidla.VozidlaController;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    @FXML
    private PasswordField passField;
    @FXML
    private TextField textField;
    @FXML
    private Label alertInfo;
    @FXML
    private CheckBox checkBox;

    public static User user;

    public static String userName;
    private String password;
    private int autoLogin;
    public int userID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textField.textProperty().addListener((observable, oldVal, newVal) -> {
            if (newVal.length() > 16) {
                textField.setText(oldVal);
            }
        });
        passField.textProperty().addListener((observable, oldVal, newVal) ->{
            if(newVal.length() > 16){
                passField.setText(oldVal);
            }
        });





    }




  @FXML
    private void login() throws SQLException {
      ConnectionClass connectionClass = new ConnectionClass();
      Connection connection = connectionClass.getConnection();
      Statement statement = connection.createStatement();


      String insertName = textField.getText().toLowerCase();
      String insertPassword = passField.getText();

      String sql = "SELECT * FROM users WHERE name ='"+insertName+"' AND password='"+insertPassword+"';";
      ResultSet results = statement.executeQuery(sql);


      if(!results.next()){
          System.out.println("Prihlásenie neúspešné");
          alertInfo.setText("Zle zadané údaje!");
      }else{
          System.out.println("Prihlásenie úspešné");

          userID=results.getInt("id");
          userName=results.getString("name");
          password=results.getString("password");

          /* Pridanie Remember me */

       /*   if(checkBox.isSelected()){
              passField.setText(password);
              textField.setText(userName);
          }else {
              //passField.setText("");
             // textField.setText("");
          }
         // Stage st = (Stage) checkBox.getScene().getWindow(); */


          System.out.println(userID);

          user= new User();
          user.setId(userID);
          user.setMeno(userName);
          user.setPassword(password);



          try {
              Stage stage = (Stage) textField.getScene().getWindow();
              FXMLLoader loader = new FXMLLoader(getClass().getResource("../vozidla/Vozidla.fxml"));
              Parent root = loader.load();

              Scene scene = new Scene(root);

              stage.setTitle("Vozidlá");
              stage.setScene(scene);
              stage.setMaximized(true);
              stage.setMinHeight(1080);
              stage.setMinWidth(1920);
              //stage.setFullScreen(true);
              stage.show();

          } catch (IOException e) {
              e.printStackTrace();
          }

      }

    }


  }


