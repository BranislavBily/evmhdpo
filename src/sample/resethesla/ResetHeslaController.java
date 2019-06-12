package sample.resethesla;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.email.emailFinder.EmailFinder;
import sample.email.emailSender.EmailSender;
import sample.email.verification.EmailVerificator;
import sample.password.PasswordChanger;
import sample.password.PasswordChangerJava;

public class ResetHeslaController {

    @FXML
    private TextField textFieldEmail;
    @FXML
    private PasswordField passField;
    @FXML
    private PasswordField passField1;
    @FXML
    private AnchorPane resetHeslaPane;
    @FXML
    private Button zmenitHeslo;
    @FXML
    private Button zaslatEmail;
    @FXML
    private Button buttonZaslatCode;
    @FXML
    private TextField textFieldCode;

    private int code;

    @FXML
    private void onOveritEmail() {
        System.out.println("Overujem kod");
        String email = textFieldEmail.getText();
        if (!EmailVerificator.isEmailCorrect(email)) {
            System.out.println("Zly email");
        } else if(!EmailFinder.emailInDatabase(email)) {
            System.out.println("Neexistuje pouzivatel s takymto emailom");
        } else {
            code = generateCode();
            EmailSender.sendVerificationEmail(email,"Cau", code);
            System.out.println(code);
            resizeScene(400);
            resetHeslaPane.autosize();
            textFieldCode.setVisible(true);
            buttonZaslatCode.setVisible(true);
            zaslatEmail.setDisable(true);
            textFieldEmail.setDisable(true);
        }
    }

    @FXML
    private void onOveritKod() {
        try {
            int userKod = Integer.parseInt(textFieldCode.getText());
            if(userKod == code) {
                System.out.println("Yey");
                buttonZaslatCode.setDisable(true);
                textFieldCode.setDisable(true);
                passField.setVisible(true);
                passField1.setVisible(true);
                zmenitHeslo.setVisible(true);
                resizeScene(620);
                resetHeslaPane.autosize();
            } else {
                System.out.println("Zly kod");
            }
        } catch (NumberFormatException e) {
            System.out.println("Sa spamataj");
        }
    }

    @FXML
    private void onZmenitHeslo() {
        String password = passField.getText();
        String passwordAgain = passField1.getText();
        if(password.equals(passwordAgain) && password.length() > 8) {
            PasswordChangerJava.changePassword(textFieldEmail.getText(), password);
            Stage stage = (Stage) textFieldCode.getScene().getWindow();
            stage.close();
        } else {
            System.out.println("Zle heslo");
        }
    }

    private int generateCode() {
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            number.append((int) (Math.random() * 10));
        }
        return Integer.parseInt(number.toString());
    }

    private void resizeScene(int height) {
        resetHeslaPane.resize(362, height);
        Stage stage = (Stage) passField.getScene().getWindow();
        stage.setMinHeight(height);
        stage.setMinWidth(362);
    }


}
